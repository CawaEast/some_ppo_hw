import java.time.Instant;

/**
 * Created by Cawa on 01.12.2017.
 */
public class SetableClock implements Clock {

    Instant now;

    public SetableClock(Instant now) {
        this.now = now;
    }

    public void setNow(Instant now){
        this.now = now;
    }

    public Instant now() {
        return now;
    }
}
