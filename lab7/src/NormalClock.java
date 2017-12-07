import java.time.Instant;

/**
 * Created by Cawa on 01.12.2017.
 */
public class NormalClock implements Clock {
    public Instant now() {
        return Instant.now();
    }
}
