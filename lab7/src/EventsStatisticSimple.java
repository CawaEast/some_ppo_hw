import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Cawa on 01.12.2017.
 */
public class EventsStatisticSimple implements EventsStatistic {

    HashMap<String, Task> tasks;
    Clock clock;

    EventsStatisticSimple() {
        tasks = new HashMap<>();
        clock = new NormalClock();
    }

    @Override
    public void incEvent(String name) {
        Instant now = clock.now();
        if (!tasks.containsKey(name)) {
            tasks.put(name, new Task(name));
        }
        Task task = tasks.get(name);
        task.inc(now);
    }

    @Override
    public float getEventStatisticByName(String name) {
        Instant now = clock.now();
        if (!tasks.containsKey(name)) {
            return 0;
        }
        Task task = tasks.get(name);
        return syncTask(task, now);
    }

    @Override
    public float getAllEventStatistic() {
        Instant now = clock.now();
        float acc = 0;
        Set<String> keys = new LinkedHashSet<>(tasks.keySet());
        for (String key: keys) {
            acc += syncTask(tasks.get(key), now);
        }
        return acc;
    }


    @Override
    public void printStatistic() {
        Instant now = clock.now();
        for (Task task : tasks.values()) {
            float num = syncTask(task, now);
            if (num != 0) {
                System.out.println("Task: " + task.name + ", amount: " + Float.toString(num));
            }
        }
    }

    private float syncTask(Task task, Instant now) {
        int num = task.sync(now);
        if (num == 0) {
            tasks.remove(task.name);
            return 0;
        }
        //System.out.print(num);
        return num /60.0f;
    }

    class Task {
        String name;
        Set<Instant> times;

        Task(String n) {
            name = n;
            times = new HashSet<>();
        }

        void inc(Instant now) {
            times.add(now);
        }

        int sync(Instant now) {
            int count = 0;
            Instant outdated = now.minusSeconds(60*60);
            times = times.stream().filter((Instant t)-> t.compareTo(outdated) > 0).collect(Collectors.toSet());
            return times.size();
        }
    }
}
