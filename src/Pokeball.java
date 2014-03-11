/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The term Pokeball is copyrighted by Nintendo.
 */

/**
 * Holds a Pokeball object.
 * 
 * @author Jonathan Lowe
 * @version 0.0.0
 */
public class Pokeball
{
    private int ballFactor;     //One factor used in the catch rate formula
    private int ballMod;        //Another factor used in the catch rate formula
    
    /**
     * Creates a Pokeball object.
     */
    public Pokeball()
    {
        ballFactor = 12;
        ballMod = 255;
    }
    
    /**
     * Returns the ball factor of the Pokeball
     * @return        the ball factor
     */
    public int getFactor()
    {
        return ballFactor;
    }
    
    /**
     * Returns the ball modification of the Pokeball
     * @return        the ball modification
     */
    public int getMod()
    {
        return ballMod;
    }
    
    /**
     * Overrides the toString() method of the Object class
     */
    public String toString()
    {
        return "Pokeball";
    }
}
