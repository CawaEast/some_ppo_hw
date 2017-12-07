import tokens.Token;
import tokens.Tokenizer;
import visitors.CalcVisitor;
import visitors.ParseVisitor;
import visitors.PrintVisitor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String str = "23 + 10 * 5 - 3 * 32 + 5 * (10 - 4 * 5) + 8 / 2";
        List<Token> tokens = new ArrayList<>();
        try {
            InputStream stream = new ByteArrayInputStream(str.getBytes());
            Tokenizer tokenizer = new Tokenizer();
            PrintVisitor printVisitor = new PrintVisitor();
            ParseVisitor parseVisitor = new ParseVisitor();
            CalcVisitor calcVisitor = new CalcVisitor();

            tokens = tokenizer.parse(stream);
            List<Token> tokens1 = parseVisitor.parse(tokens);
            int ans = calcVisitor.parse(tokens1);

            System.out.println(str);
            System.out.println(printVisitor.parse(tokens).toString());
            System.out.println(printVisitor.parse(tokens1).toString());
            System.out.print(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
	// write your code here
    }
}
