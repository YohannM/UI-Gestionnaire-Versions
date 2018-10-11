
package tools;

public class StringTools {
    public static String testString(String str)
    {
        return (estValide(str)) ? str : "Inconnue";
    }
    
    public static boolean estValide(String str)
    {
        return (str != null && !str.equals(""));
    }
}
