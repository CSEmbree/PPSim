//Cameron Embree, Gradon Faulkner
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


public class PPModel extends SimActor {
	// private final double DEF_PRED_SIZE = 10;
	// private final double DEF_PREY_SIZE = 10;

	public ArrayList<Animal> predators = new ArrayList<Animal>(); // set of all
																	// predators
																	// in
																	// simulation
	public ArrayList<Animal> prey = new ArrayList<Animal>(); // set of all prey
																// in simulation
	public ArrayList<Animal> deadAnimals = new ArrayList<Animal>();
	
	int numPred = 0; // number of prey in the simulation currently, convenience
						// variable
	int numPrey = 0; // number of predators in the simulation currently,
						// convenience variable

	double xSize = 100.0; // max x dimention of simulation (x=0, 1,...,xSize)
	double ySize = 100.0; // max y dimention of simulation (y=0, 1,...,ySize)
	DecimalFormat df = new DecimalFormat("#.00"); //for nice formatting of local double's
	

	public PPModel() {
		this(0, 0, 100.0, 100.0);
	}

	public PPModel(int numPred, int numPrey, double xSize, double ySize) {
		// set initial number of predators and prey
		this.setNumPred(numPred);
		this.setNumPrey(numPrey);

		// set size for field of play
		if (xSize > 0.0) {
			this.setXSize(xSize);
		}
		else {
			System.out.println("PPModel::PPModel: ERROR: An invalid value for xSize= "
							+ xSize + " was enountered. xSize must be >= 1.");
			System.exit(0); // exit gracefully on error
		}

		if (ySize > 0.0) {
			this.setYSize(ySize);
		}
		else {
			System.out.println("PPModel::PPModel: ERROR: An invalid value for ySize= "
							+ ySize + " was enountered. xSize must be >= 1.");
			System.exit(0); // exit gracefully on error
		}

		Random rand = new Random();
		rand.setSeed(System.nanoTime());

		

		// create that number of preds and prey
		double xPos, yPos, energy, visionRange, maxDistTravel;
		//String name, type, id, species;
		
		for (int i = 0; i < this.numPred; i++) {
			String predName = "shark" + i;
			String predType = "predator";
			String id = predType + "_" + predName;
			String predSpecies = "great white";
			energy = 100.0;
			visionRange = 50.0; //how far a predator can see for prey
			maxDistTravel = 75.0; //predators cannot move any farther than this <------------
			xPos = rand.nextInt((int) xSize);
			yPos = rand.nextInt((int) ySize);

			Shark predShark = new Shark(id, predName, predType, predSpecies,
					energy, visionRange, maxDistTravel, xPos, yPos);

			predators.add(predShark);
		}

		for (int i = 0; i < this.numPrey; i++) {
			String preyName = "fish" + i;
			String preyType = "prey";
			String id = preyType + "_" + preyName;
			String preySpecies = "gold fish";
			energy = 100;
			visionRange = 25.0;
			maxDistTravel = 25.0; //prey cannot move any farther than this <------------
			xPos = rand.nextInt((int) xSize);
			yPos = rand.nextInt((int) ySize);

			Fish preyFish = new Fish(id, preyName, preyType, preySpecies,
					energy, visionRange, maxDistTravel, xPos, yPos);

			prey.add(preyFish);
		}
	}

	public void runSimulation(int time, double timeStepPartitions) {
		System.out.println("PPModel::runSimulation: Starting run for " + time
				+ " time.");

		for (int currentTime = time; currentTime > 0; currentTime--) {
			System.out.println("PPModel::runSimulation: TIME STEP: "
					+ currentTime);

			cleanAndPlan(timeStepPartitions);

			//Time Delay for Movement on GUI
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
			}

			activateAndMove(timeStepPartitions);

			//Time Delay for Movement on GUI
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public void cleanAndPlan(double timeStepPartitions) {
		System.out.println("PPModel::cleanAndPlan: Cleaning updating any dead actors and deciding their next move");
		boolean removed;
		Animal deadAnimal;

		// remove any dead predators and determine their next move
		removed = true;
		while (removed == true) {
			removed = false;
			for (int currentActorIndex = 0; currentActorIndex < predators
					.size(); currentActorIndex++) {
				if (predators.get(currentActorIndex).getEnergy() <= 0) {
					deadAnimal = predators.remove(currentActorIndex);
					deadAnimals.add( deadAnimal );
					deadAnimals.get(deadAnimals.size()-1).setXYPosition(deadAnimals.get(deadAnimals.size()-1).getXCoord(),deadAnimals.get(deadAnimals.size()-1).getYCoord() );
					removed = true; // if clean up was performed, run the whole
									// loop again
				}
			}
		}

		for (int currentActorIndex = 0; currentActorIndex < predators.size(); currentActorIndex++) {
			predators.get(currentActorIndex).chooseNewDestination(xSize, ySize, timeStepPartitions);
		}

		// remove any dead prey and determine their next move
		removed = true;
		while (removed == true) {
			removed = false;
			for (int currentActorIndex = 0; currentActorIndex < prey.size(); currentActorIndex++) {
				if (prey.get(currentActorIndex).getEnergy() <= 0) {
					prey.get(currentActorIndex).setXYPosition(prey.get(currentActorIndex).getXCoord()+0.1 , prey.get(currentActorIndex).getYCoord()+0.1);
					deadAnimal = prey.remove(currentActorIndex);
					deadAnimals.add( deadAnimal );
					deadAnimals.get(deadAnimals.size()-1).setXYPosition(deadAnimals.get(deadAnimals.size()-1).getXCoord(),deadAnimals.get(deadAnimals.size()-1).getYCoord() );  
					removed = true; // if clean up was performed, run the whole
									// loop again
				}
			}
		}

		for (int currentActorIndex = 0; currentActorIndex < prey.size(); currentActorIndex++) {
			prey.get(currentActorIndex).chooseNewDestination(xSize, ySize, timeStepPartitions);
		}
		
		//TODO - update dead animals here?
		//setChanged(); 		//This is the code that calls the Update method for GUI 
		//notifyObservers();	//
		
		System.out.println("TEST: DEAD COUNT: "+deadAnimals.size()); //TEST - make sure dead are accounted for
		for (Animal an : deadAnimals) {
			an.displayInfo();
		}
		
	}

	private void activateAndMove(double timeStepPartitions) {
		System.out.println("PPModel::activateAndMove: Starting to move things...");

		
		double oldXCoord, oldYCoord, newXCoord, newYCoord, distTravel;
		
		// TODO - account for delta move's energy consumption
		for (int i = 0; i < timeStepPartitions; i++) {
			 //System.out.println("PPModel::activateAndMove: Partial Iteration "+i+" of "+timeStepPartitions);

			this.checkforNearbyPrey(timeStepPartitions);
			
			for (Animal an : predators) {
				oldXCoord = an.getXCoord();
				oldYCoord = an.getYCoord();
				newXCoord = oldXCoord + an.getDeltaX();
				newYCoord = oldYCoord + an.getDeltaY();
				
				an.setXYPosition(newXCoord, newYCoord);
				
				distTravel = an.distance(oldXCoord, oldYCoord, newXCoord, newYCoord);
				an.removeEnergyFromTravel(distTravel); //TODO - need this but must be balanced
			}

			for (Animal an : prey) {
				oldXCoord = an.getXCoord();
				oldYCoord = an.getYCoord();
				newXCoord = oldXCoord + an.getDeltaX();
				newYCoord = oldYCoord + an.getDeltaY();
				
				an.setXYPosition(newXCoord, newYCoord);
				
				distTravel = an.distance(oldXCoord, oldYCoord, newXCoord, newYCoord);
				an.removeEnergyFromTravel(distTravel); //TODO - need this but must be balanced
			}
			
			//TODO - consume resources if pred is close enough to prey and hungry, etc
			
			//Time delay for smooth movement on GUI
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				return;
			}
		}
		
		//TODO - remove energy based on movement distance
		//remove energy based on movement
		this.removeEnergy(10.0); //TODO - remove this temporary testing energy removal funct
		
		this.displayInfo(); // for state debug checking

		System.out.println("PPModel::activateAndMove: Stopping moving things...");
	}
	
	private void checkforNearbyPrey(double timeStepPartitions) {
		for (Animal predAn : predators) {
			for (Animal preyAn : prey) {
				if( predAn.canSee(preyAn) && preyAn.getLifeStatus()==true && predAn.isHungry()==true ) {
					//System.exit(0); //TEST
					
					if( predAn.inConsumptionRange(preyAn) ) {
						predAn.consume(preyAn);
						preyAn.setIsComsumed();
					}

					//TODO - set appropriate location for pred to move toward prey
					predAn.setDestination(preyAn.getXCoord(), preyAn.getYCoord(), timeStepPartitions);
				}
			}
			
			predAn.displayLifeState(); //TEST - temporary TODO - remove this when done testing pred to prey movement
		}
		//System.exit(0); //TEST
	}
	
	
	private void removeEnergy(double energy) {
		for (Animal an : prey) {
			an.removeEnergy(energy);
		}
		
		for (Animal an : predators) {
			an.removeEnergy(energy);
		}
	}

	public double getXCoordSize() {
		return this.xSize;
	}

	public double getYCoordSize() {
		return this.ySize;
	}

	private void setNumPrey(int preyCount) {
		this.numPrey = preyCount;
	}

	private void setNumPred(int predCount) {
		this.numPred = predCount;
	}

	private void setXSize(double xSize) {
		this.xSize = xSize;
	}

	private void setYSize(double ySize) {
		this.ySize = ySize;
	}

	public void displayPredInfo() {
		for (Animal an : predators) {
			System.out.println("\tPred Name:" + an.getName() + " at position ("
					+ df.format(an.getXCoord()) + "," + df.format(an.getYCoord()) + ")");
		}
	}

	public void displayPreyInfo() {
		for (Animal an : prey) {
			System.out.println("\tPrey Name:" + an.getName() + " at position ("
					+ df.format(an.getXCoord()) + "," + df.format(an.getYCoord()) + ")");
		}
	}

	public void displayInfo() {
		System.out.println("PPModel has " + this.numPred + " Predators and "
				+ this.numPrey + " Prey on a field of " + xSize + "x" + ySize);
		this.displayPredInfo();
		this.displayPreyInfo();

	}
}
