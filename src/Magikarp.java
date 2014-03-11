
/**
 * Creates a water type Pokemon called Magikarp
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class Magikarp extends WaterPokemon
{
    /**
     * Creates a Magikarp Pokemon with 20 HP
     */
    public Magikarp()
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
        return "Magikarp";
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
