/**
 * The Grid that represents the playing field of the user. There are 225 squares
 * each with a corresponding number, 0 means empty space, 1 means missed shot, 2
 * means ship here, 3 means hit ship, and (4 means un hit ship?).
 *
 * @author Eric Liu / Samir Ali-Chaouche/Edward Li
 * @version May 11, 2015
 * @author Period: 4
 * @author Assignment: BattleShip
 *
 * @author Sources: None
 */
 
public class Grid
{
    private int[][] battleGrid = new int[15][15];
 
    private final static int emptySpace = 0;
 
    private final static int missedShot = 1;
 
    private final static int shipHere = 2;
 
    private final static int hitShip = 3;
 
 
    /**
     * fills the grid with empty space which is 
     * the int 0
     */
    public Grid()
    {
        for ( int i = 0; i < battleGrid.length; i++ )
        {
            for ( int j = 0; j < battleGrid[0].length; j++ )
            {
                battleGrid[i][j] = emptySpace;
            }
        }
    }
 
 
    /**
     * Puts the ships in each position and
     * makes the grid hold the int 2 which represents 
     * a ship there. 
     * @param row 
     *      the starting row 
     * @param col 
     *      the starting col
     * @param fRow 
     *      the final row
     * @param fCol 
     *      the final col
     */
    public void placeShip( int row, int col, int fRow, int fCol )
    {
      if ( row != fRow )
        {
            for ( int i =row; i <= fRow; i++ )
            {
                battleGrid[i][col] = shipHere;
            }
       
        }
        if ( col != fCol )
        {
            for ( int i = col; i<=fCol; i++ )
            {
                battleGrid[row][i] = shipHere;
            }
       
        }
 
 
    }
 
 
    /**
     * Checks if there is a ship or an empty space or a missed shot at that position
     * and returns true if the shot hit a ship if a ship was there. 
     * @param row 
     *      the x value of the shot
     * @param col
     *      the y value of the shot
     * @return
     *      returns if the ship is hit or not
     */
    public boolean hitShip( int row, int col )
    {
        boolean bool = false;
        if ( battleGrid[row][col] == emptySpace )
        {
            battleGrid[row][col] = missedShot;
            bool = false;
        }
        if ( battleGrid[row][col] == shipHere )
        {
            battleGrid[row][col] = hitShip;
            bool = true;
        }
        return bool;
    }
 
 
    /**
     * Returns true if there is a hit ship on that position on the grid
     * @param row
     *      the x value
     * @param col
     *      the y value
     * @return
     *      returns if there was a hit Ship on that square in the grid
     */
    public boolean isHit( int row, int col )
    {
        if ( battleGrid[row][col] == hitShip )
        {
            return true;
        }
        return false;
    }
 
 
    /**
     * If there is a missed shot there then there should be 
     * an integer 1 on that square.
     * @param row
     *      the x value
     * @param col
     *      the y value
     * @return
     *      returns true if that square has a missed shot
     */
    public boolean isMissedShot( int row, int col )
    {
 
        return battleGrid[row][col] == missedShot;
 
    }
 
 
    /**
     * If there is a ship on that coordinate on the grid
     * then we return true else we return false.
     * @param row
     *      the x value
     * @param col
     *      the y value
     * @return
     *      returns true if there is a ship placed there
     */
    public boolean isShipHere( int row, int col )
    {
        if ( battleGrid[row][col] == shipHere )
        {
            return true;
        }
        return false;
    }
 
 
    /**
     * If there is a empty space on that coordinate in the grid
     * then it returns true else returns false.
     * @param row
     *      the x value
     * @param col
     *      the y value
     * @return
     *      returns true if there is a emptySpace
     */
    public boolean isEmptySpot( int row, int col )
    {
        if ( battleGrid[row][col] == emptySpace )
        {
            return true;
        }
        return false;
    }
 
 
    /**
     * Gets the final condition of the square on the grid
     * 
     * @param row
     *      the x value
     * @param col
     *      the y value
     * @return
     *      returns the int value on the battlegrid 
     *      which tells us the status of that square
     */
    public int getCondition( int row, int col )
    {
        return battleGrid[row][col];
    }
 
    // public static void main(String [ ] args)
    // {
    // System.out.println();
    // }
 
}
