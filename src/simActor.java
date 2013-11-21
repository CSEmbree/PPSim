


public class SimActor
{
	int x, y; //coordinates of this actor in the sim
	String id;
	
	public SimActor()
	{
		this( "", 0, 0 );
	}
	
	/*
	 * id: Identification for this actor
	 * x: x coordiante in sim, must be in valid range
	 * y: y coordiante in sim, must be in valid range
	 */
	public SimActor(String id, int x, int y)
	{
		System.out.println("SimActor::SimActor: Creating actor with id: "+id+" at position ("+x+","+y+")");
		
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	int getXCoord()
	{
		return this.x;
	}
	
	int getYCoord()
	{
		return this.y;
	}
	
	String getid()
	{
		return this.id;
	}

}
