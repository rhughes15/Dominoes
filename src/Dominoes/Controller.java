package Dominoes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.Scanner;

//***********************************
// Ryan Hughes
//
// This class handles the management of all the lower classes
// and organizes the running of the game
//***********************************

public class Controller
{

  private Player player1, player2;
  private PlayerListener playerListener;
  private boolean computerWins, playerTurn;

  private Scanner in = new Scanner(System.in); //used for early  text testing

  /**
   * The constructor makes the method calls to initialize
   * the necessary variables and deal the initial hand
   * @param gc the GraphicsContext that will be passed to all
   *           visualization classes used to draw on the canvas
   */
  public Controller(GraphicsContext gc)
  {
    init(gc);
    deal();
  }

  /**
   * initializes all the necessary variables to start the game
   * @param gc the GraphicsContext passed to all visualization classes
   */
  private void init(GraphicsContext gc)
  {
    Boneyard boneyard = new Boneyard(gc);
    Board board = new Board(gc);
    player1 = new HumanPlayer(boneyard, board, gc);
    playerListener = new PlayerListener(player1, this);
    player2 = new ComputerPlayer(boneyard, board, gc);
    computerWins = false;
    playerTurn = true;
  }

  /**
   * This method deals dominos one at a time to each player until they
   * both have the INITIAL_HAND_SIZE, then displays all of the gui
   * elements
   */
  private void deal()
  {
    for(int i = 0; i < Display.INITIAL_HAND_SIZE; i++)
    {
      player1.draw();
      player2.draw();
    }
    player1.printHand(1);
    player2.printHand(2);
    player1.printBoard();
    player1.printBones();
  }

  /**
   * this method organizes the flow of the turns and
   * controls the game, checking for end state conditions
   * and ending the game when necessary
   */
  public void run()
  {
    if(gameOver()) end(player1.getGC());
    if(playerTurn)
    {
      playerTurn = false;
      player2.takeTurn();
      playerTurn = true;
    }
    if(gameOver()) end(player1.getGC());
  }

  /**
   * This method was supposed to reinitialize all the variables and start
   * a new game. It currently does not work and due to time constraints, will
   * only display a message in the console when the game is over and prompt for
   * input that doesn't do anything
   * @param gc the GraphicsContext that is supposed to be passed to the
   *           new instances of the game objects
   */
  private void end(GraphicsContext gc)
  {
    if(computerWins) System.out.println("Computer Wins!");
    else System.out.println("Congrats, You Won!");

    System.out.println("Would You Like to Play Again? Y or N");
    String choice = in.next();
    if(choice.equalsIgnoreCase("y"))
    {
      init(gc);
      deal();
    }
  }

  /**
   * This method checks to see if the game is over
   * @return true if the game is over, false if not
   */
  private boolean gameOver()
  {
    if(player1.isEmpty()) { return true; }
    else if(player2.isEmpty()) { return true; }
    else if(player1.emptyBones()) { return true; }
    return false;
  }

  /**
   * This method passes along the event handling information
   * to the PlayerListener
   * @param event the mouse event that occurred
   */
  public void handlePress(MouseEvent event)
  {
    if(playerTurn)
    {
      playerListener.handlePress(event);
    }
  }

  /**
   * This method passes along the event handling information
   * to the PlayerListener
   * @param event the mouse event that occurred
   */
  public void handleDrag(MouseEvent event)
  {
    if(playerTurn)
    {
      playerListener.handleDrag(event);
    }
  }

  /**
   * This method passes along the event handling information
   * to the PlayerListener
   * @param event the mouse event that occurred
   */
  public void handleRelease(MouseEvent event)
  {
    if(playerTurn)
    {
      playerListener.handleRelease(event);
    }
  }
}