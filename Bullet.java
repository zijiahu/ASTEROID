import processing.core.PApplet;
import processing.core.PImage;

/**
 * A representation of a bullet.
 * @author Zijia Hu
 */
public class Bullet {

	private PlayAsteroids pa;
	
	//make sure the image file is in the src  folder
	private final static String BULLET_IMAGE_PATH = "bullet.png";
	private PImage img; 
	
	// x, y location
	public int x, y; 
	
	// going up-screen
	private int speedY = -10; 
	
	/**
	 * Constructor for a Bullet object.  
	 * @param x The x position at which to draw this bullet.
	 * @param y The y position at which to draw this bullet.
	 * @param app A reference to the object that controls the flow of the game.  This app object also inherits from PApplet, and so contains lots of useful methods that Processing codes into the PApplet class.
	 */
	public Bullet(int x, int y, PlayAsteroids pa) {
		//set up initial properties for this  bullet
		this.pa = pa; //keep a reference  to the PApplet class to handle all  Processing-specific functions and  variables

		//position it on screen
		this.x = x;
		this.y = y;
		
		//load the image and store in PImage  variable
		this.img = pa.loadImage(Bullet.BULLET_IMAGE_PATH);
		
		//add this Bullet object to the  PApplet's list of bullets
		this.pa.getBullets().add(this);

	}

	/**
	 * Get the width of this bullet, based on  the width of its image.
	 */
	public int getWidth() {
		return this.img.width; //return the PImage object's width property
	}
	
	/**
	 * Get the height of this bullet, based on  the width of its image.
	 */
	public int getHeight() {
		return this.img.height; //return the PImage object's height property
	}
	
	/**
	 * Slide the bullet further up the screen.
	 */
	public void move() {
		int newY = this.y + this.speedY; // calculate move by whichever amount is  specified in speedY variable.
		
		//check bounds
		boolean outOfBoundsToTheTop = newY < 0 +  this.getHeight(); //too far up the  screen?

		//if out of bounds...
		if (outOfBoundsToTheTop) {
			 //remove it from the array of  bullets
			this.pa.getBullets().remove(this); //aren't  ArrayLists great?
		}
		
		//make update to position
		this.y = newY;
		
	}
	
	/**
	 * Sets by how much this spaceship moves  each frame.
	 * Setter for speedX property.
	 */
	public void setSpeed(int speedY) {
		this.speedY = speedY;
	}

	/**
	 * Kill this bullet.
	 * Simply removes this bullet from the  PApplet's list of bullets;
	 */
	public void kill() {
		this.pa.getBullets().remove(this); //remove this  bullet from the list of aliens
	}
	

	/**
	 * Draws this alien to the PApplet screen.   Each alien draws itself to the main app  screen in this way.
	 */
	public void draw() {
		//draw the image using PApplet's image  method
		this.pa.image(this.img, this.x, this.y);
	}
	
	/**
	 * Static method to check collision between any bullet and any alien
	 */
	public static boolean isCollision(Bullet  bullet, Asteroid asteroid) {
		boolean collision = false; //flag to  indicate whether a collision has been  detected
		
		//check whether bullet is within the box  representing the alien
		if (bullet.x >= asteroid.getxLocation() && bullet.x +  bullet.getWidth() <= asteroid.getxLocation() + asteroid. getWidth()) {
			if (bullet.y >= asteroid.getyLocation() && bullet.y  + bullet.getHeight() <= asteroid.getyLocation() +  asteroid.getHeight()) {
				collision = true;
			}
		}
		
		return collision;
	}
	

}