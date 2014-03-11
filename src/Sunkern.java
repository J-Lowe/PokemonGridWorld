
/**
 * Creates a grass type Pokemon called Sunkern
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Sunkern extends GrassPokemon
{
    /**
     * Creates a Sunkern Pokemon with 20 HP
     */
    public Sunkern()
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
        return "Sunkern";
    }
    
    /**
     * Returns the catch rate of the Pokemon according to Nintendo
     * 
     * @return The catch rate of the Pokemon
     */
    public int getCatchRate()
    {
        return 235;
    }
}
