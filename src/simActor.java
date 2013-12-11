//test 

public class SimActor
{
	private String id; //identification
	private double x, y; //coordinates of this actor in the sim
	private double destX, destY; //coordinated of this actors next destination
	private double deltaX, deltaY; //part of the distance that from x to DestX and y and Dest Y that is made at each partitioned time step

	
	public SimActor()
	{
		this( "", 0.0, 0.0 );
	}
	
	/*
	 * id: Identification for this actor
	 * x: x coordiante in sim, must be in valid range
	 * y: y coordiante in sim, must be in valid range
	 */
	public SimActor(String id, double x, double y)
	{
		System.out.println("SimActor::SimActor: Creating actor with id: "+id+" at position ("+x+","+y+")");
		
		setID(id);
		setX(x);
		setY(y);
	}
	
	
	private void setDeltaX(double dx)
	{
		this.deltaX = dx;
	}
	
	private void setDeltaY(double dy)
	{
		this.deltaY = dy;
	}
	
	private void setX(double x)
	{
		this.x = x;
	}
	
	private void setY(double y)
	{
		this.y = y;
	}
	
	private void setID(String id)
	{
		this.id = id;
	}
	
	public void setDestination(double destX, double destY)
	{
		setXDest(destX);
		setYDest(destY);
	}
	
	public void setDestination(double destX, double destY, double timeStepPartitions)
	{
		setXDest(destX, timeStepPartitions);
		setYDest(destY, timeStepPartitions);
	}
	
	private void setXDest(double destX)
	{
		this.destX = destX;
		setDeltaX(getXDistToTravel() / 1.0);
	}
	
	private void setYDest(double destY)
	{
		this.destY = destY;
		setDeltaY(getYDistToTravel() / 1.0);
	}
	
	private void setXDest(double destX, double partitions)
	{
		this.destX = destX;
		this.deltaX = getXDistToTravel() / partitions;
	}
	
	private void setYDest(double destY, double partitions)
	{
		this.destY = destY;
		this.deltaY = getYDistToTravel() / partitions;
	}
	
	public double getXCoord()
	{
		return this.x;
	}
	
	public double getYCoord()
	{
		return this.y;
	}
	
	public double getXDest()
	{
		return this.destX;
	}
	
	public double getYDest()
	{
		return this.destY;
	}
	
	public String getid()
	{
		return this.id;
	}
	
	public double getXDistToTravel()
	{
		return distance(x, y, destX, destY);
	}
	
	public double getYDistToTravel()
	{
		return Math.abs(destX-x);
	}
	
	public double getDistToTravel()
	{
		return Math.abs(destY-y);
	}
	
	public double distance(double x1, double y1, double x2, double y2) 
	{ 
		return Math.sqrt( ( x1 - x2 ) * ( x1 - x2 ) + ( y1 - y2 ) * ( y1 - y2 ) ); 
	}

}
