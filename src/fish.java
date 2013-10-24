
public class fish extends animal
{
	public String species;
	
	
	public fish() 
	{
		super();
	}
	
	public fish(String name, String type, String species)
	{
		super(name, type);
	}
	
	public fish(String name, String type, String species, String activity, String state, boolean alive)
	{
		super(name, type, activity, state, alive);
		
		this.species = species;

		System.out.println("fish::fish: Created a fish of the species: "+species);
	}
	
	public void displayInfo()
	{
		System.out.println( "Fish of species: "+this.species );
		
		super.displayInfo();
	}

}
