import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.*;
import javax.swing.*;
/**
 * Creates a GridWorld full of Pokemon
 * 
 * @author Jonathan Lowe
 * @version 1.2.0
 */
public class PokemonRunner
{
    /**
     * Asks the user to choose a blank world or a randomly
     * generated world.  If the user selects a randomly
     * generated world, the program creates one of each of 
     * the nine Pokemon as well as a trainer who has one 
     * of each type of Pokemon and places them randomly on 
     * the grid.
     */
    public static void main(String[] args)
    {
        Pokeball pokeball = new Pokeball();
        Pokeball greatball = new Greatball();
        Pokeball ultraball = new Ultraball();
        Pokeball masterball = new Masterball();
        ArrayList<AbstractPokemon> ashPokemon = new ArrayList<AbstractPokemon>();
        ashPokemon.add(new Charmander());
        ashPokemon.add(new Squirtle());
        ashPokemon.add(new Bulbasaur());
        PokemonTrainer ash = new PokemonTrainer("Ash", ashPokemon, AbstractPokemon.Type.ANY_TYPE, masterball);
        ActorWorld world = new ActorWorld();
        AbstractPokemon charmander = new Charmander();
        AbstractPokemon squirtle = new Squirtle();
        AbstractPokemon bulbasaur = new Bulbasaur();
        AbstractPokemon vulpix = new Vulpix();
        AbstractPokemon poliwhirl = new Poliwhirl();
        AbstractPokemon sunkern = new Sunkern();
        AbstractPokemon magby = new Magby();
        AbstractPokemon magikarp = new Magikarp();
        AbstractPokemon oddish = new Oddish();
        JOptionPane worldSelector = new JOptionPane();
        String[] worldChoices = {"Randomly Generated", "Blank", "Cancel"};
        int worldSelection = worldSelector.showOptionDialog(null, "Please select the world template:", "World Selection",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, worldChoices, worldChoices[0]);
        if (worldSelection == 2)
        {
            worldSelector.showMessageDialog(null, "No world chosen.  Program will terminate.", "World Selection", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        else if (worldSelection == 0)
        {
            spawn(charmander, world);
            spawn(squirtle, world);
            spawn(bulbasaur, world);
            spawn(vulpix, world);
            spawn(poliwhirl, world);
            spawn(sunkern, world);
            spawn(magby, world);
            spawn(magikarp, world);
            spawn(oddish, world);
            spawn(ash, world);
        }
        world.show();
    }
    
    /**
     * Places the Actor at a randomly generated location
     * 
     * @param actor The Actor to be placed in the ActorWorld
     * @param world The ActorWorld to place the Actor in
     */
    private static void spawn(Actor actor, ActorWorld world)
    {
        Random generator = new Random();
        int row = generator.nextInt(10);
        int col = generator.nextInt(10);
        Location loc = new Location(row, col);
        while(world.getGrid().get(loc) != null)
        {
            row = generator.nextInt(10);
            col = generator.nextInt(10);
            loc = new Location(row, col);
        }
        world.add(loc, actor);
    }
}