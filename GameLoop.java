package particleSystem;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameLoop {
	////fields
	Canvas canvas;
	GraphicsContext gc;
	AnimationTimer aT;
	ArrayList<Particle> particleList;
	Vector MousePos;
	
	////Constructor
	protected GameLoop(Canvas c, int particleCount)	{
		this.canvas = c;
		this.gc = canvas.getGraphicsContext2D();
		this.particleList = new ArrayList<>();
		//particles
		this.particleList = Particle.initParticles(particleCount, (int)canvas.getWidth(), (int)canvas.getHeight());		//inits particles at random position
	}
	
	////methods
	//start animationloop
	protected void startLoop()	{
		//MousePos
		MousePos = new Vector();
			
		//AnimationTimer
		this.aT = new AnimationTimer()	{
			//handle method (things that happen with every animation run; usually 60 times per second)
			@Override
			public void handle(long now)	{
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());	//clearing canvas
				gc.setFill(Color.RED);
				gc.fillText("VelocityLimit: " + String.valueOf(particleList.get(0).getVelocityLimit()), 10, 15);
				drawAll();	//draw all particles at their current position
				updateAll();
				canvas.setOnMouseMoved(v -> {	//mouse position
					MousePos.setX(v.getX());
					MousePos.setY(v.getY());
				});
				
				canvas.setOnScroll(v -> {
					double deltaY = v.getDeltaY();
					updateAllVelocityLimits((int)deltaY);
				});
				
			}
			
		};
		
		this.aT.start(); //starts the gameloop
	}
	//update all particle positions
	private void updateAll() {
		for (int i = 0; i < particleList.size(); i++)	{
			particleList.get(i).update(MousePos, canvas);
		}
	}
	
	private void updateAllVelocityLimits(int value)	{
		for (int i = 0; i < particleList.size(); i++)	{
			particleList.get(i).updateVelocityLimit(value);
		}
	}
	
	//draw particles on canvas
	private void drawAll()	{
		gc.setFill(Color.LIGHTGREEN);
		for (int i = 0; i < particleList.size(); i++)	{
			gc.fillOval(particleList.get(i).getPos().getX(), particleList.get(i).getPos().getY(), 1, 1);
		}
	}
	
	

}
