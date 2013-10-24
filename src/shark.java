import sun.tools.tree.SuperExpression;


public class shark extends animal{
	public String species;
	
	
	public shark() 
	{
		super();
	}
	
	public shark(String name, String type, String species)
	{
		super(name, type);
	}
	
	public shark(String name, String type, String species, String activity, String state, boolean alive)
	{
		super(name, type, activity, state, alive);
		
		this.species = species;

		System.out.println("shark::shark: Created a shark of the species: "+species);
	}
	
	public void displayInfo()
	{
		System.out.println( "Shark of species: "+this.species );
		
		super.displayInfo();
	}
	
}
