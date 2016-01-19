/**
 *  The Human Player Class controls the human grid, sets up the board, and 
 *  handles the shooting of the other player.
 *
 *  @author  Eric Liu, Samir Ali-Chaouche, Edward Li
 *  @version May 22, 2015
 *  @author  Period: 4
 *  @author  Assignment: BattleShip
 *
 *  @author  Sources: None
 */
public class HumanPlayer extends Player
{
    int [] sizeShip = {2,2,3,3,3,4,4,5,5,6};
    Grid myGrid;
    PlayerWindow myWindow;
    Player opponent;
    /**
     * Constructor for the Human player which creates an opponent and sets up
     * an empty grid.
     */
    public HumanPlayer()
    {
        myWindow = new PlayerWindow();
        opponent = null;
        myGrid = new Grid();
         
    }
    /**
     * Sets the opposing AI player
     * @param opp the AI
     */
    public void setOpponent(AIPlayer opp)
    {
        opponent = opp;
    }
    /** 
     * Prompts the player for coordinates and then places the ships down on 
     * the grid for the player.
     */
    @Override
    public void makeBoard()
    {
        int[] locations = myWindow.getPosition();
        int j = 0;
        for( int x = 0; x<sizeShip.length;x++)
        {
            myGrid.placeShip( locations[j], locations[j+1], locations[j+2], locations[j+3] );
            j = j +4;
        }
    }
 
    /**
     * Asks for the shot from the human input in the display class and uses it 
     * to shoot at the opposing AI.
     */
    public void shoot( int row, int col)
    {
        opponent.registerHit(row, col);
         
    }
    /** 
     * Gets the grid of the AI player.
     * @return the grid of the AI player
     */
    @Override
    public Grid getBoard()
    {
        return myGrid;
         
    }
 
    /** 
     * Registers the shot of the opposing player on the AI's board.
     */
    @Override
    public boolean registerHit(int r, int c)
    {
        return myGrid.hitShip( r, c );
    }
    /**
     * Checks whether or not the user has won the game 
     * @return If player did not win or not
     */
    public boolean didNotWin()
    {
        for(int x =0; x<15;x++)
        {
            for(int y =0; y<15;y++)
            {
                if(myGrid.isShipHere( x, y ))
                {
                    return true;
                }
            }
        }
        return false;
    }
 
 
}
