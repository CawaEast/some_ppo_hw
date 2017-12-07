package visitors;

import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;
import tokens.Token;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Cawa on 01.12.2017.
 */
public class PrintVisitor implements TokenVisitor {

    public PrintVisitor() {
    }

    public OutputStream parse(List<Token> list) throws VisitorException{
        OutputStream stream = new ByteArrayOutputStream();
        try {
            for (Token t : list) {
                String str = t.toString() + " ";
                stream.write(str.getBytes());
            }
        } catch (IOException e) {
            throw new VisitorException(e.getMessage());
        }
        return stream;
    }

    @Override
    public void visit(NumberToken token) {}

    @Override
    public void visit(Operation token) {}

    @Override
    public void visit(Brace token) {}
}
