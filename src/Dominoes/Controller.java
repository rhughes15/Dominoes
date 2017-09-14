package Dominoes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.Scanner;

public class Controller
{

  private Player player1, player2;
  private PlayerListener playerListener;
  private boolean computerWins, playerTurn;

  private Scanner in = new Scanner(System.in); //used for early  text testing

  // constructor makes the calls to initialize all
  // the necessary variables, deals the first hand,
  // then starts the game
  public Controller(GraphicsContext gc)
  {
    init(gc);
    deal();
  }

  // initializes all the necessary variables
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

  // deals dominos one at a time to each player until they
  // both have the INITIAL_HAND_SIZE, then displays
  // all of the gui elements and calls run to start the game
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

  // handles the main game organization, called
  // after every turn
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

  // Gives the winner at the end of the game and
  // asks the player if they would like to play again.
  // If they do, reinitializes to clear out the old variables,
  // deals a new hand to each player, the starts the new game.
  // If they dont, does nothing.
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

  // simple method to determine if the game is over,
  // used in the main game loop.
  private boolean gameOver()
  {
    if(player1.isEmpty()) { return true; }
    else if(player2.isEmpty()) { return true; }
    else if(player1.emptyBones()) { return true; }
    return false;
  }

  public void handlePress(MouseEvent event)
  {
    if(playerTurn)
    {
      playerListener.handlePress(event);
    }
  }

  public void handleDrag(MouseEvent event)
  {
    if(playerTurn)
    {
      playerListener.handleDrag(event);
    }
  }

  public void handleRelease(MouseEvent event)
  {
    if(playerTurn)
    {
      playerListener.handleRelease(event);
    }
  }
}