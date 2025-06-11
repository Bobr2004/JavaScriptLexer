import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code = """
            let x = 42;
            const str = "hello";
            if (x > 10) {
                console.log(str);
            }
        """;

        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
