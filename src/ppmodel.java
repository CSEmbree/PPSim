//Cameron Embree, Gradon Faulkner
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;


public class PPModel extends SimActor {
	//private final double DEF_PRED_SIZE = 10;
	//private final double DEF_PREY_SIZE = 10;

	public ArrayList<Animal> predators = new ArrayList<Animal>(); //set of all predators in simulation
	public ArrayList<Animal> prey = new ArrayList<Animal>(); //set of all prey in simulation
	int numPred = 0; //number of prey in the simulation currently, convenience variable
	int numPrey = 0; //number of predators in the simulation currently, convenience variable

	double xSize = 0.0; //max x dimention of simulation (x=0, 1,...,xSize)
	double ySize = 0.0; //max y dimention of simulation (y=0, 1,...,ySize)


	public PPModel() 
	{
		this(0, 0, 100.0, 100.0);
	}

	public PPModel(int numPred, int numPrey, double xSize, double ySize)
	{
		//set initial number of predators and prey
		this.setNumPred(numPred);
		this.setNumPrey(numPrey);


		//set size for field of play
		if(xSize > 0.0)
			this.setXSize( xSize );
		else {
			System.out.println("PPModel::PPModel: ERROR: An invalid value for xSize= "+xSize+" was enountered. xSize must be >= 1.");
			System.exit(0); //exit gracefully
		}

		if(ySize > 0.0)
			this.setYSize( ySize) ;
		else {
			System.out.println("PPModel::PPModel: ERROR: An invalid value for ySize= "+ySize+" was enountered. xSize must be >= 1.");
			System.exit(0); //exit gracefully
		}

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		//int randPosX = rand.nextInt((int)xSize);
		//int randPosY = rand.nextInt((int)ySize);

		//create that number of preds and prey
		double xPos, yPos, energy, maxDistTravel;
		for (int i = 0; i < this.numPred; i++) {
			String predName = "shark"+i;
			String predType = "predator";
			String id = predType+"_"+predName;
			String predSpecies = "great white";
			energy = 100.0;
			maxDistTravel = 20.0;
			xPos = rand.nextInt((int)xSize);
			yPos = rand.nextInt((int)ySize);

			Shark predShark = new Shark( id, predName, predType, predSpecies, energy, maxDistTravel, xPos, yPos);

			predators.add( predShark );
		}

		for (int i = 0; i < this.numPrey; i++) {
			String preyName = "fish"+i;
			String preyType = "prey";
			String id = preyType+"_"+preyName;
			String preySpecies = "gold fish";
			energy = 100;
			maxDistTravel = 10;
			xPos = rand.nextInt((int)xSize);
			yPos = rand.nextInt((int)ySize);

			Fish preyFish = new Fish( id, preyName, preyType, preySpecies, energy, maxDistTravel, xPos, yPos);

			prey.add( preyFish );
		}
	}


	public void runSimulation(int time, double timeStepPartitions)
	{
		System.out.println("PPModel::runSimulation: Starting run for " + time + " time.");


		for(int currentTime = time; currentTime > 0; currentTime--)
		{
			System.out.println("PPModel::runSimulation: TIME STEP: " + currentTime);

			cleanAndPlan(timeStepPartitions);
			//Thread.sleep(50);
			 try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                return;
            }
			activateAndMove(timeStepPartitions);
			//Thread.sleep(50);	
			try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                return;
            }
		}
	}

	public void cleanAndPlan(double timeStepPartitions)
	{
		System.out.println("PPModel::cleanAndPlan: Cleaning updating any dead actors and deciding their next move");
		boolean removed;


		//remove any dead predators and determine their next move
		removed = true;
		while(removed == true) 
		{
			removed = false;
			for(int currentActorIndex = 0; currentActorIndex < predators.size(); currentActorIndex++ )
			{
				if(predators.get(currentActorIndex).getEnergy() <= 0)
				{
					predators.remove(currentActorIndex);
					removed = true; //if clean up was performed, run the whole loop again
				}
			}
		}


		for(int currentActorIndex = 0; currentActorIndex < predators.size(); currentActorIndex++ )
		{
			predators.get(currentActorIndex).chooseNewDestination(xSize, ySize, timeStepPartitions);	
		}



		//remove any dead prey and determine their next move
		removed = true;
		while(removed == true) 
		{
			removed = false;
			for(int currentActorIndex = 0; currentActorIndex < prey.size(); currentActorIndex++ )
			{
				if(prey.get(currentActorIndex).getEnergy() <= 0)
				{
					prey.remove(currentActorIndex);
					removed = true; //if clean up was performed, run the whole loop again
				}
			}
		}

		for(int currentActorIndex = 0; currentActorIndex < prey.size(); currentActorIndex++ )
		{
			prey.get(currentActorIndex).chooseNewDestination(xSize, ySize, timeStepPartitions);	
		}
	}

	private void activateAndMove(double timeStepPartitions)
	{
		System.out.println("PPModel::activateAndMove: Starting to move things...");

		//TODO - account for delta move and add in steps
		for (int i = 0; i < timeStepPartitions; i++) 
		{
			//System.out.println("PPModel::activateAndMove: Partial Iteration "+i+" of "+timeStepPartitions);
			
			for (Animal an : predators) {
				an.setXYPosition( an.getXCoord()+an.getDeltaX(), an.getYCoord()+an.getDeltaY() );
				setChanged();
            	notifyObservers();
			}

			for (Animal an : prey) {
				an.setXYPosition( an.getXCoord()+an.getDeltaX(), an.getYCoord()+an.getDeltaY() );
				setChanged();
           	 	notifyObservers();
			}
			//Thread.sleep(50);
		}
		this.displayInfo(); //for state debug checking


		System.out.println("PPModel::activateAndMove: Stopping moving things...");
	}

	public double getXCoordSize()
	{
		return this.xSize;
	}

	public double getYCoordSize()
	{
		return this.ySize;
	}

	private void setNumPrey(int preyCount)
	{
		this.numPrey = preyCount;
	}

	private void setNumPred(int predCount)
	{
		this.numPred = predCount;
	}

	private void setXSize( double xSize )
	{
		this.xSize = xSize;
	}

	private void setYSize( double ySize )
	{
		this.ySize = ySize;
	}
	
	public void displayPredInfo()
	{
		for (Animal an : predators) {
			System.out.println("\tPred Name:"+an.getName()+" at position ("+an.getXCoord()+","+an.getYCoord()+")");
		}
	}
	
	public void displayPreyInfo()
	{
		for (Animal an : prey) {
			System.out.println("\tPrey Name:"+an.getName()+" at position ("+an.getXCoord()+","+an.getYCoord()+")");
		}
	}

	public void displayInfo()
	{
		System.out.println("PPModel has "+this.numPred+" Predators and "+this.numPrey+" Prey on a field of "+xSize+"x"+ySize );
		this.displayPredInfo();
		this.displayPreyInfo();

	}
}
