package particleSystem;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.Canvas;

public class Particle {
	////fields
	private Vector pos;
	private Vector velocity;
	private Vector acceleration;
	private double VelocityLimit;		//this is used for the user adjusting the velocity using the mouse wheel
	
	////Constructor
	protected Particle()	{
		this.pos = new Vector();
		this.velocity = new Vector();
		this.acceleration = new Vector();
		this.VelocityLimit = 0;
	}
	
	protected Particle(double x, double y)	{
		this.pos = new Vector(x, y);
		this.velocity = new Vector();
		this.acceleration = new Vector(-0.001, 0.0001);
		this.VelocityLimit =10;
	}
	
	////getter, setter
	protected void setPos(Vector position)	{
		this.pos = position;
	}
	protected Vector getPos()	{
		return this.pos;
	}
	
	protected Vector getVelocity()	{
		return this.velocity;
	}
	
	protected void setVelocityLimit(double limit)	{
		this.VelocityLimit = limit;
	}
	protected double getVelocityLimit()	{
		return this.VelocityLimit;
	}
	
	////methods
	//update
	protected void update(Vector attractor, Canvas c)	{
		Vector dir = Vector.sup(attractor, pos);
		dir.normalize();
		dir.mult(0.5);
		acceleration = dir;
		this.velocity = Vector.add(velocity, acceleration);
		this.velocity.limit(this.VelocityLimit);
		this.pos = Vector.add(pos, velocity);
		checkEdge(c);
	}
	
	//adjust velocity to mouse wheel scroll
	protected void updateVelocityLimit(int value)	{
		if (value > 0)	{
			this.VelocityLimit += 1;
		}
		if (value < 0)	{
			if (this.VelocityLimit >= 0)	{
				this.VelocityLimit -= 1;
			}
		}
	}
	//check edges
	private void checkEdge(Canvas c)		{
		if (this.pos.getX() > c.getWidth())	{
			this.pos.setX(0);
		}
		else {
			if (this.pos.getX() < 0)	{
				this.pos.setX(c.getWidth());
			}
		}
		
		if (this.pos.getY() > c.getHeight())	{
			this.pos.setY(0);
		}
		else {
			if (this.pos.getY() < 0)	{
				this.pos.setY(c.getHeight());
			}
		}
	}
	////static Methods
	protected static ArrayList<Particle> initParticles(int count, int canvasWidth, int canvasHeight)	 {	//init particles at random position
		Random generator = new Random();
		ArrayList<Particle> ret = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			ret.add(new Particle(generator.nextInt(canvasWidth), generator.nextInt(canvasHeight)));
		}
		return ret;
	}
	
}
