//Cameron Embree, Gradon Faulkner


public class Simulation 
{
//	static final double X_SIZE = 100.0;
//	static final double Y_SIZE = 100.0;
	
	
	
	public PPModel prepSimulation(int numPred, int numPrey, double xSize, double ySize)
	{
		System.out.println("simulation::prepSimulation: setting up model with: numPred: "+numPred+", numPrey: "+numPrey);
		
		PPModel model = new PPModel(numPred, numPrey, xSize, ySize);
		
		return model;
	}
	
	
	void runSimulation( PPModel sim, int time, double timeStepPartitions)
	{

		// System.out.println("simulation::runSimulation: Running model with time: "+time);
		
		// sim.runSimulation( time );
		
		// System.out.println("simulation::runSimulation: Finsihed running model with time: "+time);
		

		//===========This is code that needs to be modified to run=====
		/*while(true) {
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                return;
            }
            for(Blob blob : blobs) {
                blob.tick();
            }
        } */



		System.out.println("simulation::runSimulation: Running model with time: "+time);
		
		sim.runSimulation( time, timeStepPartitions );
		
		System.out.println("simulation::runSimulation: Finsihed running model with time: "+time);
	}
	
	
	// public static void main(String [] args)
	// {
	// 	System.out.println("simulation::main: Begining main...");
		
	// 	//read from user?
	// 	int numPrey = 5;
	// 	int numPred = 5;
	// 	int simTime = 10;
	// 	double xFieldSize = X_SIZE;
	// 	double yFieldSize = Y_SIZE;
		
	// 	double timeStepPartitions = 100;
		
		
	// 	//setup the simulation with values from user		
	// 	Simulation sim = new Simulation();
	// 	PPModel model = sim.prepSimulation(numPred, numPrey, xFieldSize, yFieldSize);
		
		
	// 	//run the actual simulation with user init conditions
	// 	sim.runSimulation(model, simTime, timeStepPartitions);
		
		
	// 	System.out.println("simulation::main: Exiting main.");






	// 	//============This needs to be modified for Simulation====
	// 	/*
	// 	Simulation simulation = new Simulation();

 //        JFrame frame = new JFrame();
 //        frame.getContentPane().add(new Animation(simulation));
 //        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 //        frame.pack();
 //        frame.setVisible(true);

 //        simulation.simulate();
	// 	*/


		
	// }
	
}
