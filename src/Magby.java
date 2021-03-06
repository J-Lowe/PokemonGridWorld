
/**
 * Creates a fire type Pokemon called Magby
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Magby extends FirePokemon
{
    /**
     * Creates a Magby Pokemon with 20 HP
     */
    public Magby()
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
        return "Magby";
    }
    
    /**
     * Returns the catch rate of the Pokemon according to Nintendo
     * 
     * @return The catch rate of the Pokemon
     */
    public int getCatchRate()
    {
        return 45;
    }
}
