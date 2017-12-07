import java.util.*;

/**
 * Created by Cawa on 01.12.2017.
 */
public class MatrixGraph extends Graph {

    private List<Pair> nodes;
    private List<Set<Integer>> edges;

    public MatrixGraph(DrawingApi drawingApi) {
        super(drawingApi);
        nodes = new ArrayList<>();
        edges = new ArrayList<Set<Integer>>();
    }

    @Override
    public void addNode(float x, float y) {
        Pair p = new Pair(x, y);
        nodes.add(p);
        edges.add(new HashSet<Integer>());
    }

    @Override
    void addEdge(int a, int b) {
        if ((a >= getSize()) || (b >=  getSize()) || (a == b)) {
            return;
        }/*
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }*/
        edges.get(a).add(b);
        edges.get(b).add(a);
    }

    @Override
    public void drawGraph() {
        for (int i = 0; i < getSize(); i++) {
            Node a = nodes.get(i);
            Set<Integer> e = edges.get(i);
            for (int j : e) {
                Node b = nodes.get(j);
                drawingApi.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
            }
            drawingApi.drawCircle(a.getX(), a.getY());
        }
        drawingApi.show();
    }


    public int getSize() {
        return nodes.size();
    }

    public Node getNode(int i) {
        if (i <= getSize()) {
            return nodes.get(i);
        }
        return null;
    }

    class Pair extends Graph.Node {
        Pair(float x1, float y1) {
            super(x1, y1);
        }
    }
}
