//Cameron Embree, Gradon Faulkner

public class Simulation 
{
//	static final double X_SIZE = 100.0;
//	static final double Y_SIZE = 100.0;
	
	
	public PPModel prepSimulation(int numPred, int numPrey, double xSize, double ySize) {
		System.out.println("simulation::prepSimulation: setting up model with: numPred: "+numPred+", numPrey: "+numPrey);
		
		PPModel model = new PPModel(numPred, numPrey, xSize, ySize);
		
		return model;
	}
	
	
	void runSimulation( PPModel sim, int time, double timeStepPartitions) {
		System.out.println("simulation::runSimulation: Running model with time: " + time);
		
		sim.runSimulation( time, timeStepPartitions );
		
		System.out.println("simulation::runSimulation: Finsihed running model with time: " + time);
	}
}
