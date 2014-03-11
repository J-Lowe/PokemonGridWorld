/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The terms Pokeball and Greatball are copyrighted by Nintendo.
 */

/**
 * Holds a Greatball object.
 * 
 * @author Jonathan Lowe
 * @version 0.0.0
 */
public class Greatball extends Pokeball
{
    private int ballFactor;     //One factor used in the catch rate formula
    private int ballMod;        //Another factor used in the catch rate formula
    
    /**
     * Creates a Greatball object
     */
    public Greatball()
    {
        ballFactor = 8;
        ballMod = 200;
    }
    
    /**
     * Overrides the toString() method of the Object class
     */
    public String toString()
    {
        return "Greatball";
    }
}
