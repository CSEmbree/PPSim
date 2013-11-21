import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;



public class animal 
{
	String name;
	String type;
	String state;
	int energy;
	boolean alive;
	
	
	public animal()
	{
		this("", "", 100);
	}
	
	/*
	 * name: Unique identifier for fish
	 * type: Predator or Prey
	 * energy: life force of the animal (100 Healthy - 0 Dead)
	 */
	public animal(String name, String type, int energy)
	{
		this.name = name;
		this.type = type;
		this.energy = energy;
		
		//set life state based on energy
		this.state = determineState(this.energy);
		this.alive = determineLife(this.energy);

		
		System.out.println("animal::animal: Created animal with: Name: "+this.name
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
	
	int compareEnergy(animal otherAnimal)
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
	
	public void displayInfo()
	{
		System.out.println("Animal with: Name: "+this.name
				+", Type: "+this.type
				+", State: "+this.state
				+", Energy: "+this.energy
				+", Alive: "+this.alive);
	}
	
}
