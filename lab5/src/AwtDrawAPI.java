import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cawa on 01.12.2017.
 */
public class AwtDrawAPI implements DrawingApi {

    int weigth = 1000;
    int height = 1000;
    float r = 25.0f;

    private Set<Ellipse2D.Float> circles;
    private Set<Line> lines;

    AwtDrawAPI() {
        circles = new HashSet<Ellipse2D.Float>();
        lines = new HashSet<Line>();
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public long getDrawingAreaWidth() {
        return weigth;
    }

    @Override
    public void drawCircle(float x, float y) {
        circles.add(new Ellipse2D.Float(x - r/2, y - r / 2,r, r ));
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        lines.add(new Line(x1, y1, x2, y2));
    }

    @Override
    public void show() {
        Frame frame = new AwtFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize(weigth, height);
        frame.setVisible(true);
    }

    class AwtFrame extends Frame {
        @Override
        public void paint(Graphics g) {
            Graphics2D ga = (Graphics2D) g;
            ga.setPaint(Color.black);
            for (Line line:lines) {
                ga.drawLine((int) line.a,(int)  line.b,(int)  line.c,(int) line.d);
            }
            ga.setPaint(Color.green);
            for (Ellipse2D.Float el:circles) {
                ga.fill(el);
            }
        }
    }

    class Line {
        public float a, b, c, d;
        Line(float x1, float y1, float x2, float y2) {
            a = x1;
            b = y1;
            c = x2;
            d = y2;
        }
    }
}