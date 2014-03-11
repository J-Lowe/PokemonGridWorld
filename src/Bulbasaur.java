
/**
 * Creates a grass type Pokemon called Bulbasaur
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Bulbasaur extends GrassPokemon
{
    /**
     * Creates a Bulbasaur Pokemon with 20 HP
     */
    public Bulbasaur()
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
        return "Bulbasaur";
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
