//Cameron Embree, Gradon Faulkner

public class SimActor extends Observable 
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
		
		this.setID(id);
		this.setX(x);
		this.setY(y);
	}
	
	
	private void setDeltaX(double dx)
	{
		this.deltaX = dx;
	}
	
	private void setDeltaY(double dy)
	{
		this.deltaY = dy;
	}
	
	protected void setX(double x)
	{
		this.x = x;
	}
	
	protected void setY(double y)
	{
		this.y = y;
	}
	
	private void setID(String id)
	{
		this.id = id;
	}
	
	public void setDestination(double destX, double destY)
	{
		this.setXDest(destX);
		this.setYDest(destY);
	}
	
	public void setDestination(double destX, double destY, double timeStepPartitions)
	{
		this.setXDest(destX, timeStepPartitions);
		this.setYDest(destY, timeStepPartitions);
	}
	
	private void setXDest(double destX)
	{
		this.destX = destX;
		this.setDeltaX(getXDistToTravel() / 1.0); //moves to final location in 1 move
	}
	
	private void setYDest(double destY)
	{
		this.destY = destY;
		this.setDeltaY(getYDistToTravel() / 1.0); //moves to final location in 1 move
	}
	
	private void setXDest(double destX, double partitions)
	{
		this.destX = destX;
		this.setDeltaX( getXDistToTravel() / partitions ); //moves to final location in "partitions" move
	}
	
	private void setYDest(double destY, double partitions)
	{
		this.destY = destY;
		this.setDeltaY( getYDistToTravel() / partitions ); //moves to final location in "partitions" move
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
	
	public double getDeltaX()
	{
		return this.deltaX;
	}
	
	public double getDeltaY()
	{
		return this.deltaY;
	}
	
	public String getid()
	{
		return this.id;
	}
	
	public double getXDistToTravel()
	{
		return Math.abs(destX-x);
	}
	
	public double getYDistToTravel()
	{
		return Math.abs(destY-y);
	}
	
	public double getDistToTravel()
	{
		return distance(x, y, destX, destY);
	}
	
	public double distance(double x1, double y1, double x2, double y2) 
	{ 
		return Math.sqrt( ( x1 - x2 ) * ( x1 - x2 ) + ( y1 - y2 ) * ( y1 - y2 ) ); 
	}

}
