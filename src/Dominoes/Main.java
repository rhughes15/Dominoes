package Dominoes;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application
{
  private Controller controller;

  public static void main(String[] args)
  {
    launch(args);
  }

  public void start(Stage primaryStage)
  {
    primaryStage.setTitle("Dominoes");
    Group root = new Group();
    Canvas canvas = new Canvas(Display.SCREEN_WIDTH, Display.SCREEN_HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    controller = new Controller(gc);
    canvas.setOnMousePressed(mousePressedEventHandler);
    canvas.setOnMouseDragged(mouseDraggedEventHandler);
    canvas.setOnMouseReleased(mouseReleasedEventHandler);
    root.getChildren().add(canvas);
    Scene scene = new Scene(root, Display.SCREEN_WIDTH, Display.SCREEN_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  EventHandler<MouseEvent> mousePressedEventHandler = new EventHandler<MouseEvent>()
  {
    @Override
    public void handle(MouseEvent event) {
      controller.handlePress(event);
    }
  };

  EventHandler<MouseEvent> mouseDraggedEventHandler = new EventHandler<MouseEvent>()
  {
    @Override
    public void handle(MouseEvent event) {
      controller.handleDrag(event);
    }
  };

  EventHandler<MouseEvent> mouseReleasedEventHandler = new EventHandler<MouseEvent>()
  {
    @Override
    public void handle(MouseEvent event) {
      controller.handleRelease(event);
    }
  };
}
