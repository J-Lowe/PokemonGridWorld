/**
 * Acknowledgements: 
 * 
 * On my honor I have neither given nor recieved aid on this Lab.
 * 
 * The formula for capturing wild Pokemon is derived from the "formula"
 * used by Nintendo in the first generation of Pokemon games (Pokemon
 * Red and Pokemon Blue).  I did not come up with this formula on my
 * own.  The terms Pokemon, Pokeball, Pokemon Trainer, and PokeCenter
 * are copyrighted by Nintento as well.  The images of these items
 * (if there is one) were found in the public domain.
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * An actor that battles and captures Pokemon critters.
 * 
 * @author Jonathan Lowe
 * @version 1.0.0
 */
public class PokemonTrainer extends Actor
{
    private ArrayList<AbstractPokemon> caughtPokemon;
    private AbstractPokemon.Type faveType;
    private Pokeball pokeball;
    private JFrame selectionScreen = new JFrame();
    private Random generator = new Random();
    private Selector selector = new Selector();
    private String name;
    
    /**
     * Createa a customizable Pokemon Trainer for use in the GUI
     * component of GridWorld
     */
    public PokemonTrainer()
    {
        name = selector.selectName();
        faveType = selector.selectType();
        pokeball = selector.selectBall();
        caughtPokemon = selector.selectPokemon();
        setColor(null);
    }
    
    /**
     * Createa a Pokemon Trainer that will capture any type of Pokemon
     * and that has a list of Pokemon
     * 
     * @param name The name of this trainer
     * @param pokemonList The lsit of starter Pokemon for this trainer
     */
    public PokemonTrainer(String name, ArrayList<AbstractPokemon> pokemonList)
    {
        faveType = AbstractPokemon.Type.ANY_TYPE;
        pokeball = new Pokeball();
        caughtPokemon = pokemonList;
        this.name = name;
        setColor(null);
    }
    
    /**
     * Creates a Pokemon Trainer that will capture a certain type of Pokemon
     * and has a certain starter Pokemon
     * 
     * @param name The name of this trainer
     * @param pokemonList The list of starter Pokemon of this trainer
     * @param type The type of Pokemon this trainer is willing to capture
     */
    public PokemonTrainer(String name, ArrayList<AbstractPokemon> pokemonList, AbstractPokemon.Type type)
    {
        faveType = type;
        pokeball = new Pokeball();
        caughtPokemon = pokemonList;
        this.name = name;
        setColor(null);
    }
    
    /**
     * Creates a Pokemon Trainer that will capture a certain type of Pokemon,
     * has a certain starter Pokemon, and uses a certain type of Pokeball
     * 
     * @param name The name of this trainer
     * @param pokemonList The list of starter Pokemon of this trainer
     * @param type The type of Pokemon this trainer is willing to capture
     * @param pb The type of Pokeball this trainer uses
     */
    public PokemonTrainer(String name, ArrayList<AbstractPokemon> pokemonList, AbstractPokemon.Type type, Pokeball pb)
    {
        faveType = type;
        pokeball = pb;
        caughtPokemon = pokemonList;
        this.name = name;
        setColor(null);
    }
    
    /**
     * Returns the list of Pokemon that this trainer has caught
     * 
     * @return The list of Pokemon that this trainer has caught
     */
    public ArrayList<AbstractPokemon> getPokemon()
    {
        return caughtPokemon;
    }
    
    /**
     * Use to battle a wild Pokemon.  The battle continues as long
     * as both Pokemon (the trainer's and the wild Pokemon) have 
     * some hit points left.  If the wild Pokemon can be captured,
     * and the trainer is willing to capture the wild Pokemon,
     * the trainer will throw a pokeball.  If the wild Pokemon is
     * captured, then the battle stops as well.
     * 
     * @param otherPokemon The wild Pokemon to be battled
     */
    public void battle(AbstractPokemon otherPokemon)
    {
        boolean capturedPokemon = false;
        AbstractPokemon myPokemon = caughtPokemon.get(0);
        while(caughtPokemon.size() > 0 && otherPokemon.getHealth() > 0 && myPokemon.getHealth() > 0 && capturedPokemon == false)
        {
            System.out.println(otherPokemon.toString() + " has " + otherPokemon.getHealth() + " HP.");
            System.out.println(toString() + "'s " + myPokemon.toString() + " has " + myPokemon.getHealth() + " HP.");
            while (otherPokemon.getHealth() > 0 && myPokemon.getHealth() > 0 && capturedPokemon == false)
            {
                boolean willingToCapture = false;
                if(faveType == AbstractPokemon.Type.ANY_TYPE)
                    willingToCapture = true;
                else if(otherPokemon.getType() == faveType)
                    willingToCapture = true;
                if(!otherPokemon.canCapture() || !willingToCapture)
                {
                    otherPokemon.takeDamage(myPokemon);
                    if(otherPokemon.getHealth() < 0)
                        otherPokemon.setHealth(0);
                    System.out.println(otherPokemon.toString() + " has " + otherPokemon.getHealth() + " HP.");
                    if(otherPokemon.getHealth() > 0)
                    {
                        myPokemon.takeDamage(otherPokemon);
                        if(myPokemon.getHealth() < 0)
                            myPokemon.setHealth(0);
                        System.out.println(toString() + "'s " + myPokemon.toString() + " has " + myPokemon.getHealth() + " HP.");
                    }
                }
                else
                {
                    if(throwPokeball(otherPokemon))
                    {
                        System.out.println(otherPokemon.toString() + " has been captured!");
                        otherPokemon.setHealth(20);
                        caughtPokemon.add(otherPokemon);
                        otherPokemon.removeSelfFromGrid();
                        capturedPokemon = true;
                    }
                    else
                    {
                        myPokemon.takeDamage(otherPokemon);
                        if(myPokemon.getHealth() < 0)
                            myPokemon.setHealth(0);
                        System.out.println(toString() + "'s " + myPokemon.toString() + " has " + myPokemon.getHealth() + " HP.");
                    }
                }
            }
            if (myPokemon.getHealth() <= 0)
            {
                System.out.println(toString() + "'s " + myPokemon.toString() + " has fainted!");
                caughtPokemon.remove(0);
            }
            if (caughtPokemon.size() > 0)
            {
                System.out.println(toString() + " sent out " + caughtPokemon.get(0).toString() + ".");
                myPokemon = caughtPokemon.get(0);
            }
        }
        if(capturedPokemon == false)
        {
            if (otherPokemon.getHealth() <= 0)
            {
                System.out.println(otherPokemon.toString() + " has fainted!");
                otherPokemon.removeSelfFromGrid();
                myPokemon.setHealth(20);
            }
            else
            {
                System.out.println(toString() + " has blacked out! " + toString() + " has appeared at a PokeCenter.");
                removeSelfFromGrid();
                otherPokemon.setHealth(20);
            }
        }
        System.out.println("");
    }
    
    /**
     * Used to battle another trainer.  The battle continues
     * as long as both trainers still have Pokemon that have
     * not fainted.  Each individual Pokemon battle acts like
     * a wild Pokemon battle (see above), with the exception
     * that the trainer will not attempt to capture the other
     * Pokemon.
     * 
     * @param otherTrainer The Trainer to be battled
     */
    public void battleTrainer(PokemonTrainer otherTrainer)
    {
        ArrayList<AbstractPokemon> otherTrainersPokemon = otherTrainer.getPokemon();
        AbstractPokemon myPokemon = caughtPokemon.get(0);
        AbstractPokemon otherPokemon = otherTrainersPokemon.get(0);
        while(caughtPokemon.size() > 0 && otherTrainersPokemon.size() > 0)
        {
            System.out.println(toString() + " sent out " + caughtPokemon.get(0).toString() + ".");
            System.out.println(otherTrainer.toString() + " sent out " + otherTrainersPokemon.get(0).toString() + ".");
            System.out.println(otherTrainer.toString() + "'s " + otherPokemon.toString() + " has " + otherPokemon.getHealth() + " HP.");
            System.out.println(toString() + "'s " + myPokemon.toString() + " has " + myPokemon.getHealth() + " HP.");
            while (otherPokemon.getHealth() > 0 && myPokemon.getHealth() > 0)
            {
                otherPokemon.takeDamage(myPokemon);
                if(otherPokemon.getHealth() < 0)
                    otherPokemon.setHealth(0);
                System.out.println(otherTrainer.toString() + "'s " + otherPokemon.toString() + " has " + otherPokemon.getHealth() + " HP.");
                if(otherPokemon.getHealth() > 0)
                {
                    myPokemon.takeDamage(otherPokemon);
                    if(myPokemon.getHealth() < 0)
                        myPokemon.setHealth(0);
                    System.out.println(toString() + "'s " + myPokemon.toString() + " has " + myPokemon.getHealth() + " HP.");
                }
            }
            if (myPokemon.getHealth() <= 0)
            {
                System.out.println(toString() + "'s " + myPokemon.toString() + " has fainted!");
                caughtPokemon.remove(0);
            }
            else
            {
                System.out.println(otherTrainer.toString() + "'s " +otherPokemon.toString() + " has fainted!");
                otherTrainersPokemon.remove(0);
            }
            if (caughtPokemon.size() > 0)
            {
                System.out.println(toString() + " sent out " + caughtPokemon.get(0).toString() + ".");
                myPokemon = caughtPokemon.get(0);
            }
            if (otherTrainersPokemon.size() > 0)
            {
                System.out.println(otherTrainer.toString() + " sent out " + otherTrainersPokemon.get(0).toString() + ".");
                otherPokemon = otherTrainersPokemon.get(0);
            }
        }
        if (otherTrainersPokemon.size() == 0)
        {
            System.out.println(otherTrainer.toString() + " has blacked out! " + otherTrainer.toString() + " has appeared at a PokeCenter.");
            otherTrainer.removeSelfFromGrid();
            myPokemon.setHealth(20);
        }
        else
        {
            System.out.println(toString() + " has blacked out! " + toString() + " has appeared at a PokeCenter.");
            removeSelfFromGrid();
        }
        System.out.println("");
    }
    
    /**
     * Method that removes a Pokemon from the trainer's caught Pokemon list
     * 
     * Precondition: pokemonToRemove is not larger than the size of the list - 1 and
     * is not negative
     * 
     * @param pokemonToRemove The location of the Pokemon in the list
     */
    public void removePokemon(int pokemonToRemove)
    {
        caughtPokemon.remove(pokemonToRemove);
    }
    
    /**
     * Makes the trainer act on the grid.
     */
    public void act()
    {
        if (getGrid() == null)
            return;
        if (caughtPokemon.size() <= 0)
        {
            removeSelfFromGrid();
            System.out.println(toString() + " has blacked out! " + toString() + " has appeared at a PokeCenter.");
            return;
        }
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
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
     * Selects which Location the trainer will attempt to move toward next.
     * 
     * @param locs The list of valid move Locations
     * @return The Location to move to
     */
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }
    
    /**
     * Moves the trainer toward the selected move Location from
     * selectMoveLocation(ArrayList<Location> locs).  If the 
     * selected move Location is occupied by a wild Pokemon or
     * another trainer, then this trainer commences to battle
     * the wild Pokemon or other trainer.
     * 
     * @param loc The selected move Location that the trainer will attempt to move to
     */
    public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
        {
            int newDirection = getLocation().getDirectionToward(loc);
            Location nextLocation = getLocation().getAdjacentLocation(newDirection);
            Actor otherActor = getGrid().get(nextLocation);
            if(otherActor != null)
            {
                if(otherActor instanceof AbstractPokemon)
                {
                    AbstractPokemon otherPokemon = (AbstractPokemon) otherActor;
                    battle(otherPokemon);
                }
                else
                {
                    PokemonTrainer otherTrainer = (PokemonTrainer) otherActor;
                    battleTrainer(otherTrainer);
                }
            }
            if(getGrid() != null)
                moveTo(loc);
        }
    }
    
    /**
     * Overides the toString() method of the Actor class
     * 
     * @return The string representation of this trainer
     */
    public String toString()
    {
        return name;
    }
    
    /**
     * Attempts to capture a wild Pokemon.  The success
     * or failure is determined by what type of Pokeball
     * the trainer is using, as well as the maximum HP of
     * the Pokemon and the current HP of the Pokemon. 
     * If the trainer captures the wild Pokemon the
     * wild Pokemon is fully healed and is placed
     * in the trainer's list of caught Pokemon.
     * 
     * @param wildPokemon The wild Pokemon that this trainer wants to capture
     * @return Wether or not the wild Pokemon was caught
     */
    public boolean throwPokeball(AbstractPokemon wildPokemon)
    {
        System.out.println(toString() + " threw a " + pokeball.toString() + "!");
        int ballFactor = pokeball.getFactor();
        if(pokeball instanceof Masterball)
            return true;
        int d = wildPokemon.getCatchRate() / ballFactor;
        if (d >= 256)
            return false;
        else
        {
            int pokemonsHP = wildPokemon.getHealth();
            if (pokemonsHP == 0)
                pokemonsHP = 4;
            int factor1 = (wildPokemon.getMaxHealth() * 255) / ballFactor;
            int factor2 = wildPokemon.getHealth() / 4;
            if (factor2 == 0)
                factor2 = 1;
            int factorTotal = factor1 / factor2;
            if (factorTotal > 255)
                factorTotal = 255;
            int probabilityOfCapture = (d * factorTotal) / 255;
            if (probabilityOfCapture > 70)
                return true;
            else
                return false;
        }
    }
    
    /**
     * Used to select objects for a PokemonTrainer.
     * 
     * @author Jonathan Lowe
     * @version 0.0.0
     */ 
    class Selector
    {
        /**
         * Selects the trainer's name
         * 
         * @return The name of the trainer
         */
        public String selectName()
        {
            JOptionPane nameSelector = new JOptionPane();
            String name = nameSelector.showInputDialog("What is this trainer's name?");
            return name;
        }
        
        /**
         * Selects the trainer's preferred type
         * 
         * @return The type of Pokemon the trainer is willing to capture
         */
        public AbstractPokemon.Type selectType()
        {
            JOptionPane typeSelector = new JOptionPane();
            Object message = "Select which Pokemon this trainer is willing to capture:";
            Object[] options = {AbstractPokemon.Type.FIRE_TYPE, AbstractPokemon.Type.WATER_TYPE, AbstractPokemon.Type.GRASS_TYPE, AbstractPokemon.Type.ANY_TYPE};
            int type = typeSelector.showOptionDialog(null, message, "Selection",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 
                options[3]);
            AbstractPokemon.Type faveType;
            switch(type)
            {
                case 0: faveType = AbstractPokemon.Type.FIRE_TYPE; break;
                case 1: faveType = AbstractPokemon.Type.WATER_TYPE; break;
                case 2: faveType = AbstractPokemon.Type.GRASS_TYPE; break;
                case 3: faveType = AbstractPokemon.Type.ANY_TYPE; break;
                default: faveType = AbstractPokemon.Type.ANY_TYPE; break;
            }
            return faveType;
        }
        
        /**
         * Selects the trainer's preferred Pokeball type
         * 
         * @return The type of Pokeball the trainer uses
         */
        public Pokeball selectBall()
        {
            JOptionPane ballSelector = new JOptionPane();
            Object[] options2 = {new Pokeball(), new Greatball(), new Ultraball(), new Masterball()};
            int ball = ballSelector.showOptionDialog(null, "Select which Pokeball this trainer will use:", "Selection",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, 
                options2[3]);
            Pokeball pokeball;
            switch(ball)
            {
                case 0: pokeball = new Pokeball(); break;
                case 1: pokeball = new Greatball(); break;
                case 2: pokeball = new Ultraball(); break;
                case 3: pokeball = new Masterball(); break;
                default: pokeball = new Pokeball(); break;
            }
            return pokeball;
        }
        
        /**
         * Selects the trainer's starting Pokemon
         * 
         * @return The list of starter Pokemon the trainer will use
         */
        public ArrayList<AbstractPokemon> selectPokemon()
        {
            /**
             * Tells the Java Runtime Environment to add a certain Pokemon to a list of Pokemon when a button is clicked.
             * 
             * @author Jonathan Lowe
             * @version 1.0.0
             */
            class PokemonClickListener implements ActionListener
            {
                private JTextArea label;
                private String pokemonName;
                private AbstractPokemon pokemon;
                private ArrayList<AbstractPokemon> party;
                
                /**
                 * Creates a copy of the Pokemon to be stored in the list of Pokemon
                 * 
                 * @param list The list the selected Pokemon will be added to
                 * @param label The output stream to be updated when a Pokemon is added
                 * @param poke The Pokemon to be added to the list
                 */
                public PokemonClickListener(ArrayList<AbstractPokemon> list, JTextArea label, AbstractPokemon poke)
                {
                    this.label = label;
                    this.pokemonName = poke.toString();
                    if(poke instanceof Bulbasaur)
                        this.pokemon = new Bulbasaur();
                    else if(poke instanceof Sunkern)
                        this.pokemon = new Sunkern();
                    else if(poke instanceof Oddish)
                        this.pokemon = new Oddish();
                    else if(poke instanceof Charmander)
                        this.pokemon = new Charmander();
                    else if(poke instanceof Vulpix)
                        this.pokemon = new Vulpix();
                    else if(poke instanceof Magby)
                        this.pokemon = new Magby();
                    else if(poke instanceof Squirtle)
                        this.pokemon = new Squirtle();
                    else if(poke instanceof Poliwhirl)
                        this.pokemon = new Poliwhirl();
                    else if(poke instanceof Magikarp)
                        this.pokemon = new Magikarp();
                    else
                        this.pokemon = new Charmander();
                    this.party = list;
                }
                
                /**
                 * Adds the copied Pokemon to the list of Pokemon
                 * 
                 * @param event The button click event
                 */
                public void actionPerformed(ActionEvent event)
                {
                    label.append("\n" + pokemonName);
                    party.add(pokemon);
                }
            }
            
            /**
             * Tells the Java Runtime Environment to "close" the JFrame this button is found in
             * 
             * @author Jonathan Lowe
             * @version 1.0.0
             */
            class ExitClickListener implements ActionListener
            {
                private JFrame frame;
                
                /**
                 * Sets the JFrame to be closed
                 * 
                 * @param frame The JFrame to be closed
                 */
                public ExitClickListener(JFrame frame)
                {
                    this.frame = frame;
                }
                
                /**
                 * Closes the JFrame
                 * 
                 * @param event The button click event
                 */
                public void actionPerformed(ActionEvent event)
                {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            }
            
            JFrame frame = new JFrame();
            JTextArea label = new JTextArea("The Pokemon in your party:", 7, 20);
            label.setEditable(false);
            JPanel masterPanel = new JPanel(new GridLayout(3, 1));
            JLabel title = new JLabel("Please select this trainer's Pokemon.");
            JPanel titlePanel = new JPanel();
            titlePanel.setSize(10, 400);
            titlePanel.add(title);
            masterPanel.add(titlePanel);
            JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
            ArrayList<AbstractPokemon> party = new ArrayList<AbstractPokemon>();
            JPanel leftPanel = new JPanel(new GridLayout(3, 3));
            JButton charmanderButton = new JButton(new ImageIcon("Charmander.gif"));
            charmanderButton.addActionListener(new PokemonClickListener(party, label, new Charmander()));
            leftPanel.add(charmanderButton);
            JButton vulpixButton = new JButton(new ImageIcon("Vulpix.gif"));
            vulpixButton.addActionListener(new PokemonClickListener(party, label, new Vulpix()));
            leftPanel.add(vulpixButton);
            JButton magbyButton = new JButton(new ImageIcon("Magby.gif"));
            magbyButton.addActionListener(new PokemonClickListener(party, label, new Magby()));
            leftPanel.add(magbyButton);
            JButton squirtleButton = new JButton(new ImageIcon("Squirtle.gif"));
            squirtleButton.addActionListener(new PokemonClickListener(party, label, new Squirtle()));
            leftPanel.add(squirtleButton);
            JButton poliwhirlButton = new JButton(new ImageIcon("Poliwhirl.gif"));
            poliwhirlButton.addActionListener(new PokemonClickListener(party, label, new Poliwhirl()));
            leftPanel.add(poliwhirlButton);
            JButton magikarpButton = new JButton(new ImageIcon("Magikarp.gif"));
            magikarpButton.addActionListener(new PokemonClickListener(party, label, new Magikarp()));
            leftPanel.add(magikarpButton);
            JButton bulbasaurButton = new JButton(new ImageIcon("Bulbasaur.gif"));
            bulbasaurButton.addActionListener(new PokemonClickListener(party, label, new Bulbasaur()));
            leftPanel.add(bulbasaurButton);
            JButton sunkernButton = new JButton(new ImageIcon("Sunkern.gif"));
            sunkernButton.addActionListener(new PokemonClickListener(party, label, new Sunkern()));
            leftPanel.add(sunkernButton);
            JButton oddishButton = new JButton(new ImageIcon("Oddish.gif"));
            oddishButton.addActionListener(new PokemonClickListener(party, label, new Oddish()));
            leftPanel.add(oddishButton);
            bottomPanel.add(leftPanel);
            JPanel rightPanel = new JPanel();
            rightPanel.add(label);
            bottomPanel.add(rightPanel);
            masterPanel.add(bottomPanel);
            JButton exitButton = new JButton("Okay, I'm done");
            exitButton.addActionListener(new ExitClickListener(frame));
            JPanel exitPanel = new JPanel();
            exitPanel.setSize(20, 100);
            exitPanel.add(exitButton);
            masterPanel.add(exitPanel);
            frame.add(masterPanel);
            frame.setTitle("Pokemon Selection");
            frame.setSize(600, 400);
            frame.setVisible(true);
            
            return party;
        }
    }
}
