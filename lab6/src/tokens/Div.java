package tokens;


/**
 * Created by Cawa on 01.12.2017.
 */
public class Div extends Operation {

    String name = "DIV";

    @Override
    public int calc(int a, int b) {
        if (b == 0) {
            return 0;
        } else {
            return a / b;
        }
    }

    @Override
    public String toString() {
        return "DIV";
    }
}
