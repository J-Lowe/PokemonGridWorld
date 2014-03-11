
/**
 * Creates a fire type Pokemon called Charmander
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Charmander extends FirePokemon
{
    /**
     * Creates a Charmander Pokemon with 20 HP
     */
    public Charmander()
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
        return "Charmander";
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
