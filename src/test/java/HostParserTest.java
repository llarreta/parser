import org.example.exceptions.HostParserException;
import org.example.tools.HostParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class HostParserTest {


    private static String TEXT_FROM_HOST = "prueba01082023";

    @Test
    void test(){
        HostParser<FromHostToAPXEntityExample> parserToAPX = new HostParser();
        HostParser<FromAPXToHostEntityExample> parserToHost = new HostParser();
        try {
            FromHostToAPXEntityExample entity = parserToAPX.parse(TEXT_FROM_HOST, FromHostToAPXEntityExample.class);
            assertNotNull(entity);
            assertNotNull(entity.getField1());
            assertNotNull(entity.getMyDate());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

            assertEquals(FromHostToAPXEntityExample.class, entity.getClass());
            assertEquals("prueba", entity.getField1());
            assertEquals(LocalDate.parse("01082023", formatter), entity.getMyDate());

            FromAPXToHostEntityExample fromAPXToHostEntityExample = new FromAPXToHostEntityExample();
            fromAPXToHostEntityExample.setName(entity.getField1());
            fromAPXToHostEntityExample.setTargetDate(entity.getMyDate());

            String toHost = parserToHost.toString(fromAPXToHostEntityExample);
            System.out.println(toHost);
            assertNotNull(toHost);
            assertEquals("prueba08", toHost);

        } catch (HostParserException e) {
            assertTrue(Boolean.FALSE);
        }




    }

}
