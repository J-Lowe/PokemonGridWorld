/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The terms Pokemon, Bulbasaur, Sunkern, and Oddish are copyrighted
 * by Nintendo.  The images for these were found in the public domain.
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
/**
 * A more specialized Pokemon of the grass type
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public abstract class GrassPokemon extends AbstractPokemon
{
    private AbstractPokemon.Type type = AbstractPokemon.Type.GRASS_TYPE;
    private AbstractPokemon.Type weakness = AbstractPokemon.Type.FIRE_TYPE;
    
    /**
     * Returns the Pokemon type.
     * 
     * @return Type the Pokemon type
     */
    public Type getType()
    {
        return type;
    }
    
    /**
     * Returns the Pokemon's weakness.
     * 
     * @return Type the Pokemon's weakness
     */
    public Type getWeakness()
    {
        return weakness;
    }
}
