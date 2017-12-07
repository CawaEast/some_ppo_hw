package visitors;

import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;

/**
 * Created by Cawa on 01.12.2017.
 */
public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(Brace token);
    void visit(Operation token);
}