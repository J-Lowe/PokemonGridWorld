/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The term Pokeball and Masterball are copyrighted by Nintendo.
 */

/**
 * Holds a masterball object.
 * 
 * @author Jonathan Lowe
 * @version 0.0.0
 */
public class Masterball extends Pokeball
{
    private int ballFactor;     //One factor used in the catch rate formula
    private int ballMod;        //Another factor used in the catch rate formula
    
    /**
     * Creates a Masterball object
     */
    public Masterball()
    {
        ballFactor = 1;
        ballMod = 0;
    }
    
    /**
     * Overrides the toString() method of the Object class
     */
    public String toString()
    {
        return "Masterball";
    }
}
