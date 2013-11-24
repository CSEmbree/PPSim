import java.util.Random;




public class Animal extends SimActor
{
	private String name;
	private String type;
	private String state;
	private int energy;
	private boolean alive;
	private int maxDistTravel;
	
	
	public Animal()
	{
		this("", "", "", 100, 0, 0, 0);
	}
	
	/*
	 * name: Unique identifier for fish
	 * type: Predator or Prey
	 * energy: life force of the PPModel (100 Healthy - 0 Dead)
	 */
	public Animal(String id, String name, String type, int energy, int maxDistTravel, int x, int y)
	{
		super(id, x, y);
		setName(name);
		setType(type);
		setEnergy(energy);
		setMaxDistTravel(maxDistTravel);
		
		if(energy <= 0)
			System.out.println("Animal::Animal: WARNING: Created PPModel "+name+" with "+energy+" energy will is dead.");
		
		
		//set life state based on energy
		this.state = determineState(this.energy);
		this.alive = determineLife(this.energy);

		
		System.out.println("Animal::Animal: Created Animal with: Name: "+this.name
				+", Type: "+this.type
				+", Energy: "+this.energy);
	}
	
	
	public void chooseNewDestination(int xLimit, int yLimit)
	{		
		Random rand = new Random();
		//rand.setSeed(arg0); //to-do - add SEED value of time
		
		int distMoveX, distMoveY;
		int newCoordX, newCoordY;
		
		//pick a new destination x and y coordinate
		distMoveX = rand.nextInt(2*maxDistTravel) - maxDistTravel;
		newCoordX = getXCoord()+distMoveX;
		if(newCoordX < 0)
			newCoordX+=Math.abs(distMoveX);
		else if(newCoordX > xLimit)
			newCoordX-=Math.abs(distMoveX);
		
		
		distMoveY = rand.nextInt(2*maxDistTravel) - maxDistTravel;
		newCoordY = getYCoord()+distMoveY;
		if(newCoordY < 0)
			newCoordY+=Math.abs(distMoveY);
		else if(newCoordY > yLimit)
			newCoordY-=Math.abs(distMoveY);
		
		
		setDestination(newCoordX, newCoordY);
	}
	
	
	public void setDestination(int xDest, int yDest)
	{		
		//careful not to allow destination set that is out of our field!!
		//use 'chooseNewDestination' function to avoid out of range!
		
		super.setDestination(xDest, yDest);	
	}
	
	
	public String determineState(int energy)
	{
		String currentState;
		
		if(energy > 100)
			currentState = "FULL";
		else if(energy > 75)
			currentState = "CONTENT";
		else if(energy > 50)
			currentState = "HUNGRY";
		else
			currentState = "STARVING";
		
		return currentState;
	}
	
	public boolean determineLife(int energy)
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
	
	public void setMaxDistTravel(int maxDist)
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
	
	public void setEnergy(int energy)
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
	
	public int getEnergy()
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
	
	public int getXCoord()
	{
		return super.getXCoord();
	}
	
	public int getYCoord()
	{
		return super.getYCoord();
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
