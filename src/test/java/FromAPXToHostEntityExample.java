import org.example.annotations.HostFieldDate;
import org.example.annotations.HostFieldString;

import java.time.LocalDate;
import java.util.HashMap;

public class FromAPXToHostEntityExample extends HashMap<String, Object> {


    private static final String NAME = "NAME";
    private static final String TARGET_DATE = "TARGET_DATE";

    @HostFieldString(name=NAME, start = 0, end = 6)
    public String getName() {
        return (String) get(NAME);
    }

    public void setName(String name) {
        put(NAME, name);
    }

    @HostFieldDate(name = TARGET_DATE, start = 6, end = 8, pattern="MM")
    public LocalDate getTargetDate() {
        return (LocalDate) get(TARGET_DATE);
    }

    public void setTargetDate(LocalDate date) {
        put(TARGET_DATE, date);
    }

}
