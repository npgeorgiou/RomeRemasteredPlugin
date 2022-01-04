package rr.language;

public class Util {
    public static String quote(String string) {
        return "\"" + string + "\"";
    }

    public static String unquote(String string) {
        if ((string.startsWith("\"") && string.endsWith("\""))) {
            string = string.substring(1, string.length() - 1);
        }

        return string;
    }
    public static String removeLastOccurrence(String str, String search) {
        if (str.endsWith(search)) {
            str = str.substring(0, str.length() - search.length());
        }

        return str;
    }
}