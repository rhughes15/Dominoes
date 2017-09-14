package Dominoes;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//***********************************
// Ryan Hughes
//
// This class handles the initial javafx calls to set up
// window and create the canvas we will be drwaing on and
// sets up the listeners which call methods in the controller.
//***********************************

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

  /**
   * this is the event handler for when the mouse is clicked
   */
  EventHandler<MouseEvent> mousePressedEventHandler = new EventHandler<MouseEvent>()
  {
    /**
     * This method get's called when the mouse is pressed on
     * the canvas and passes the information to the controller
     */
    @Override
    public void handle(MouseEvent event)
    {
      controller.handlePress(event);
    }
  };

  /**
   * this is the event handler for when the mouse is dragged
   */
  EventHandler<MouseEvent> mouseDraggedEventHandler = new EventHandler<MouseEvent>()
  {
    /**
     * This method get's called when the mouse is dragged on
     * the canvas and passes the information to the controller
     */
    @Override
    public void handle(MouseEvent event)
    {
      controller.handleDrag(event);
    }
  };

  /**
   * this is the event handler for when the mouse is released
   */
  EventHandler<MouseEvent> mouseReleasedEventHandler = new EventHandler<MouseEvent>()
  {
    /**
     * This method get's called when the mouse is released on
     * the canvas and passes the information to the controller
     */
    @Override
    public void handle(MouseEvent event) {
      controller.handleRelease(event);
    }
  };
}
