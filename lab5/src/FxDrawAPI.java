import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cawa on 01.12.2017.
 */
public class FxDrawAPI implements DrawingApi{

    int weigth = 1000;
    int height = 1000;
    float r = 25.0f;

    private Set<Line> circles;
    private Set<Line> lines;

    FxDrawAPI() {
        circles = new HashSet<Line>();
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
        circles.add(new Line(x - r / 2, y - r / 2, r, r));
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        lines.add(new Line(x1, y1, x2, y2));
    }

    @Override
    public void show() {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(FxDrawApp.class);
            }
        }.start();
        FxDrawApp startUpTest = FxDrawApp.waitForStartUpTest();
        startUpTest.setData(circles, lines);
    }

    class Line {
        float a, b, c, d;
        Line(float x1, float y1, float x2, float y2) {
            a = x1;
            b = y1;
            c = x2;
            d = y2;
        }
    }
}