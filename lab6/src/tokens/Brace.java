package tokens;

import visitors.TokenVisitor;

/**
 * Created by Cawa on 01.12.2017.
 */
public class Brace implements Token{

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
