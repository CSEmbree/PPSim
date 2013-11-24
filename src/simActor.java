

public class SimActor
{
	private int x, y; //coordinates of this actor in the sim
	private int destX, destY; //coordinated of this actors next destination
	private String id;
	
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
	
	
	public void setDestination(int destX, int destY)
	{
		setXDest(destX);
		setYDest(destY);
	}
	
	private void setXDest(int destX)
	{
		this.destX = destX;
	}
	
	private void setYDest(int destY)
	{
		this.destY = destY;
	}
	
	public int getXCoord()
	{
		return this.x;
	}
	
	public int getYCoord()
	{
		return this.y;
	}
	
	public int getXDest()
	{
		return this.destX;
	}
	
	public int getYDest()
	{
		return this.destY;
	}
	
	public String getid()
	{
		return this.id;
	}

}
