/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The terms Pokemon, Charmander, Vulpix, and Magby are copyrighted
 * by Nintendo.  The images for these were found in the public domain.
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
/**
 * A specialized Pokemon of the fire type
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public abstract class FirePokemon extends AbstractPokemon
{
    private AbstractPokemon.Type type = AbstractPokemon.Type.FIRE_TYPE;
    private AbstractPokemon.Type weakness = AbstractPokemon.Type.WATER_TYPE;
    
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
