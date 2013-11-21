
public class fish extends animal
{
	public String species;
	
	
	public fish() 
	{
		super(); //default constructor
	}
	
	/*
	 * name: Unique identifier for fish
	 * type: Predator or Prey
	 * species: Type of fish
	 * energy: life force of the animal (100 Healthy - 0 Dead)
	 */
	public fish(String name, String type, String species, int energy)
	{
		super(name, type, energy);
		
		this.species = species;

		System.out.println("fish::fish: Created a fish of the species: "+species);
	}
	
	
	String getName()
	{
		return super.getName();
	}
	
	String getType()
	{
		return super.getType();
	}
	
	String getSpecies()
	{
		return this.species;
	}
	
	int getEnergy()
	{
		return super.getEnergy();
	}
	
	String getState()
	{
		return super.getState();
	}
	
	boolean getLifeStatus()
	{
		return super.getLifeStatus();
	}
	
	public void displayInfo()
	{
		System.out.println( "Fish of species: "+this.species );
		
		super.displayInfo();
	}

}
