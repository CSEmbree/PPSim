import java.util.ArrayList;



public class PPModel extends SimActor{
	//private final int DEF_PRED_SIZE = 10;
	//private final int DEF_PREY_SIZE = 10;
	
	private ArrayList<SimActor> predators = new ArrayList<SimActor>();
	private ArrayList<SimActor> prey = new ArrayList<SimActor>();
	int numPred = 0;
	int numPrey = 0;
	
	int xSize = 0;
	int ySize = 0;
	
	
	public PPModel() 
	{
		this(0, 0, 100, 100);
	}
	
	public PPModel(int numPred, int numPrey, int xSize, int ySize)
	{
		//set initial number of predators and prey
		this.numPred = numPred;
		this.numPrey = numPrey;
		
		
		//set size for field of play
		if(xSize > 0)
			this.xSize = xSize;
		else {
			System.out.println("PPModel::PPModel: ERROR: An invalid value for xSize= "+xSize+" was enountered. xSize must be >= 1.");
			System.exit(0); //exit gracefully
		}

		if(ySize > 0)
			this.ySize = ySize;
		else {
			System.out.println("PPModel::PPModel: ERROR: An invalid value for ySize= "+ySize+" was enountered. xSize must be >= 1.");
			System.exit(0); //exit gracefully
		}
		
		
		//create that number of preds and prey
		int x, y;
		for (int i = 0; i < this.numPred; i++) {
			String predName = "shark"+i;
			String predType = "predator";
			String id = predType+"_"+predName;
			String predSpecies = "great white";
			int predEnergy = 100;
			x = 0;
			y = 0;
			
			Shark predShark = new Shark( id, predName, predType, predSpecies, predEnergy, x, y);
			
			
			predators.add( predShark );
		}
		
		for (int i = 0; i < this.numPrey; i++) {
			String preyName = "fish"+i;
			String preyType = "prey";
			String id = preyType+"_"+preyName;
			String preySpecies = "gold fish";
			int preyEnergy = 100;
			x = 0;
			y = 0;
			
			Fish preyFish = new Fish( id, preyName, preyType, preySpecies, preyEnergy, x, y);
			
			prey.add( preyFish );
		}
	}
	
	
	public void runSimulation(int time)
	{
		System.out.println("PPModel::runSimulation: Starting run for " + time + " time.");
		
		for(int currentTime = time; currentTime > 0; currentTime--)
		{
			System.out.println("PPModel::runSimulation: TIME STEP: " + currentTime);
			
			//for(int currentActor =);
				
		}
	}
	
	
	public int getXCoordSize()
	{
		return this.xSize;
	}
	
	public int getYCoordSize()
	{
		return this.ySize;
	}
	
	public void displayInfo()
	{
		System.out.println( "PPModel has "+this.numPred+" Predators and "+this.numPrey+" Prey on a field of "+xSize+"x"+ySize );
		
	}
}
