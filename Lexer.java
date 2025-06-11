import java.util.*;
import java.util.regex.*;

public class Lexer {

    private static Set<String> keywords = new HashSet<>(Arrays.asList(
            "let", "const", "var", "function", "if", "else", "return"
    ));

    // Regex patterns (see next commit, where they are substituted with FAs)
    private static Pattern numberPattern = Pattern.compile("^\\d+(\\.\\d+)?");
    private static Pattern stringPattern = Pattern.compile("^\"(\\\\.|[^\"])*\"|^'(\\\\.|[^'])*'");
    private static Pattern namePattern = Pattern.compile("^[a-zA-Z_$][a-zA-Z0-9_$]*");
    private static Pattern operatorPattern = Pattern.compile("^(==|!=|<=|>=|\\+\\+|--|\\+|-|\\*|/|=|<|>)");
    private static Pattern symbolPattern = Pattern.compile("^[(){};,]");

    private String inputText; // the code we are going to scan
    private List<Token> tokenList = new ArrayList<>(); // this will store all tokens

    public Lexer(String input) {
        inputText = input;
    }

    // main function work it goes through the input and finds tokens
    public List<Token> tokenize() {
        int i = 0;
        int line = 1;

        while (i < inputText.length()) {
            char ch = inputText.charAt(i);

            // skip spaces and newlines
            if (Character.isWhitespace(ch)) {
                if (ch == '\n') {
                    line++; // count lines
                }
                i++;
                continue;
            }

            // get rest of the string to match with patterns
            String rest = inputText.substring(i);

            Matcher matcher;

            // check if it's a number
            matcher = numberPattern.matcher(rest);
            if (matcher.find()) {
                String number = matcher.group();
                tokenList.add(new Token(TokenType.NUMBER, number, line));
                i += number.length();
                continue;
            }

            // check if it's a string
            matcher = stringPattern.matcher(rest);
            if (matcher.find()) {
                String str = matcher.group();
                tokenList.add(new Token(TokenType.STRING, str, line));
                i += str.length();
                continue;
            }

            // check if it is a variable or keyword
            matcher = namePattern.matcher(rest);
            if (matcher.find()) {
                String name = matcher.group();
                TokenType type;
                if (keywords.contains(name)) {
                    type = TokenType.KEYWORD;
                } else {
                    type = TokenType.IDENTIFIER;
                }
                tokenList.add(new Token(type, name, line));
                i += name.length();
                continue;
            }

            // check if it is an operator like + or ==
            matcher = operatorPattern.matcher(rest);
            if (matcher.find()) {
                String op = matcher.group();
                tokenList.add(new Token(TokenType.OPERATOR, op, line));
                i += op.length();
                continue;
            }

            // check for simple symbols like ( or ;
            matcher = symbolPattern.matcher(rest);
            if (matcher.find()) {
                String symbol = matcher.group();
                tokenList.add(new Token(TokenType.SYMBOL, symbol, line));
                i += symbol.length();
                continue;
            }

            // if nothing matches, throw an error (add error token to the array)
            tokenList.add(new Token(TokenType.ERROR, String.valueOf(ch), line));
            System.out.println("Error at line " + line + ": '" + ch + "'"); // log error, line and char
            i++;
        }

        return tokenList; // send back list of the tokens
    }
}
