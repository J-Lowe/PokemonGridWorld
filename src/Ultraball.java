/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The term Pokeball and Ultraball are copyrighted by Nintendo.
 */

/**
 * Holds an Ultraball object.
 * 
 * @author Jonathan Lowe
 * @version 0.0.0
 */
public class Ultraball extends Pokeball
{
    private int ballFactor;     //One factor used in the catch rate formula
    private int ballMod;        //Another factor used in the catch rate formula
    
    /**
     * Creates an Ultraball object
     */
    public Ultraball()
    {
        ballFactor = 4;
        ballMod = 150;
    }
    
    /**
     * Overrides the toString() method of the Object class
     */
    public String toString()
    {
        return "Ultraball";
    }
}
