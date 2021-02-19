import processing.core.*;

/**
 * The Asteroid class represents an Asteroid.
 * @author Zijia Hu
 */
public class Asteroid {
	
	private PlayAsteroids pa;
	
	private final static String Asteroid_IMAGE_PATH  = "asteroid.png"; 
	private PImage img; 
		
	private boolean exists = true; 
	private int speed = 1;
	
	private int xLocation;
	private int yLocation;
	
	
	/**
	 * Constructor for an Asteroid object.  
	 * @param x The x position at which to draw this asteroid.
	 * @param y The y position at which to draw this asteroid.
	 * @param pa A reference to the object that controls the flow of the game.  
	 */
	public Asteroid(int x, int y, PlayAsteroids pa) {
		this.pa = pa;
		this.img = pa.loadImage(Asteroid.Asteroid_IMAGE_PATH);
		img.resize(60,60);
		this.setxLocation(x);
		this.setyLocation(y);
		this.assignRandomSpeed();
	}
	
	/**
	 * Get the width of this asteroid, based on the width of its image.
	 * @return int width of this image
	 */
	public int getWidth() {
		return this.img.width; 
	}
	
	/**
	 * Get the height of this asteroid, based on the height of its image.
	 * @return int height of this image
	 */
	public int getHeight() {
		return this.img.height;
	}
	
	
	/**
	 * @return the xLocation of the Asteroids
	 */
	public int getxLocation() {
		return this.xLocation;
	}
	
	/**
	 * @param xLocation the xLocation to set
	 */
	public void setxLocation(int xLocation) {
		if (xLocation < PlayAsteroids.getWIDTH()- this.getWidth() && xLocation > 0 + this.getWidth() ) {
			this.xLocation = xLocation;
		}
	}
	
	/**
	 * @return the yLocation of the Asteroids
	 */
	
	public int getyLocation() {
		return this.yLocation;
	}
	
	/**
	 * @param yLocation the yLocation to set
	 */
	public void setyLocation(int yLocation) {
			this.yLocation = yLocation;
	}

	/**
	 * get boolean if the object exists
	 */
	public boolean getExists() {
		return this.exists;
	}
	
	/**
	 * @param exists the boolean whether it exists
	 */
	public void setExists(boolean exists) {
		this.exists = exists;
	}
	
	/**
	 * Draw the Asteroid.
	 */
	public void draw() {
		if(this.exists) {
			this.pa.image(this.img, this.xLocation, this.yLocation);
		}
	}
	
	/**
	 * Remove the Asteroid.
	 */
	public void kill() {
		this.setExists(false);
		this.pa.getAsteroids().remove(this);
	}
	
	
	/**
	 * Move the Asteroid.
	 */
	public void move() {
		int newY = this.yLocation + this.speed;
		int randomX = (int) (Math.random()* PlayAsteroids.getWIDTH());
		if(newY >= PlayAsteroids.getHEIGHT()) {
			newY=0;
			this.setxLocation(randomX);
			this.assignRandomSpeed();
		}
		this.setyLocation(newY);
	}
	
	public void assignRandomSpeed() {
		int randomSpeed = (int)(Math.random()*3+1);
		this.speed = randomSpeed;
	}

}