public class Main {

    public static void main(String[] args) {
        EventsStatistic ec = new EventsStatisticSimple();
        ec.incEvent("t1");
        ec.incEvent("t1");
        ec.incEvent("t1");
        ec.incEvent("t2");
        ec.printStatistic();
	// write your code here
    }
}
