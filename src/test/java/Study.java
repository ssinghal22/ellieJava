import java.util.HashMap;
import java.util.Map;

public class Study {

    private static Map map;

    public Study() {
        map = new HashMap<>();
        map.put("foo", 1);
        map.put("bar", 3);
    }

    public static int getValue(String input, int numRetries) throws Exception {
        try {
            return (int) map.get(input);
                    //.intValue();
        }
        catch (Exception e) {
            if (numRetries > 3) {
                throw e;
            }
            return getValue(input, numRetries + 1);
        }
    }

    public static void main(String[] args) throws Exception {
      //  new MyClass();
        System.out.println(getValue("fubar", 1));
    }

}