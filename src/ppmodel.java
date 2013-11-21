import java.util.ArrayList;

import com.sun.tools.javadoc.Messager.ExitJavadoc;



public class ppmodel {
	//private final int DEF_PRED_SIZE = 10;
	//private final int DEF_PREY_SIZE = 10;
	
	private ArrayList<animal> predators = new ArrayList<animal>();
	private ArrayList<animal> prey = new ArrayList<animal>();
	int numPred = 0;
	int numPrey = 0;
	
	int xSize = 0;
	int ySize = 0;
	
	
	public ppmodel() 
	{
		this(0, 0, 100, 100);
	}
	
	public ppmodel(int numPred, int numPrey, int xSize, int ySize)
	{
		//set initial number of predators and prey
		this.numPred = numPred;
		this.numPrey = numPrey;
		
		
		//set size for field of play
		if(xSize > 0)
			this.xSize = xSize;
		else {
			System.out.println("ppmodel::ERROR: An invalid value for xSize= "+xSize+" was enountered. xSize must be >= 1.");
			System.exit(0); //exit gracefully
		}

		if(ySize > 0)
			this.ySize = ySize;
		else {
			System.out.println("ppmodel::ERROR: An invalid value for ySize= "+ySize+" was enountered. xSize must be >= 1.");
			System.exit(0); //exit gracefully
		}
		
		
		//create that number of preds and prey
		for (int i = 0; i < this.numPred; i++) {
			String predName = "shark"+i;
			String predType = "predator";
			String predSpecies = "great white";
			int predEnergy = 100;
			
			shark predShark = new shark( predName, predType, predSpecies, predEnergy);
			
			predators.add( predShark );
		}
		
		for (int i = 0; i < this.numPrey; i++) {
			String preyName = "fish"+i;
			String preyType = "prey";
			String preySpecies = "gold fish";
			int preyEnergy = 100;
			
			fish preyFish = new fish( preyName, preyType, preySpecies, preyEnergy);
			
			prey.add( preyFish );
		}
	}
	
	
	public void runSimulation(int time)
	{
		System.out.println("ppmodel::runSimulation: Starting run for " + time + " time.");
		
		for(int i = time; i > 0; i--)
		{
			System.out.println("ppmodel::runSimulation: TIME STEP: " + i);
				
		}
	}
	
	public void displayInfo()
	{
		System.out.println( "Model has "+this.numPred+" Predators and "+this.numPrey+" Prey" );
		
	}
}
