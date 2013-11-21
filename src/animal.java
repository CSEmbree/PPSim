


public class Animal extends SimActor
{
	String name;
	String type;
	String state;
	int energy;
	boolean alive;
	
	
	public Animal()
	{
		this("", "", "", 100, 0, 0);
	}
	
	/*
	 * name: Unique identifier for fish
	 * type: Predator or Prey
	 * energy: life force of the PPModel (100 Healthy - 0 Dead)
	 */
	public Animal(String id, String name, String type, int energy, int x, int y)
	{
		super(id, x, y);
		this.name = name;
		this.type = type;
		this.energy = energy;
		
		if(energy <= 0)
			System.out.println("Animal::Animal: WARNING: Created PPModel "+name+" with "+energy+" energy will is dead.");
		
		
		//set life state based on energy
		this.state = determineState(this.energy);
		this.alive = determineLife(this.energy);

		
		System.out.println("Animal::Animal: Created Animal with: Name: "+this.name
				+", Type: "+this.type
				+", Energy: "+this.energy);
	}
	
	String determineState(int energy)
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
	
	boolean determineLife(int energy)
	{
		if(this.energy > 0)
			return true; //alive
		else
			return false; //dead
	}
	
	int compareEnergy(Animal otherAnimal)
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
	
	
	String getName()
	{
		return this.name;
	}
	
	String getType()
	{
		return this.type;
	}
	
	int getEnergy()
	{
		return this.energy;
	}
	
	String getState()
	{
		return this.state;
	}
	
	boolean getLifeStatus()
	{
		return this.alive;
	}
	
	String getid()
	{
		return super.getid();
	}
	
	int getXCoord()
	{
		return super.getXCoord();
	}
	
	int getYCoord()
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
