
/**
 * Creates a fire type Pokemon called Vulpix
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Vulpix extends FirePokemon
{
    /**
     * Creates a Vulpix Pokemon with 20 HP
     */
    public Vulpix()
    {
        setHealth(20);
        setColor(null);
    }
    
    /**
     * Returns the name of the Pokemon
     * 
     * @return The name of the Pokemon
     */
    public String toString()
    {
        return "Vulpix";
    }
    
    /**
     * Returns the catch rate of the Pokemon according to Nintendo
     * 
     * @return The catch rate of the Pokemon
     */
    public int getCatchRate()
    {
        return 190;
    }
}
