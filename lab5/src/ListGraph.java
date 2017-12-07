import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cawa on 01.12.2017.
 */
public class ListGraph extends Graph {

    private List<LNode> nodes;

    ListGraph(DrawingApi da) {
        super(da);
        nodes = new ArrayList<LNode>();
    }

    @Override
    void addNode(float x, float y) {
        nodes.add(new LNode(x, y));
    }

    @Override
    void addEdge(int i, int j) {
        LNode a = nodes.get(i);
        LNode b = nodes.get(j);
        a.addEdgeTo(b);
        b.addEdgeTo(a);
    }

    @Override
    int getSize() {
        return nodes.size();
    }

    @Override
    public Node getNode(int i) {
        if (i <= getSize()) {
            return nodes.get(i);
        }
        return null;
    }

    @Override
    public void drawGraph() {
        for (int i = 0; i < getSize(); i++) {
            LNode a = nodes.get(i);
            for (LNode b : a.edges) {
                drawingApi.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
            }
            drawingApi.drawCircle(a.getX(), a.getY());
        }
        drawingApi.show();
    }

    class LNode extends Node{
        Set<LNode> edges;

        LNode(float x, float y) {
            super(x, y);
            edges = new HashSet<LNode>();
        }

        void addEdgeTo(LNode n) {
            edges.add(n);
        }
    }
}
