//Cameron Embree, Gradon Faulkner
import java.util.Random;


public class Animal extends SimActor
{
	private String name; //identification
	private String type; //predator or prey
	private String state; //hungry, dead, eating, moving, etc. Used for printing state of system
	private double energy; //life force level. 0=dead, 100=max healthy. State of energy determines goals, hunting, moving, etc.
	private boolean alive; //alive or dead, helps quickly determine state for cleanup
	private double maxDistTravel; //limit on travel capability of an animal
	
	
	public Animal()
	{
		this("", "", "", 100, 0.0, 0.0, 0.0);
	}
	
	/*
	 * name: Unique identifier for fish
	 * type: Predator or Prey
	 * energy: life force of the PPModel (100 Healthy - 0 Dead)
	 */
	public Animal(String id, String name, String type, double energy, double maxDistTravel, double x, double y)
	{
		super(id, x, y);
		this.setName(name);
		this.setType(type);
		this.setEnergy(energy);
		this.setMaxDistTravel(maxDistTravel);
		
		if(energy <= 0.0)
			System.out.println("Animal::Animal: WARNING: Created PPModel "+name+" with "+energy+" energy will is dead.");
		
		
		//set life state based on energy
		this.state = determineState(this.energy);
		this.alive = determineLife(this.energy);

		
		System.out.println("Animal::Animal: Created Animal with: Name: "+this.name
				+", Type: "+this.type
				+", Energy: "+this.energy);
	}
	
	
	public void chooseNewDestination(double xLimit, double yLimit, double timeStepPartitions)
	{		
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		//rand.setSeed(arg0); //to-do - add SEED value of time
		
		double distMoveX, distMoveY;
		double newCoordX, newCoordY;
		
		
		//pick a new destination x coordinate 
		double randX = rand.nextDouble();
		distMoveX = (randX*2*maxDistTravel) - maxDistTravel;
		newCoordX = getXCoord()+distMoveX;
		if(newCoordX < 0)
			newCoordX+=Math.abs(distMoveX);
		else if(newCoordX > xLimit)
			newCoordX-=Math.abs(distMoveX);
		
		
		//pick a new destination y coordinate 
		double randY = rand.nextDouble();
		distMoveY = (randY*2*maxDistTravel) - maxDistTravel;
		newCoordY = getYCoord()+distMoveY;
		if(newCoordY < 0)
			newCoordY+=Math.abs(distMoveY);
		else if(newCoordY > yLimit)
			newCoordY-=Math.abs(distMoveY);
		
		
		//set the new x and y coordinate formally
		setDestination(newCoordX, newCoordY, timeStepPartitions);
	}
	
	public void setXYPosition(double x, double y)
	{
		super.setX(x);
		super.setY(y);
	}
	
	public void setDestination(double xDest, double yDest)
	{		
		//careful not to allow destination set that is out of our field!!
		//use 'chooseNewDestination' function to avoid out of range!
		
		super.setDestination(xDest, yDest);	
	}
	
	
	public String determineState(double energy)
	{
		String currentState;
		
		if(energy > 100.0)
			currentState = "FULL";
		else if(energy > 75.0)
			currentState = "CONTENT";
		else if(energy > 50.0)
			currentState = "HUNGRY";
		else
			currentState = "STARVING";
		
		return currentState;
	}
	
	public boolean determineLife(double energy)
	{
		if(this.energy > 0)
			return true; //alive
		else
			return false; //dead
	}
	
	public int compareEnergy(Animal otherAnimal)
	{
		int compResult = 0;
		
		if(this.energy > otherAnimal.getEnergy())
			compResult = 1;
		else if(this.energy < otherAnimal.getEnergy())
			compResult = -1;
		else
			compResult = 0;
		
		return compResult;
	}
	
	public void setMaxDistTravel(double maxDist)
	{
		this.maxDistTravel = maxDist;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setEnergy(double energy)
	{
		this.energy = energy;
	}
	
	
	public String getName()
	{
		return this.name;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public double getEnergy()
	{
		return this.energy;
	}
	
	public String getState()
	{
		return this.state;
	}
	
	public boolean getLifeStatus()
	{
		return this.alive;
	}
	
	public String getid()
	{
		return super.getid();
	}
	
	public double getXCoord()
	{
		return super.getXCoord();
	}
	
	public double getYCoord()
	{
		return super.getYCoord();
	}
	
	public double getXDest()
	{
		return super.getXDest();
	}
	
	public double getYDest()
	{
		return super.getYDest();
	}
	
	public double getDeltaX()
	{
		return super.getDeltaX();
	}
	
	public double getDeltaY()
	{
		return super.getDeltaY();
	}
	
	public void displayInfo()
	{
		System.out.println("Animal with: Name: "+this.name
				+", Type: "+this.type
				+", State: "+this.state
				+", Energy: "+this.energy
				+", Alive: "+this.alive);
	}
	
}
