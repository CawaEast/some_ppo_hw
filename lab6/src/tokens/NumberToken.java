package tokens;

import visitors.TokenVisitor;

/**
 * Created by Cawa on 01.12.2017.
 */
public class NumberToken implements Token {
    int val;
    String name = "NUMBER";

    NumberToken(int v) {
        super();
        val = v;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return name + "(" + Integer.toString(val) + ")";
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
