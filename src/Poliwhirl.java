
/**
 * Creates a water type Pokemon called Poliwhirl
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Poliwhirl extends WaterPokemon
{
    /**
     * Creates a Poliwhirl Pokemon with 20 HP
     */
    public Poliwhirl()
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
        return "Poliwhirl";
    }
    
    /**
     * Returns the catch rate of the Pokemon according to Nintendo
     * 
     * @return The catch rate of the Pokemon
     */
    public int getCatchRate()
    {
        return 120;
    }
}
