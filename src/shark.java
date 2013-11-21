

public class shark extends animal
{
	public String species;
	
	
	public shark() 
	{
		super();
	}
	
	public shark(String name, String type, String species, int energy)
	{
		super(name, type, energy);
		
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
