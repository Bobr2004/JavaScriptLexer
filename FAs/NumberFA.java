package FAs;

public class NumberFA {
    public static String matchNumberFA(String input) {
        int state = 0;
        int i = 0;

        while (i < input.length()) {
            char ch = input.charAt(i);

            switch (state) {
                case 0:
                    if (Character.isDigit(ch)) {
                        state = 1;
                    } else {
                        return null;
                    }
                    break;
                case 1:
                    if (Character.isDigit(ch)) {
                        // stay in state 1
                    } else if (ch == '.') {
                        state = 2;
                    } else {
                        return input.substring(0, i);
                    }
                    break;
                case 2:
                    if (Character.isDigit(ch)) {
                        state = 3;
                    } else {
                        return null; // invalid float (e.g., "123.")
                    }
                    break;
                case 3:
                    if (!Character.isDigit(ch)) {
                        return input.substring(0, i);
                    }
                    break;
            }
            i++;
        }

        if (state == 1 || state == 3) {
            return input.substring(0, i); // valid number (int or float)
        }

        return null;
    }
}
