


public class Simulation 
{
	static final int X_SIZE = 100;
	static final int Y_SIZE = 100;
	
	
	
	public PPModel prepSimulation(int numPred, int numPrey, int xSize, int ySize)
	{
		System.out.println("simulation::prepSimulation: setting up model with: numPred: "+numPred+", numPrey: "+numPrey);
		
		PPModel model = new PPModel(numPred, numPrey, xSize, ySize);
		
		return model;
	}
	
	
	void runSimulation( PPModel sim , int time)
	{
		System.out.println("simulation::runSimulation: Running model with time: "+time);
		
		sim.runSimulation( time );
		
		System.out.println("simulation::runSimulation: Finsihed running model with time: "+time);
	}
	
	
	public static void main(String [] args)
	{
		System.out.println("simulation::main: Begining main...");
		
		//read from user?
		int numPrey = 5;
		int numPred = 5;
		int simTime = 10;
		int xFieldSize = X_SIZE;
		int yFieldSize = Y_SIZE;
		
		
		//setup the simulation with values from user		
		Simulation sim = new Simulation();
		PPModel model = sim.prepSimulation(numPred, numPrey, xFieldSize, yFieldSize);
		
		
		//run the actual simulation with user init conditions
		sim.runSimulation(model, simTime);
		
		
		System.out.println("simulation::main: Exiting main.");
	}
	
}
