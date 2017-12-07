import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;

/**
 * Created by Cawa on 01.12.2017.
 */
public class EventsStatisticSimpleTest {


    @Test
    public void incEvent() throws Exception {
        Instant i = Instant.now();
        EventsStatisticSimple ec = new EventsStatisticSimple();
        SetableClock clock = new SetableClock(i);
        ec.clock = clock;
        ec.incEvent("task1");
        ec.incEvent("task1");
        ec.incEvent("task1");
        Assert.check(ec.tasks.keySet().size() == 1, "1 name 1 task list");
        ec.incEvent("task2");
        ec.incEvent("task2");
        Assert.check(ec.tasks.keySet().size() == 2, "2 name 2 task list");
    }

    @Test
    public void getEventStatisticByName() throws Exception {
        Instant now = Instant.now();
        EventsStatisticSimple ec = new EventsStatisticSimple();
        SetableClock clock = new SetableClock(now);
        ec.clock = clock;
        for (int i = 0; i < 12; i++) {
            ec.incEvent("task1");
            ec.incEvent("task2");
            now = now.plusSeconds(60);
            clock.setNow(now);
        }
        float stats = ec.getEventStatisticByName("task1");
        Assert.check(stats == 0.2f, "just adds for 1 task");
        now = now.plusSeconds(60*53 + 2);
        clock.setNow(now);
        stats = ec.getEventStatisticByName("task1");
        Assert.check(stats == 0.1f, "just checks timeout for 1 task");
        now = now.plusSeconds(60*53 + 2);
        clock.setNow(now);
        stats = ec.getEventStatisticByName("task1");
        Assert.check(stats == 0.0f, "just checks full timeout for 1 task");
    }

    @Test
    public void getAllEventStatistic() throws Exception {
        Instant now = Instant.now();
        EventsStatisticSimple ec = new EventsStatisticSimple();
        SetableClock clock = new SetableClock(now);
        ec.clock = clock;
        for (int i = 0; i < 12; i++) {
            ec.incEvent("task1");
            ec.incEvent("task2");
            now = now.plusSeconds(60);
            clock.setNow(now);
        }
        float stats = ec.getAllEventStatistic();
        Assert.check(stats == 0.4f, "just adds for all task");
        now = now.plusSeconds(60*53 + 2);
        clock.setNow(now);
        stats = ec.getAllEventStatistic();
        Assert.check(stats == 0.2f, "just checks timeout for all task");
        now = now.plusSeconds(60*53 + 2);
        clock.setNow(now);
        stats = ec.getAllEventStatistic();
        Assert.check(stats == 0.0f, "just checks full timeout for all task");
    }

    @Test
    public void printStatistic() throws Exception {
    }

}