import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


//Needs to have code convereted to run with correct classes
//This should be the 'main' for the program
public class Animation extends JPanel implements Observer {
	static final double X_SIZE = 100.0;
	static final double Y_SIZE = 100.0;
	

	
    PPModel simulation;
    //This initializes the simulation grid and all Predator and Prey objects
    Animation(PPModel simulation) {
        this.simulation = simulation;
        setPreferredSize(new Dimension(400, 400));

        //simulation.predators

        for(Animal predators : simulation.predators) {
            predators.addObserver(this);
        }
        for(Animal prey : simulation.prey) {
            prey.addObserver(this);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        Animal animal = (Animal)o;
        repaint((int)animal.getXDest(),(int)animal.getYDest(),24,24);
       // Blob blob = (Blob)o;
        //repaint(blob.x, blob.y, 24, 24);       
        //repaint(blob.x - 12, blob.y - 12, 24, 24);
    }
   
    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        for(Animal predators : simulation.predators) {
            //predators.addObserver(this);
            Color color = Color.getHSBColor(102, 102, 102);
            g.setColor(color);
            g.fillOval((int)predators.getXDest(), (int)predators.getYDest(), 20, 20);
        }
        
        for(Animal prey : simulation.prey) {
            Color color = Color.getHSBColor(255, 204, 0);
            g.setColor(color);
            g.fillOval((int)prey.getXDest(), (int)prey.getYDest(), 20, 20);
        }
    }


    /*
    // Necesary to update the predator and prey after each game tick
    @Override
    public void update(Observable o, Object arg) {
        Blob blob = (Blob)o;
        repaint(blob.x, blob.y, 24, 24);       
        //repaint(blob.x - 12, blob.y - 12, 24, 24);
    }
    
    // Necesary to update the predator and prey after each game tick
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Blob blob : simulation.blobs) {
            g.setColor(blob.color);
            g.fillOval(blob.x, blob.y, 20, 20);
            //g.fillOval(blob.x - 10, blob.y - 10, 20, 20);
        }
    }
*/
    // public static void main(String[] args) {
    //     Simulation simulation = new Simulation();

    //     JFrame frame = new JFrame();
    //     frame.getContentPane().add(new Animation(simulation));
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.pack();
    //     frame.setVisible(true);

    //     simulation.simulate();
    // }




    public static void main(String [] args)
    {
        System.out.println("simulation::main: Begining main...");
        
        //read from user?
        int numPrey = 5;
        int numPred = 5;
        int simTime = 10;
        double xFieldSize = X_SIZE;
        double yFieldSize = Y_SIZE;
        
        double timeStepPartitions = 100;
        
        
        //setup the simulation with values from user        
        Simulation sim = new Simulation();
        PPModel model = sim.prepSimulation(numPred, numPrey, xFieldSize, yFieldSize);
        
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Animation(model));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        


        //run the actual simulation with user init conditions
        sim.runSimulation(model, simTime, timeStepPartitions);
        
        
        System.out.println("simulation::main: Exiting main.");






        //============This needs to be modified for Simulation====
        /*
        Simulation simulation = new Simulation();

        JFrame frame = new JFrame();
        frame.getContentPane().add(new Animation(simulation));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        simulation.simulate();
        */


        
    }







    
}
