package particleSystem;

public class Vector {
	////fields
	private double x;
	private double y;
	
	////Constructor
	protected Vector ()	{
		this.x = 0;
		this.y = 0;
	}
	protected Vector (double xPos, double yPos)	{
		this.x = xPos;
		this.y = yPos;
	}
	
	////getter setter
	protected double getX() {
		return this.x;
	}
	protected double getY()	{
		return this.y;
	}
	
	protected void setX(double X)	{
		this.x = X;
	}
	protected void setY(double Y) {
		this.y = Y;
	}
	
	////Vector operations
	//add
	protected static Vector add(Vector v1, Vector v2)	{
		return new Vector((v1.x+v2.x), (v1.y+v2.y));
	}
	protected void add(int value)	{
		this.x += 1;
		this.y += 1;
	}
	//sub
	protected static Vector sup(Vector v1, Vector v2)	{
		return new Vector((v1.x-v2.x), (v1.y-v2.y));
	}
	protected void sub(int value)	{
		this.x -= 1;
		this.y -= 1;
	}
	//mult
	protected void mult(double n)	{
		this.x = this.x*n;
		this.y = this.y*n;
	}
	protected static Vector mult(Vector v1, double n)	{
		return new Vector((v1.x*n), (v1.y*n));
	}
	protected static double mult(Vector v1, Vector v2)	{
		return (v1.x*v2.x) + (v1.y*v2.y);
	}
	//div
	protected static Vector div(Vector v1, double n)	{
		return new Vector((v1.x/n), (v1.y/n));
	}
	protected void div(double n)	{
		this.x = this.x/n;
		this.y = this.y/n;
	}
	//magnitude
	protected static double mag(Vector v)	{
		return Math.sqrt((Math.pow(v.x, 2)) + (Math.pow(v.y, 2)));
	}
	//normalize
	protected void normalize()	{
		this.div(mag(this));
	}
	//limit
	protected void limit(double max)	{
		if (mag(this) > max)	{
			this.normalize();
			this.mult(max);
		}
	}
	////toString
	@Override
	public String toString()	{
		return this.x + " " + this.y;
	}
}
