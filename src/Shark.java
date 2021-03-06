//Cameron Embree, Gradon Faulkner

public class Shark extends Animal
{
	private String species; //identification
	
	
	public Shark() {
		super();
	}
	
	public Shark(String id, String name, String type, String species, double energy, double visionRange, double maxDistTravel, double x, double y) {
		super(id, name, type, energy, visionRange, maxDistTravel, x, y);
		
		this.setSpecies(species);

		System.out.println("shark::shark: Created a shark of the species: "+species);
	}
	
	private void setSpecies(String species) {
		this.species = species;
	}
	
	public String getName() {
		return super.getName();
	}
	
	public String getType() {
		return super.getType();
	}
	
	public String getSpecies() {
		return this.species;
	}
	
	public double getEnergy() {
		return super.getEnergy();
	}
	
	public String getState() {
		return super.getState();
	}
	
	public boolean getLifeStatus() {
		return super.getLifeStatus();
	}
	
	public void displayInfo() {
		System.out.println( "Shark of species: "+this.species );
		super.displayInfo();
	}
}
