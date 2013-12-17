//Cameron Embree, Gradon Faulkner
import java.util.Random;

public class Animal extends SimActor
{
	final double FULL = 100.0;
	final double CONTENT = 90.0;
	final double HUNGRY = 80.0;
	final double STARVING = 50.0;
	final double DIEING = 20.0;
	final double DEAD = 0.0;
	
	private String name; //identification
	private String type; //predator or prey
	private String state; //hungry, dead, eating, moving, etc. Used for printing state of system
	private double energy; //life force level. 0=dead, 100=max healthy. State of energy determines goals, hunting, moving, etc.
	private boolean alive; //alive or dead, helps quickly determine state for cleanup
	private double visionRange; //how far an animal can see. used for detection of resources.
	private double maxDistTravel; //limit on travel capability of an animal
	
	
	public Animal() {
		this("", "", "", 100, 0.0, 0.0, 0.0, 0.0);
	}
	
	/*
	 * name: Unique identifier for fish
	 * type: Predator or Prey
	 * energy: life force of the PPModel (100 Healthy - 0 Dead)
	 */
	public Animal(String id, String name, String type, double energy, double visionRange, double maxDistTravel, double x, double y) {
		super(id, x, y);
		this.setName(name);
		this.setType(type);
		this.setEnergy(energy);
		this.setVisionRange(visionRange);
		this.setMaxDistTravel(maxDistTravel);
		
		if(energy <= 0.0)
			System.out.println("Animal::Animal: WARNING: Created PPModel "+name+" with "+energy+" energy will is dead.");
		
		
		//set life state based on energy
		this.updateLifeStatus(); //sets 'state' and 'alive' based on energy

		
		System.out.println("Animal::Animal: Created Animal with: Name: "+this.name
				+", Type: "+this.type
				+", Energy: "+this.energy);
	}
	
	
	public void chooseNewDestination(double xLimit, double yLimit, double timeStepPartitions) {		
		Random rand = new Random();
		rand.setSeed(System.nanoTime());
		
		double distMove;
		double newCoordX, newCoordY;
		double randVal = rand.nextDouble();
		
		
		//pick a new destination x coordinate 
		randVal = rand.nextDouble();
		distMove = (randVal*2*maxDistTravel) - maxDistTravel;
		newCoordX = getXCoord() + distMove;

		//force move inward if next destination would be out of X bounds
		if(newCoordX < 0) {
			newCoordX = getXCoord() + Math.abs(distMove); //force more inward
		}
		else if( newCoordX > xLimit) {
			newCoordX = getXCoord() - Math.abs(distMove); //force more inward
		}
		
		
		//pick a new destination y coordinate 
		randVal = rand.nextDouble();
		distMove = (randVal*2*maxDistTravel) - maxDistTravel;
		newCoordY = getYCoord() + distMove;
		
		//force move inward if next destination would be out of Y bounds
		if(newCoordY < 0) {
			newCoordY = getYCoord() + Math.abs(distMove); //force more inward
		}
		else if(newCoordY > yLimit) {
			newCoordY = getYCoord() - Math.abs(distMove); //force move inward
		}
	
		
		//set the new x and y coordinate formally
		setDestination(newCoordX, newCoordY, timeStepPartitions);
	}
	
	public void setXYPosition(double x, double y) {
		super.setX(x);
		super.setY(y);
		setChanged(); 		//This is the code that calls the Update method for GUI 
		notifyObservers();	//
	}
	
	public void setDestination(double xDest, double yDest) {		
		//careful not to allow destination set that is out of our field!!
		//use 'chooseNewDestination' function to avoid out of range!
		
		super.setDestination(xDest, yDest);	
	}
	
	public void setDestination(double xDest, double yDest, double timeStepPartitions) {
		super.setDestination(xDest, yDest, timeStepPartitions);
	}
	
	public String determineState(double energy) {
		String currentState;
		
		if(energy >= FULL)
			currentState = "FULL";
		else if(energy > CONTENT)
			currentState = "CONTENT";
		else if(energy > HUNGRY)
			currentState = "HUNGRY";
		else if(energy > STARVING)
			currentState = "STARVING";
		else if(energy > DIEING)
			currentState = "DIEING";
		else
			currentState = "DEAD";
		
		return currentState;
	}
	
	public boolean determineLife(double energy) {
		if(this.energy > 0.0)
			return true; //alive
		else
			return false; //dead
	}
	
	public int compareEnergy(Animal otherAnimal) {
		int compResult = 0;
		
		if(this.energy > otherAnimal.getEnergy())
			compResult = 1;
		else if(this.energy < otherAnimal.getEnergy())
			compResult = -1;
		else
			compResult = 0;
		
		return compResult;
	}
	
	
	private void updateLifeStatus() {
		this.setState( determineState(energy) );
		this.setAlive( determineLife(energy) );
	}
	
	public void setMaxDistTravel(double maxDist) {
		this.maxDistTravel = maxDist;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private void setAlive(boolean lifeStatus) {
		this.alive = lifeStatus;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setEnergy(double energy) {
		this.energy = energy;
		
		updateLifeStatus();
	}
	
	public void removeEnergy(double energy) {
		this.energy -= energy;
		
		updateLifeStatus();
	}
	
	public boolean inConsumptionRange(Animal otherAn) {
		boolean isConsumptionRange = false;
		
		if( this.distance(this.getXCoord(), this.getYCoord(), otherAn.getXCoord(), otherAn.getYCoord()) <= 20.0 ) { //TODO - dont make hardcoded
			isConsumptionRange = true;
		}
		
		return isConsumptionRange;
	}
	
	//TODO - balance energy removal based on distance traveled
	//currently - every 10 units is 1 energy
	public double removeEnergyFromTravel(double distTravel) {
		double energyToRemove = 0.0;
		
		energyToRemove = Math.abs(distTravel) / 10.0; //<----Needs Balancing
		
		removeEnergy(energyToRemove);
		
		return energyToRemove;
	}
	
	public boolean canSee(Animal an) {
		
		if( this.distance(this.getXCoord(), this.getYCoord(), an.getXCoord(), an.getYCoord()) <= this.getVisionRange()) {
			System.out.println("Animal::canSee: "+this.getName()
					+"("+this.getType()+") can see "
					+an.getName()+"("+an.getType()+")");
			
			return true;
		}
		else {
			return false;
		}
	}
	
	private double getConsumptionEnergy() {
		return this.getEnergy()*0.50; //TODO - Set appropriate energy consumption amount
	}
	
	public void consume(Animal otherAn) {
		this.addEnergy(otherAn.getConsumptionEnergy());
	}
	
	private void addEnergy(double energy) {
		double newEnergy = this.getEnergy() + energy;
		setEnergy(newEnergy);
	}
	
	public void setIsComsumed() {
		this.setEnergy(0.0);
	}
	
	private void setVisionRange(double visionRange) {
		this.visionRange = visionRange;
	}
	
	private void setState(String state) {
		this.state = state;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getEnergy() {
		return this.energy;
	}
	
	public String getState() {
		return this.state;
	}
	
	public boolean getLifeStatus() {
		return this.alive;
	}
	
	public boolean isHungry() {
		if(energy <= this.HUNGRY) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getid() {
		return super.getid();
	}
	
	public double getXCoord() {
		return super.getXCoord();
	}
	
	public double getYCoord() {
		return super.getYCoord();
	}
	
	public double getXDest() {
		return super.getXDest();
	}
	
	public double getYDest() {
		return super.getYDest();
	}
	
	public double getDeltaX() {
		return super.getDeltaX();
	}
	
	public double getDeltaY() {
		return super.getDeltaY();
	}
	
	public double getVisionRange() {
		return this.visionRange;
	}
	
	public void displayLifeState() {
		System.out.println("Animal: Name: \t\t"+this.name
				+"\n\tType: \t\t"+this.type
				+"\n\tState: \t\t"+this.state+", ("+this.energy+")"
				+"\n\tAlive: \t\t"+this.alive);
	}
	
	public void displayInfo()
	{
		System.out.println("Animal: Name: \t\t"+this.name
				+"\n\tType: \t\t"+this.type
				+"\n\tState: \t\t"+this.state
				+"\n\tEnergy: \t"+this.energy
				+"\n\tVisionRange: \t"+this.visionRange
				+"\n\tMaxDistTravel: \t"+this.maxDistTravel
				+"\n\tAlive: \t\t"+this.alive);
	}	
}
