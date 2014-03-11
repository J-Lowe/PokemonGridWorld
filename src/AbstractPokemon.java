/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The terms Pokemon and Pokemon Trainer are copyrighted by 
 * Nintento.  The images of these items were found in the
 * public domain.
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
/**
 * An abstract class used for the general methods of all Pokemon.
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public abstract class AbstractPokemon extends Critter
{
    public enum Type {FIRE_TYPE, WATER_TYPE, GRASS_TYPE, ANY_TYPE};
    private Type weakness;
    private Type type;
    private Random generator = new Random();
    private final int CRITICAL_HIT_LEVEL = 91;
    private static final int MAX_HIT_POINTS = 20;
    private int currentHitPoints;
    private Location nextLocation;
    
    /**
     * Makes the Pokemon act on the grid.
     */
    public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Location> moveLocs = getMoveLocations();
        nextLocation = selectMoveLocation(moveLocs);
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        if (getGrid() == null)
            return;
        makeMove(nextLocation);
    }
    
    /**
     * Gets a list of valid move Locations.  Valid move Locations
     * are any Locations that are on the grid and are either
     * null, a wild Pokemon, or another Trainer.
     * 
     * @return The list of valid move Locations
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        ArrayList<Location> validLocations = getGrid().getValidAdjacentLocations(getLocation());
        for (Location neighborLoc : validLocations)
        {
            if (getGrid().get(neighborLoc) == null || getGrid().get(neighborLoc) instanceof AbstractPokemon || getGrid().get(neighborLoc) instanceof PokemonTrainer)
                locs.add(neighborLoc);
        }
        return locs;
    }
    
    /**
     * Returns a "list" of the Actor that is in the location
     * that this Pokemon wants to move to.
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> pokemonList = new ArrayList<Actor>();
        Location frontLocation = getLocation().getAdjacentLocation(getLocation().getDirectionToward(nextLocation));
        if(getGrid().isValid(frontLocation))
        {
            if(getGrid().get(frontLocation) instanceof AbstractPokemon || getGrid().get(frontLocation) instanceof PokemonTrainer)
                pokemonList.add(getGrid().get(frontLocation));
        }
        return pokemonList;
    }
    
    /**
     * Overides the processActors method of the Critter class.
     * Battles the Actor in front of this Pokemon if there is one
     * and the Actor meets the requirements above.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        if(actors.size() == 0)
            return;
        battle(actors.get(0));
    }
    
    /**
     * Battles another AbstractPokemon or a PokemonTrainer.
     * Precondition: other must be an AbstractPokemon or a PokemonTrainer
     * 
     * @param other The Actor this AbstractPokemon will fight
     */
    public void battle(Actor other)
    {
        if (other instanceof AbstractPokemon)
        {
            AbstractPokemon otherPokemon = (AbstractPokemon) other;
            System.out.println(otherPokemon.toString() + " has " + otherPokemon.getHealth() + " HP.");
            System.out.println(toString() + " has " + getHealth() + " HP.");
            while (otherPokemon.getHealth() > 0 && getHealth() > 0)
            {
                otherPokemon.takeDamage(this);
                if(otherPokemon.getHealth() < 0)
                    otherPokemon.setHealth(0);
                System.out.println(otherPokemon.toString() + " has " + otherPokemon.getHealth() + " HP.");
                if(otherPokemon.getHealth() > 0)
                {
                    takeDamage(otherPokemon);
                    if(getHealth() < 0)
                        setHealth(0);
                    System.out.println(toString() + " has " + getHealth() + " HP.");
                }
            }
            if (otherPokemon.getHealth() <= 0)
            {
                System.out.println(otherPokemon.toString() + " has fainted!");
                otherPokemon.removeSelfFromGrid();
                setHealth(20);
            }
            else
            {
                System.out.println(toString() + " has fainted!");
                removeSelfFromGrid();
                otherPokemon.setHealth(20);
            }
            System.out.println();
        }
        else
        {
            PokemonTrainer trainer = (PokemonTrainer) other;
            trainer.battle(this);
        }
    }
    
    /**
     * A method used to calculate the damage taken during one
     * move of a battle.
     * 
     * @param other The other Pokemon that this Pokemon is battling.
     */
    public void takeDamage(AbstractPokemon other)
    {
        int critical = generator.nextInt(100);
        int damage = generator.nextInt(6);
        if (critical > CRITICAL_HIT_LEVEL)
            damage = damage * 2;
        if (other.getType() == getWeakness())
            damage = damage * 2;
        else if (other.getType() != getType())
            damage = damage / 2;
        int newHitPoints = currentHitPoints - damage;
        setHealth(newHitPoints);
    }
    
    /**
     * Sets the Pokemon's currentHealth
     * 
     * @param h The new HP level of this Pokemon
     */
    public void setHealth(int h)
    {
        if(h > MAX_HIT_POINTS)
            h = MAX_HIT_POINTS;
        currentHitPoints = h;
    }
    
    /**
     * Returns the current HP of this Pokemon
     * 
     * @return The current HP of this Pokemon
     */
    public int getHealth()
    {
        return currentHitPoints;
    }
    
    /**
     * Returns wether or not this Pokemon is below 20%
     * of its maximum HP.
     * 
     * @return True if the Pokemon's HP is below 20% of the max, false if not
     */
    public boolean canCapture()
    {
        int captureHitPoints = (MAX_HIT_POINTS / 10) * 2;
        return (currentHitPoints <= captureHitPoints && currentHitPoints != 0);
    }
    
    /**
     * Returns the Pokemon type.
     * 
     * @return Type the Pokemon type
     */
    public abstract Type getType();
    
    /**
     * Returns the Pokemon's weakness.
     * 
     * @return Type the Pokemon's weakness
     */
    public abstract Type getWeakness();
    
    /**
     * Returns the Pokemon's maximum HP
     * 
     * @return The maximum HP ths Pokemon can have
     */
    public int getMaxHealth()
    {
        return MAX_HIT_POINTS;
    }
    
    /**
     * Returns the catch rate of the Pokemon according to Nintendo
     * 
     * @return The catch rate of the Pokemon
     */
    public abstract int getCatchRate();
    
    /**
     * Returns the name of the Pokemon
     * 
     * @return The name of the Pokemon
     */
    public abstract String toString();
}
