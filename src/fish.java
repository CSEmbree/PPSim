
public class Fish extends Animal
{
	public String species;
	
	
	public Fish() 
	{
		super(); //default constructor
	}
	
	/*
	 * name: Unique identifier for fish
	 * type: Predator or Prey
	 * species: Type of fish
	 * energy: life force of the PPModel (100 Healthy - 0 Dead)
	 */
	public Fish(String id, String name, String type, String species, int energy, int x, int y)
	{
		super(id, name, type, energy, x, y);
		
		this.species = species;

		System.out.println("Fish::Fish: Created a fish of the species: "+species);
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
