import java.io.*;
 
import javax.swing.JOptionPane;
 
import java.util.Scanner;
 
 
/**
 * Ask the user to input the starting positions and the end positions of the ships
 * in order to place them on the grid. We take those positions and then put it into an Array.
 * The playerWindow also makes sure the ships are placed within the boundaries of the grid      and
 * that the length placements are accurate according to the ship size. 
 *
 *  @author  Edward Li, Eric Liu, Samir Ali-Chaouche
 *  @version May 26, 2015
 *  @author  Period: 4
 *  @author  Assignment: BattleShip Proj
 *
 *  @author  Sources: None
 */
public class PlayerWindow
{
    int [] sizeShip = {2,2,3,3,3,4,4,5,5,6};
    int [] placeShip = new int[40];
    public void setUp()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter position of ship starting with x-coordinate followed by a space then the y-coordinate");
        int j = 0;
        for(int i = 0; i < sizeShip.length;)
        {
            System.out.println( "Enter the starting position of your ship of size " + sizeShip[i]);
            String index = scan.nextLine();
            index = index.trim();
            System.out.println( "Enter the final position of your ship of size " + sizeShip[i]);
            String fin = scan.nextLine();
            fin = fin.trim();
            String startIndex = index.substring(0, index.indexOf(" ")); //gives us substring of line1
            String endIndex = index.substring(index.indexOf(" ")+1, index.length()); // gives us the last part of line1
            int positionX = Integer.parseInt(startIndex); //take the parseInt of the line1
            int positionY = Integer.parseInt(endIndex); //take the parseInt of line1
            String finXIndex = fin.substring(0, fin.indexOf(" "));
            String finYIndex = fin.substring(fin.indexOf(" ")+1, fin.length());
            int positionFinX = Integer.parseInt(finXIndex); 
            int positionFinY = Integer.parseInt(finYIndex);
//            System.out.println(positionX + " " + positionY + " " + positionFinX + " " + positionFinY);
            if((positionX > 0 && positionX < 15) && (positionY > 0 && positionY < 15) 
                    && (positionFinX > 0 &&  positionFinX < 15) && (positionFinY >0 && positionFinY < 15))
            {
                if(((positionX != positionFinX  && positionY == positionFinY) || (positionX == positionFinX && positionY != positionFinY)) 
                        &&( (Math.abs(positionX - positionFinX) == sizeShip[i] - 1)|| (Math.abs(positionY - positionFinY) ==sizeShip[i] -1)))
                {
                    placeShip[j] = positionX;
                    placeShip[j+1] = positionY;
                    placeShip[j+2] = positionFinX;
                    placeShip[j+3] = positionFinY;
                    j = j+4;
                    i++;
                }
                else
                {
                    System.out.println("Your ship is not of that length please try again");
                }
            }
            else
            {
                System.out.println("Your ship is not in the grid. Please enter it within 0 to 15.");
            }
        }
         
    }
 
 
    /**
     * returns the placeShip array which is 
     * then used and implemented by another class
     * @return placeShip array
     */
 
public int[] getPosition()
    {
         
        return placeShip;
 
     
    }
}
