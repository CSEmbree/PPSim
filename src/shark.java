

public class Shark extends Animal
{
	public String species;
	
	
	public Shark() 
	{
		super();
	}
	
	public Shark(String id, String name, String type, String species, int energy, int x, int y)
	{
		super(id, name, type, energy, x, y);
		
		this.species = species;

		System.out.println("shark::shark: Created a shark of the species: "+species);
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
		System.out.println( "Shark of species: "+this.species );
		
		super.displayInfo();
	}
	
}
