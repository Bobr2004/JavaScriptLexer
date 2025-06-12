package FAs;

public class StringFA {
    public static String matchString(String input) {
        if (input.isEmpty()) return null;

        char quote = input.charAt(0);
        if (quote != '"' && quote != '\'') return null;

        int i = 1;
        boolean escape = false;

        while (i < input.length()) {
            char ch = input.charAt(i);

            if (escape) {
                escape = false;
            } else if (ch == '\\') {
                escape = true;
            } else if (ch == quote) {
                return input.substring(0, i + 1);
            }

            i++;
        }

        return null;
    }
}
