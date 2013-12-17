import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Animation extends JPanel implements Observer {
	static final double X_SIZE = 500.0;
	static final double Y_SIZE = 500.0;
	static final int SIM_DURATION = 20;

	PPModel simulation;

	// This initializes the simulation grid and all Predator and Prey objects
	Animation(PPModel simulation) {
		this.simulation = simulation;
		setPreferredSize(new Dimension((int)X_SIZE, (int)Y_SIZE));

		for (Animal predators : simulation.predators) {
			predators.addObserver(this);
		}
		for (Animal prey : simulation.prey) {
			prey.addObserver(this);
		}
	}

	//Necessary Update Override to update movement on GUI
	@Override
	public void update(Observable o, Object arg) {
		Animal animal = (Animal) o;
		// if(animal.getLifeStatus() == true)
		// {
		// 	repaint((int) animal.getXCoord(), (int) animal.getYCoord(), 10, 10);
		// }
		// else
		// {
		// 	//repaint((int) animal.getXCoord(), (int) animal.getYCoord(), 10, 10);
		// 	repaint();
		// }

			repaint((int) animal.getXCoord(), (int) animal.getYCoord(), 10, 10);

	}

	//Used to add the initial components for GUI. Adds the number of Prey/Predators on Grid
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Animal predators : simulation.predators) {
			// predators.addObserver(this);
			Color color = Color.getHSBColor(102, 102, 102); // Color for Predators - Black 
			g.setColor(Color.red);
			g.fillOval((int) predators.getXCoord(), (int) predators.getYCoord(),
					10, 10); //Sets size of GUI Representations of Predators
		}

		for (Animal prey : simulation.prey) {
			Color color = Color.getHSBColor(255, 204, 0); // Color for Prey - Yellow 
			g.setColor(Color.green);
			g.fillOval((int) prey.getXCoord(), (int) prey.getYCoord(), 10, 10); //Sets size of GUI Representations of Prey
		}
		for(Animal dead : simulation.deadAnimals)
		{
			Color color = Color.getHSBColor(255, 0, 0); // Color for Prey - Yellow 
			g.setColor(Color.black);
			g.fillOval((int) dead.getXCoord(), (int) dead.getYCoord(), 10, 10); //Sets size of GUI Representations of Prey
		}
	}

	public static void main(String[] args) {
		System.out.println("simulation::main: Begining main...");

		// read from user?
		int numPrey = 5;
		int numPred = 5;
		int simTime = SIM_DURATION;
		double xFieldSize = X_SIZE;
		double yFieldSize = Y_SIZE;

		double timeStepPartitions = 100;

		// setup the simulation with values from user
		Simulation sim = new Simulation();
		PPModel model = sim.prepSimulation(numPred, numPrey, xFieldSize,
				yFieldSize);

		//Sets up the Frame for the GUI and Movements of the Simulation
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Animation(model));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		// run the actual simulation with user init conditions
		sim.runSimulation(model, simTime, timeStepPartitions);

		System.out.println("simulation::main: Exiting main.");

	}

}
