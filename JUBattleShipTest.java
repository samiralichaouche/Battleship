import static org.junit.Assert.*;
 
import org.junit.Test;
 
/**
 * 
 *  Testing Class
 *
 *  @author  Edward, Eric, Samir
 *  @version May 26, 2015
 *  @author  Period: 4
 *  @author  Assignment: BattleShip Proj
 *
 *  @author  Sources: None
 */
public class JUBattleShipTest
{
 
    @Test
    public void gridHitShip()
    {
        Grid gr = new Grid();
        gr.placeShip( 1, 0, 1, 5 );
        assertEquals( gr.hitShip( 0, 0 ), false );
        assertEquals( gr.hitShip( 1, 1 ), true );
 
    }
 
 
    @Test
    public void gridIsHit()
    {
        Grid gr = new Grid();
        gr.placeShip( 1, 0, 1, 5 );
        gr.hitShip( 1, 1 );
        assertEquals( gr.isHit( 1, 1 ), true );
        assertEquals( gr.isHit( 1, 2 ), false );
    }
 
 
    @Test
    public void gridIsMissedShot()
    {
        Grid gr = new Grid();
        gr.hitShip( 4, 4 );
        assertEquals( gr.isMissedShot( 0, 0 ), false );
        assertEquals( gr.isMissedShot( 4, 4 ), true );
 
    }
 
 
    @Test
    public void gridIsShipHere()
    {
        Grid gr = new Grid();
        gr.placeShip( 1, 0, 1, 5 );
        assertEquals( gr.isShipHere( 0, 0 ), false );
        assertEquals( gr.isShipHere( 1, 1 ), true );
 
    }
 
 
    @Test
    public void gridIsEmptySpot()
    {
        Grid gr = new Grid();
        gr.placeShip( 1, 0, 1, 5 );
        assertEquals( gr.isEmptySpot( 0, 0 ), true );
        assertEquals( gr.isEmptySpot( 1, 1 ), false );
 
    }
 
 
    @Test
    public void gridGetCondition()
    {
        Grid gr = new Grid();
        gr.placeShip( 1, 0, 1, 5 );
        gr.hitShip( 0, 0 );
        gr.hitShip( 1, 1 );
        assertEquals( gr.getCondition( 0, 0 ), 1 );
        assertEquals( gr.getCondition( 0, 1 ), 0 );
        assertEquals( gr.getCondition( 1, 1 ), 3 );
        assertEquals( gr.getCondition( 1, 2 ), 2 );
 
    }
 
 
    @Test
    public void AIMakeBoard()
    {
        Player ai = new AIPlayer();
        ai.makeBoard();
        int count = 0;
        for ( int x = 0; x < 15; x++ )
        {
            for ( int y = 0; y < 15; y++ )
            {
                if ( ai.getBoard().isShipHere( x, y ) )
                {
                    count++;
                }
            }
 
        }
        assertEquals( count, 37 );
    }
 
 
    @Test
    public void AIShoot()
    {
        AIPlayer ai = new AIPlayer();
        HumanPlayer hmn = new HumanPlayer();
        ai.setOpponent( hmn );
        for ( int x = 0; x < 60; x++ )
        {
            ai.shoot();
        }
        int count = 0;
        for ( int x = 0; x < 15; x++ )
        {
            for ( int y = 0; y < 15; y++ )
            {
                if ( hmn.getBoard().isMissedShot( x, y ) )
                {
                    count++;
                }
            }
        }
        assertEquals( count, 60 );
 
    }
 
 
    @Test
    public void AIRegisterHit()
    {
        AIPlayer ai = new AIPlayer();
        ai.makeBoard();
        ai.registerHit( 1, 1 );
        assertTrue( ai.getBoard().isHit( 1, 1 )
            || ai.getBoard().isMissedShot( 1, 1 ) );
    }
 
 
    @Test
    public void AISetOpponent()
    {
        AIPlayer ai = new AIPlayer();
        HumanPlayer human = new HumanPlayer();
        ai.setOpponent( human );
        assertNotNull( ai.human );
    }
 
 
    @Test
    public void HumanSetOpponent()
    {
        AIPlayer ai = new AIPlayer();
        HumanPlayer human = new HumanPlayer();
        human.setOpponent( ai );
        assertNotNull( human.opponent );
    }
 
 
    @Test
    public void HumanPlayerShoot()
    {
        HumanPlayer same = new HumanPlayer();
        AIPlayer ai = new AIPlayer();
        same.setOpponent( ai );
        ai.getBoard().placeShip( 1, 0, 1, 5 );
        same.shoot( 1, 1 );
        same.shoot( 3, 4 );
        assertEquals( ai.getBoard().isHit( 1, 1 ), true );
        assertEquals( ai.getBoard().isMissedShot( 3, 4 ), true );
    }
 
    // Remove block comment below to run JUnit test in console
    /*
     * public static junit.framework.Test suite() { return new
     * JUnit4TestAdapter( JUSafeTradeTest.class ); }
     * 
     * public static void main( String args[] ) {
     * org.junit.runner.JUnitCore.main( "JUSafeTradeTest" ); }
     */
 
}
