package tokens;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.reverseBytes;

/**
 * Created by Cawa on 01.12.2017.
 */
public class Tokenizer {
    List<Token> tokens;
    InputStream input;
    boolean eof = false;
    char currChar;
    int lvl = 0;

    public Tokenizer() {
        tokens = new LinkedList<Token>();
    }

    public List<Token> parse(InputStream is) throws TokenizerException{
        input = is;
        nextChar();
        while (!eof) {
            tokens.add(nextToken());
        }
        return tokens;
    }

    private char nextChar() throws TokenizerException{
        try {
            int c = input.read();
            if (c < 0) {
                eof = true;
                return currChar;
            } else {
                currChar = (char) c;
                return currChar;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new TokenizerException(e.getMessage());
        }
    }

    private Token nextToken() throws TokenizerException{
        char c = currChar;
        while ((c == ' ') && (!eof)) {
            c = nextChar();
        }
        if (eof) {
            return null;
        }
        Token token = null;
        switch (c) {
            case '+':
                token = new Add();
                break;
            case '-':
                token = new Sub();
                break;
            case '*':
                token = new Mul();
                break;
            case '/':
                token = new Div();
                break;
            case '(':
                lvl++;
                token = new Left();
                break;
            case ')':
                lvl--;
                if (lvl < 0) {
                    throw new TokenizerException("Mismatched brackets");
                }
                token = new Right();
                break;
        }
        if (token != null) {
            nextChar();
        }
        String curr = "";
        if (isDigit(c)) {
            while (isDigit(c) && (!eof)) {
                curr += c;
                c = nextChar();
            }
            token = new NumberToken(new Integer(curr));
        }
        if (token == null) {
            throw new TokenizerException("Unexpected char");
        }
        return token;
    }
}
