
public class Fish extends Animal
{
	private String species;
	
	
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
	public Fish(String id, String name, String type, String species, int energy, int maxDistTravel, int x, int y)
	{
		super(id, name, type, energy, maxDistTravel, x, y);
		setSpecies(species);

		System.out.println("Fish::Fish: Created a fish of the species: "+species);
	}
	
	
	private void setSpecies(String species)
	{
		this.species = species;
	}
	
	
	public String getName()
	{
		return super.getName();
	}
	
	public String getType()
	{
		return super.getType();
	}
	
	public String getSpecies()
	{
		return this.species;
	}
	
	public int getEnergy()
	{
		return super.getEnergy();
	}
	
	public String getState()
	{
		return super.getState();
	}
	
	public boolean getLifeStatus()
	{
		return super.getLifeStatus();
	}
	
	public void displayInfo()
	{
		System.out.println( "Fish of species: "+this.species );
		
		super.displayInfo();
	}

}
