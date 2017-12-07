package tokens;

import visitors.TokenVisitor;

/**
 * Created by Cawa on 01.12.2017.
 */
public abstract class Operation implements Token{
    public abstract int calc(int a, int b);

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
