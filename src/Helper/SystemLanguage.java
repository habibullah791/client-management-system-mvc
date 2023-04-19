package Helper;

import java.util.function.Supplier;

public class SystemLanguage {
 
    public static Supplier<String> language = () -> "";

    public static String getLanguage() {
        return language.get();
    }
    
    public static void setLanguage(String newLanguage) {
        language = () -> newLanguage;
    }
    
}
