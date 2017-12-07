package visitors;

import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;
import tokens.Token;

import java.util.List;
import java.util.Stack;

/**
 * Created by Cawa on 01.12.2017.
 */
public class CalcVisitor implements TokenVisitor {

    Stack<Integer> stack;

    public CalcVisitor() {
        stack = new Stack<>();
    }

    public Integer parse(List<Token> list) {
        for (Token t:list) {
            t.accept(this);
        }
        return stack.pop();
    }

    @Override
    public void visit(NumberToken token) {
        stack.push(token.getVal());
    }

    @Override
    public void visit(Operation token) {
        int a = stack.pop();
        int b = stack.pop();
        stack.push(token.calc(b, a));
    }

    @Override
    public void visit(Brace token) {}
}
