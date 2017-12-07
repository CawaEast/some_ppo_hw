public class Main {

    public static void main(String[] args) {
        DrawingApi api = null;
        Graph graph = null;
        if (args != null) {
            if (args.length >= 2) {
                if (args[0].equals("awt")) {
                    api = new AwtDrawAPI();
                } else {
                    if (args[0].equals("fx")) {
                        api = new FxDrawAPI();
                    }
                }
                if (args[1].equals("list")) {
                    graph = new ListGraph(api);
                } else {
                    if (args[1].equals("matrix")) {
                        graph = new MatrixGraph(api);
                    }
                }
            }
        }
        if ((api == null) || (graph == null)) {
            System.out.print("Should be 2 args: awt/fx; list/matrix.");
            return;
        }

        graph.addNode(100, 100);
        graph.addNode(200, 100);
        graph.addNode(200, 200);
        graph.addNode(100, 200);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        graph.drawGraph();
    }
}
