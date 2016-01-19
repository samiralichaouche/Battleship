/**
 *  The AI of the BattleShip game. The AI sets up the board randomly and uses
 *  a basic algorithm to find the best places to shoot and shooting at the 
 *  human player.
 *
 *  @author  Edward, Eric, Samir
 *  @version May 22, 2015
 *  @author  Period: 4
 *  @author  Assignment: BattleShip
 *
 *  @author  Sources: None
 */
public class AIPlayer extends Player
{
    int[] sizes = { 2, 2, 3, 3, 3, 4, 4, 5, 5, 6 };
    int direction = 0;// 0 for horizontal, 1 for vertical
    int mode = 0;
    Grid aiGrid;
    Player human;
    boolean chase = false;
    int chaseRow;
    int chaseCol;
    /**
     * Creates a new AI Player with opponent set to null and with a new grid.
     */
    public AIPlayer()
    {
        human = null;
        aiGrid = new Grid();
    }
 
 
    /**
     * Sets the player that is to be the opponent of the AI
     * 
     * @param hmn
     *            the human opponent.
     */
    public void setOpponent( HumanPlayer hmn )
    {
        human = hmn;
    }
 
 
    /**
     * Randomly creates a board full of battleships. The direction and location
     * of each ship is generated randomly.
     */
    @Override
    public void makeBoard()
    {
        for ( int x = 0; x < sizes.length; x++ )
        {
            direction = (int)( Math.random() * 2 );           
            if ( direction == 0 )
            {
                int row = (int)( Math.random() * 15 );
                int col = (int)( Math.random() * (15-sizes[x]) );
                 
                int c;
                for ( c = col; c < col + sizes[x]; c++ )
                {
                    if ( aiGrid.isShipHere( row, c ) )
                    {
                        x--;
                        c= 0;
                        break;
                    }
                }
                if ( c == col + sizes[x] )
                {
                    aiGrid.placeShip( row, col, row, col + sizes[x]-1 );
                }
            }
            if ( direction == 1 )
            {
                int row = (int)( Math.random() * (15-sizes[x]) );
                int col = (int)( Math.random() * 15 );
                int r;
                for ( r = row; r < row + sizes[x]; r++ )
                {
                    if ( aiGrid.isShipHere( r, col ) )
                    {
                        x--;
                        r= 0;
                        break;
                    }
                }
                if ( r == row + sizes[x] )
                {
                    aiGrid.placeShip( row, col, row + sizes[x]-1, col );
                }
            }
        }
 
    }
 
 
    /**
     * Uses an algorithm to calculate the next best shot and then takes it. The
     * AI operates in two modes random mode, in which shots are made randomly
     * and chase mode, which is turned on after a shot in random mode is
     * registered. In chase mode, the AI searches horizontally and vertically to
     * eliminate the ships in the area.
     */
    public void shoot()
    {
        if ( chase == false )
        {
            int row = (int)( Math.random() * 15 );
            int col = (int)( Math.random() * 15 );
            while ( human.getBoard().isHit( row, col )
                || human.getBoard().isMissedShot( row, col ) )
            {
                row = (int)( Math.random() * 15 );
                col = (int)( Math.random() * 15 );
            }
            if ( human.registerHit( row, col ) )
            {
                chaseRow = row;
                chaseCol = col;
                chase = true;
            }
        }
        else
        {
            int row = chaseRow;
            int col = chaseCol;
            switch ( mode )
            {
                case 0:
                    while ( human.getBoard().isHit( row, col )
                        || human.getBoard().isMissedShot( row, col ))
                    {
                        col++;    
                        if(col>=15)
                        {
                            break;
                        }
                    }
                    if(col>=15)
                    {
                        mode = 1;
                        shoot();
                        break;
                    }
                    else if ( !human.registerHit( row, col ) )
                    {
                        mode = 1;
                    }
                    break;
                case 1:
                    while ( human.getBoard().isHit( row, col )
                        || human.getBoard().isMissedShot( row, col ) )
                    {
                        row++;
                        if(row>=15)
                        {
                            break;
                        }
                    }
 
                    if(row>=15)
                    {
                        mode =2;
                        shoot();
                        break;
                    }
                    else if ( !human.registerHit( row, col ) )
                    {
                        mode = 2;
                    }
                    break;
                case 2:
                    while ( human.getBoard().isHit( row, col )
                        || human.getBoard().isMissedShot( row, col ) )
                    {
                         
                        col--;
                        if(col<0)
                        {
                            break;
                        }
                    }
                    if(col<0)
                    {
                        mode = 3;
                        shoot();
                        break;
                    }
                    else if ( !human.registerHit( row, col ) )
                    {
                        mode = 3;
                    }
                    break;
                case 3:
                    while (human.getBoard().isHit( row, col )
                        || human.getBoard().isMissedShot( row, col ) )
                    {                     
                        row--;
                        if(row<0)
                        {
                            break;
                        }
                    }
                    if(row<0)
                    {
                        chase = false;
                        mode = 0;
                        shoot();
                        break;
                    }
                    else if ( !human.registerHit( row, col ) )
                    {
                        chase = false;
                        mode = 0;
                    }
                    break;
            }
        }
 
    }
 
 
    /**
     * Gets the grid of the AI player.
     * 
     * @return the grid of the AI player
     */
    @Override
    public Grid getBoard()
    {
        return aiGrid;
 
    }
 
 
    /**
     * Registers the shot of the opposing player on the AI's board.
     */
    @Override
    public boolean registerHit( int r, int c )
    {
        return aiGrid.hitShip( r, c );
 
    }
 
 
    /**
     * Tells the game whether or not the AI has won yet.
     * 
     * @return true if the AI hasn't won and the game is continuing false if the
     *         AI has won
     */
    public boolean didNotWin()
    {
        for ( int x = 0; x < 15; x++ )
        {
            for ( int y = 0; y < 15; y++ )
            {
                if ( aiGrid.isShipHere( x, y ) )
                {
                    return true;
                }
            }
        }
        return false;
    }
 
}
