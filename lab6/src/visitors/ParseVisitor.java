package visitors;

import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;
import tokens.Token;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Cawa on 01.12.2017.
 */
public class ParseVisitor implements TokenVisitor {

    Stack<Token> stack;
    Stack<Token> ops;

    public ParseVisitor() {
        stack = new Stack<>();
        ops = new Stack<>();
    }

    public List<Token> parse(List<Token> list) {
        for (Token t:list) {
            t.accept(this);
        }
        while (!ops.isEmpty()) {
            stack.push(ops.pop());
        }
        return stack;
    }

    @Override
    public void visit(NumberToken token) {
        stack.push(token);
    }

    @Override
    public void visit(Operation token) {
        ops.push(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.toString().equals("LEFT")) {
            ops.add(token);
        } else {
            while (true) {
                if (ops.isEmpty()) {
                    System.err.print("oos");
                    return;
                }
                Token t = ops.pop();
                if (t.toString().equals("LEFT")) {
                    return;
                }
                stack.push(t);
            }
        }
    }
}
