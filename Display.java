import java.awt.*;
import java.awt.event.*;
 
import javax.swing.*;
 
 
/**
 * This class encompasses the gui, graphics, and the main method which will put
 * together all of the methods in order to run the game.
 * 
 *
 * @author Edward, Eric, Samir
 * @version May 26, 2015
 * @author Period: 4
 * @author Assignment: BattleShip Proj
 *
 * @author Sources: None
 */
public class Display
{
 
    /**
     * Human Player object
     */
    private static HumanPlayer myHuman = new HumanPlayer();
 
    /**
     * AI Player object
     */
    private static AIPlayer myAI = new AIPlayer();
 
    private static int x = 0;
 
    private static int y = 0;
 
    private static int rowInSecondGrid;
 
    private static int colInSecondGrid;
 
    private static final Color missedShot = Color.cyan;
 
    private static final Color hitShip = Color.red;
 
    private static final Color unHitShip = Color.green;
 
 
    /**
     * 
     * TODO Write a one-sentence summary of your class here. TODO Follow it with
     * additional details about its purpose, what abstraction it represents, and
     * how to use it.
     *
     * @author Edward, Eric, Samir
     * @version May 26, 2015
     * @author Period: 4
     * @author Assignment: BattleShip Proj
     *
     * @author Sources: None
     */
    private static class HelloWorldDisplay extends JPanel
    {
        /**
         * This method is called whenever repaint() is called and paints the
         * grid and labels.
         * 
         * @param g
         *            Graphics
         */
        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );
            g.setColor( Color.white );
            for ( int i = 0; i < 15; i++ )
            {
                create15SquaresInRow( 0, i, g );
            }
            for ( int index = 0; index < 15; index++ )
            {
                create215SquaresInRow( 0, index, g );
            }
            for ( int i = 0; i < 15; i++ )
            {
                emptyCreate15SquaresInRow( 0, i, g );
            }
            for ( int index = 0; index < 15; index++ )
            {
                emptyCreate215SquaresInRow( 0, index, g );
            }
            for ( int index = 0; index < 15; index++ )
            {
                emptyCreate215SquaresInRow( 0, index, g );
            }
            drawXNumLabelsBoxes( g );
            drawYNumLabelsBoxes( g );
            drawXNumLabelsBoxes2( g );
            drawYNumLabelsBoxes2( g );
            drawLabels( g );
        }
    }
 
 
    /**
     * This method uses recursion to create 15 squares in the same row by
     * constantly adding 1 to the column parameter and having the method exit
     * when the column reaches 15.
     * 
     * @param col
     *            Column of player's grid
     * @param row
     *            Row of player's grid
     * @param gr
     *            Graphics
     */
    public static void create15SquaresInRow( int col, int row, Graphics gr )
    {
        gr.setColor( Color.black );
        if ( col == 15 )
        {
            return;
        }
        if ( myHuman.getBoard().getCondition( row, col ) == 2 )
        {
            gr.setColor( unHitShip );
            gr.fillRect( 75 + col * 30, 150 + row * 30, 30, 30 );
        }
        else if ( myHuman.getBoard().getCondition( row, col ) == 1 )
        {
            gr.setColor( missedShot );
            gr.fillRect( 75 + col * 30, 150 + row * 30, 30, 30 );
        }
        else if ( myHuman.getBoard().getCondition( row, col ) == 3 )
        {
            gr.setColor( hitShip );
            gr.fillRect( 75 + col * 30, 150 + row * 30, 30, 30 );
        }
        gr.drawRect( 75 + col * 30, 150 + row * 30, 30, 30 );
        create15SquaresInRow( col + 1, row, gr );
    }
 
 
    /**
     * This method draws an extra column in the grid and labels that column with
     * the number of the row it is.
     * 
     * @param gr
     *            Graphics
     */
    public static void drawXNumLabelsBoxes( Graphics gr )
    {
        gr.setColor( Color.black );
        int i = 0;
        while ( i < 15 )
        {
            gr.drawRect( 45, 150 + i * 30, 30, 30 );
            gr.drawString( i + "", 54, 174 + i * 30 );
            i++;
        }
    }
 
 
    /**
     * This method labels each grid and it includes the word battleship in the
     * top middle of the gui.
     * 
     * @param gr
     */
    public static void drawLabels( Graphics gr )
    {
        gr.setColor( Color.black );
        Font bigBoldFont = new Font( "SansSerif", Font.BOLD, 24 );
        gr.setFont( bigBoldFont );
        gr.drawString( "Your Grid", 220, 650 );
        gr.drawString( "Opponent's Grid", 795, 650 );
        Font bigBoldFont2 = new Font( "Serif", Font.BOLD, 40 );
        gr.setFont( bigBoldFont2 );
        gr.drawString( "BattleShip", 490, 75 );
    }
 
 
    /**
     * This method draws an extra row in the grid and labels that row with the
     * number of the column it is.
     * 
     * @param gr
     *            Graphics
     */
    public static void drawYNumLabelsBoxes( Graphics gr )
    {
        gr.setColor( Color.black );
        int i = 0;
        while ( i < 16 )
        {
            gr.drawRect( 45 + i * 30, 120, 30, 30 );
            i++;
        }
        i = 0;
        while ( i < 15 )
        {
            gr.drawString( i + "", 84 + i * 30, 142 );
            i++;
        }
    }
 
 
    /**
     * This method draws over the previous grid in order to make each individual
     * square visible when the ships and squares next to each other are chosen
     * to provide clarity and detail.
     * 
     * @param col
     *            Column of player's grid
     * @param row
     *            Row of player's grid
     * @param gr
     *            Graphics
     */
    public static void emptyCreate15SquaresInRow( int col, int row, Graphics gr )
    {
        gr.setColor( Color.black );
        if ( col == 15 )
        {
            return;
        }
        gr.drawRect( 75 + col * 30, 150 + row * 30, 30, 30 );
        emptyCreate15SquaresInRow( col + 1, row, gr );
    }
 
 
    /**
     * This method uses recursion to create 15 squares in the same row by
     * constantly adding 1 to the column parameter and having the method exit
     * when the column reaches 15.
     * 
     * @param col
     *            Column of player's grid
     * @param row
     *            Row of player's grid
     * @param gr
     *            Graphics
     */
    public static void create215SquaresInRow( int col, int row, Graphics gr )
    {
        gr.setColor( Color.black );
        if ( col == 15 )
        {
            return;
        }
        if ( myAI.getBoard().getCondition( row, col ) == 1 )
        {
            gr.setColor( missedShot );
            gr.fillRect( 680 + col * 30, 150 + row * 30, 30, 30 );
        }
        else if ( myAI.getBoard().getCondition( row, col ) == 3 )
        {
            gr.setColor( hitShip );
            gr.fillRect( 680 + col * 30, 150 + row * 30, 30, 30 );
        }
        gr.drawRect( 680 + col * 30, 150 + row * 30, 30, 30 );
        create215SquaresInRow( col + 1, row, gr );
    }
 
 
    /**
     * This method draws an extra row in the grid and labels that row with the
     * number of the column it is.
     * 
     * @param gr
     *            Graphics
     */
    public static void drawXNumLabelsBoxes2( Graphics gr )
    {
        gr.setColor( Color.black );
        int i = 0;
        while ( i < 15 )
        {
            gr.drawRect( 650, 150 + i * 30, 30, 30 );
            gr.drawString( i + "", 659, 174 + i * 30 );
            i++;
        }
    }
 
 
    /**
     * This method draws an extra row in the grid and labels that row with the
     * number of the column it is.
     * 
     * @param gr
     *            Graphics
     */
    public static void drawYNumLabelsBoxes2( Graphics gr )
    {
        gr.setColor( Color.black );
        int i = 0;
        while ( i < 16 )
        {
            gr.drawRect( 650 + i * 30, 120, 30, 30 );
            i++;
        }
        i = 0;
        while ( i < 15 )
        {
            gr.drawString( i + "", 689 + i * 30, 142 );
            i++;
        }
    }
 
 
    /**
     * This method draws over the previous grid in order to make each individual
     * square visible when the ships and squares next to each other are chosen
     * to provide clarity and detail.
     * 
     * @param col
     *            Column of player's grid
     * @param row
     *            Row of player's grid
     * @param gr
     *            Graphics
     */
    public static void emptyCreate215SquaresInRow( int col, int row, Graphics gr )
    {
        gr.setColor( Color.black );
        if ( col == 15 )
        {
            return;
        }
        gr.drawRect( 680 + col * 30, 150 + row * 30, 30, 30 );
        emptyCreate215SquaresInRow( col + 1, row, gr );
    }
 
 
    // private static class ButtonHandler implements ActionListener {
    // public void actionPerformed(ActionEvent e) {
    // System.exit(0);
    // }
    // }
 
    /**
     * 
     * This class is used in order to utilize the mouselistener class so that we
     * may use the mouseClicked method and thus be able to react when a player
     * clicks on a square on the grid.
     *
     * @author Edward, Eric, Samir
     * @version May 26, 2015
     * @author Period: 4
     * @author Assignment: BattleShip Proj
     *
     * @author Sources: None
     */
    public static class RepaintOnClick implements MouseListener
    {
 
        /**
         * Called whenever the mouse clicks on a square on the grid.
         */
        @Override
        public void mouseClicked( MouseEvent e )
        {
            if ( !myHuman.didNotWin() || !myAI.didNotWin() )
            {
                if ( myHuman.didNotWin() )
                {
                    System.out.println( "The Computer Won! :(" );
                }
                else
                {
                    System.out.println( "You Won!!!! :)" );
                }
                System.exit( 0 );
            }
            x = e.getX();
            y = e.getY();
            Component source = (Component)e.getSource();
            colInSecondGrid = ( x - 680 ) / 30;
            rowInSecondGrid = ( y - 175 ) / 30;
            if ( rowInSecondGrid < 0 || rowInSecondGrid >= 15
                || colInSecondGrid < 0 || colInSecondGrid >= 15 )
            {
                System.out.println( "Out of Bounds" );
            }
            else
            {
                myHuman.shoot( rowInSecondGrid, colInSecondGrid );
                source.repaint();
                myAI.shoot();
                source.repaint();
            }
        }
 
 
        @Override
        public void mousePressed( MouseEvent e )
        {
            // TODO Auto-generated method stub
 
        }
 
 
        @Override
        public void mouseReleased( MouseEvent e )
        {
            // TODO Auto-generated method stub
 
        }
 
 
        @Override
        public void mouseEntered( MouseEvent e )
        {
            // TODO Auto-generated method stub
        }
 
 
        @Override
        public void mouseExited( MouseEvent e )
        {
            // TODO Auto-generated method stub
 
        }
 
    }
 
 
    /**
     * This is the main method for the Battleship program.
     * 
     * @param args
     *            Default
     */
    public static void main( String[] args )
    {
 
        myAI.setOpponent( myHuman );
        myHuman.setOpponent( myAI );
        myAI.makeBoard();
        myHuman.makeBoard();
        HelloWorldDisplay displayPanel = new HelloWorldDisplay();
        // JButton okButton = new JButton("OK");
        // ButtonHandler listener = new ButtonHandler();
        // okButton.addActionListener(listener);
 
        JPanel content = new JPanel();
        content.setLayout( new BorderLayout() );
        content.add( displayPanel, BorderLayout.CENTER );
        // content.add(okButton, BorderLayout.SOUTH);
 
        JFrame window = new JFrame( "Battleship" );
        RepaintOnClick listener = new RepaintOnClick();
        window.addMouseListener( listener );
        window.setContentPane( content );
        window.setSize( 1180, 800 );
        window.setLocation( 100, 100 );
        window.setVisible( true );
        window.setResizable( false );
    }
 
}
