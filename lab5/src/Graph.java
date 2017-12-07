import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cawa on 01.12.2017.
 */
abstract class Graph {

    DrawingApi drawingApi;

    Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public abstract void drawGraph();

    abstract void addNode(float x, float y);
    abstract void addEdge(int a, int b);

    abstract int getSize();
    abstract public Node getNode(int i);

    public void show() {
        drawingApi.show();
    }


    abstract class Node {
        float x;
        float y;

        Node(float x, float y) {
            this.x = x;
            this.y = y;
        }
        float getX(){
            return x;
        }
        float getY(){
            return y;
        }
    }
}
