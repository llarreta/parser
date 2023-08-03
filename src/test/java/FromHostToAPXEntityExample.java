import org.example.annotations.HostEntity;
import org.example.annotations.HostFieldDate;
import org.example.annotations.HostFieldString;

import java.time.LocalDate;
import java.util.HashMap;

@HostEntity
public class FromHostToAPXEntityExample extends HashMap<String, Object>  {

    private static final String FIELD1 = "FIELD1";
    private static final String MYDATE = "MYDATE";

    @HostFieldString(name=FIELD1, start = 0, end = 6)
    public String getField1() {
        return (String) get(FIELD1);
    }

    public void setField1(String field1) {
        put(FIELD1, field1);
    }

    @HostFieldDate(name = MYDATE, start = 6, end = 14, pattern="ddMMyyyy")
    public LocalDate getMyDate() {
        return (LocalDate) get(MYDATE);
    }

    public void setMyDate(LocalDate date) {
        put(MYDATE, date);
    }


}
