import java.util.ArrayList;



public class ppmodel {
	//private final int DEF_PRED_SIZE = 10;
	//private final int DEF_PREY_SIZE = 10;
	
	private ArrayList<animal> predators = new ArrayList<animal>();
	private ArrayList<animal> prey = new ArrayList<animal>();
	int numPred = 0;
	int numPrey = 0;
	
	
	public ppmodel() 
	{
		this(0, 0);
	}
	
	public ppmodel(int numPred, int numPrey)
	{
		//set initial number of predators and prey
		this.numPred = numPred;
		this.numPrey = numPrey;
		
		
		//create that number of preds and prey
		for (int i = 0; i < this.numPred; i++) {
			String predName = "shark"+i;
			String predType = "predator";
			String predSpecies = "great white";
			
			shark predShark = new shark( predName, predType, predSpecies );
			
			predators.add( predShark );
		}
		
		for (int i = 0; i < this.numPrey; i++) {
			String preyName = "fish"+i;
			String preyType = "prey";
			String preySpecies = "gold fish";
			
			fish preyFish = new fish( preyName, preyType, preySpecies );
			
			prey.add( preyFish );
		}
	}
	
	public void runSimulation(int time)
	{
		System.out.println("ppmodel::runSimulation: Starting run for " + time + " time.");
	}
	
	public void displayInfo()
	{
		System.out.println( "Model has "+this.numPred+" Predators and "+this.numPrey+" Prey" );
	}
}
