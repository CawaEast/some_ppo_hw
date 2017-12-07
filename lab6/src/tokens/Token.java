package tokens;

import visitors.TokenVisitor;

/**
 * Created by Cawa on 01.12.2017.
 */
public interface Token {
    void accept(TokenVisitor visitor);
}
