import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author akirakozov
 */
public class FxDrawApp extends Application {

    private Set<FxDrawAPI.Line> circles;
    private Set<FxDrawAPI.Line> lines;

    public static final CountDownLatch latch = new CountDownLatch(1);
    public static FxDrawApp startUpTest = null;

    public static FxDrawApp waitForStartUpTest() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return startUpTest;
    }

    public static void setStartUpTest(FxDrawApp startUpTest0) {
        startUpTest = startUpTest0;
        latch.countDown();
    }

    public FxDrawApp() {
        setStartUpTest(this);
    }

    public void setData(Set<FxDrawAPI.Line> c, Set<FxDrawAPI.Line> l) {
        lines = l;
        circles = c;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing circle");
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (FxDrawAPI.Line line:lines) {
            Line l = new Line(line.a, line.b, line.c, line.d);
            root.getChildren().add(l);
        }
        gc.setFill(Color.GREEN);
        for (FxDrawAPI.Line el:circles) {
            gc.fillOval(el.a, el.b, el.c, el.d);
        }
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}