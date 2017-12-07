/**
 * Created by Cawa on 01.12.2017.
 */
public interface EventsStatistic {
    void incEvent(String name);
    float getEventStatisticByName(String name);
    float getAllEventStatistic();
    void printStatistic();
}