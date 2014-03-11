
/**
 * Creates a grass type Pokemon called Oddish
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Oddish extends GrassPokemon
{
    /**
     * Creates an Oddish Pokemon with 20 HP
     */
    public Oddish()
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
        return "Oddish";
    }
    
    /**
     * Returns the catch rate of the Pokemon according to Nintendo
     * 
     * @return The catch rate of the Pokemon
     */
    public int getCatchRate()
    {
        return 255;
    }
}
