package Dominoes;

import javafx.scene.paint.Color;

public class Display
{
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;
  public static final int HAND_HEIGHT = 100;
  public static final int DOMINO_VBUFFER = 25;
  public static final int DOMINO_HBUFFER = 10;
  public static final int DOMINO_HEIGHT = HAND_HEIGHT - (DOMINO_VBUFFER * 2);
  public static final int DOMINO_WIDTH = 100;
  public static final int DOMINO_ARC_WIDTH = 10;
  public static final int DOMINO_ARC_HEIGHT = 10;
  public static final int INITIAL_HAND_SIZE = 7;
  public static final int BONEYARD_X = 10;
  public static final int BONEYARD_Y = (SCREEN_HEIGHT / 2) - (95 / 2) - 80;
  public static final int BONEYARD_WIDTH = 233;
  public static final int BONEYARD_HEIGHT = 95;
  public static final Color BACKGROUND_COLOR = Color.BLACK;
}
