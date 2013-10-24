

public class animal 
{
	String name;
	String type;
	String activity;
	String state;
	boolean alive;
	
	
	public animal()
	{
		this("","");
	}
	
	public animal(String name, String type)
	{
		this(name, type, "idle", "full", true);
	}
	
	public animal(String name, String type, String activity, String state, boolean alive)
	{
		this.name = name;
		this.type = type;
		this.activity = activity;
		this.state = state;
		this.alive = alive;
		
		System.out.println("animal::animal: Created animal with: Name: "+this.name
				+", Type: "+this.type
				+", Activity: "+this.activity
				+", state: "+this.state
				+", alive: "+this.alive);
	}
	
	public void displayInfo()
	{
		System.out.println("Animal with: Name: "+this.name
				+", Type: "+this.type
				+", Activity: "+this.activity
				+", state: "+this.state
				+", alive: "+this.alive);
	}
	
}
