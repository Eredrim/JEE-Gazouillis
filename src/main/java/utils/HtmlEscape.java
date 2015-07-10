package main.java.utils;


public class HtmlEscape {

    public static String escapeHtml(String string) {
        return string.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
