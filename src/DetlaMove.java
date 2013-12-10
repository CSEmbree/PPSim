
public class DetlaMove {
	private double dx;
	private double dy;
	private double dz;

	
	
	public DetlaMove()
	{
		this(0, 0, 0);
	}
	
	
	public DetlaMove(double dx, double dy, double dz)
	{
		setDX(dx);
		setDY(dy);
		setDZ(dz);
	}
	
	private void setDX(double d)
	{
		this.dx = d;
	}
	
	private void setDY(double d)
	{
		this.dy = d;
	}
	
	private void setDZ(double d)
	{
		this.dz = d;
	}
	
	public double getDX()
	{
		return this.dx;
	}
	
	public double getDY()
	{
		return this.dy;
	}
	
	public double getDZ()
	{
		return this.dz;
	}
	
}
