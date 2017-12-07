package tokens;


/**
 * Created by Cawa on 01.12.2017.
 */
public class Sub extends Operation {

    @Override
    public int calc(int a, int b) {
        return a - b;
    }


    @Override
    public String toString() {
        return "SUB";
    }
}
