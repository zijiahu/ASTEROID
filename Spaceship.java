

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class represents the spaceship.
 * @author Zijia Hu
 *
 */
public class Spaceship {
	private PlayAsteroids pa;
	
	private final static String SPACESHIP_IMAGE_PATH = "spaceship.png"; 
	private PImage img;
	
	private int x;
	private int y;
	private int speedX = 10;
	private int speedY = 10;
	
	
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
	 * Constructor for a spaceship. 
	 * @param pa A reference to the object that controls the flow of the game.
	 */
	public Spaceship(PlayAsteroids pa) {
		this.pa = pa;

		this.x = (int) (PlayAsteroids.getWIDTH() / 2); 
		this.y = PlayAsteroids.getHEIGHT() - 100; 
		
		this.img = this.pa.loadImage(Spaceship.SPACESHIP_IMAGE_PATH);
	}
	
	
	/**
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		if (this.x < PlayAsteroids.getWIDTH()- this.getWidth() && this.x > 0 + this.getWidth() ) {
			this.x = x;
		}
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		if (this.y < PlayAsteroids.getHEIGHT() - this.getHeight() && this.y > 0 + this.getHeight() ) {
			this.y = y;
		}
	}

	/**
	 * Set speed such that the spaceship moves to the left.
	 */
	public void goLeft() {
		if (this.x > this.getWidth()) {
			this.setX(x - speedX);
		}
		
	}
	
	/**
	 * Set speed such that the spaceship moves to the right.
	 */
	public void goRight() {
		if (this.x < PlayAsteroids.getWIDTH()- this.getWidth()) {
			this.setX(x + speedX);
		}
	}
	
	/**
	 * Set speed such that the spaceship moves up.
	 */
	public void goUp() {
		if (this.y < PlayAsteroids.getHEIGHT() - this.getHeight()) {
			this.setY(y - speedY);
		}
	}
	
	/**
	 * Set speed such that the spaceship moves down.
	 */
	public void goDown() {
		if (this.y > this.getHeight())
			this.setY(y + speedY);
		
	}
	
	/**
	 * Draw the spaceship.
	 */
	public void draw() {
		this.pa.image(this.img, this.x, this.y);
	}
	
	/**
	 * Shoot the bullet.
	 */
	public void shoot() {
		int x = (int) (this.x + (this.getWidth()  / 2)); 
		int y = (int) (this.y - (this. getHeight() / 2)); 

		Bullet bullet = new Bullet(x, y, this.pa);
			
	}
	
	/**
	 * Check whether the asteroids hit the spaceship.
	 * @return collision Whether it hits
	 */
	public static boolean isCollision(Spaceship spaceship, Asteroid asteroid) {
		boolean collision = false;
		
		if (spaceship.x + spaceship.getWidth() >= asteroid.getxLocation() && spaceship.x <= asteroid.getxLocation() + asteroid.getWidth()) {
			if (spaceship.y + spaceship.getHeight() >= asteroid.getyLocation() && spaceship.y <= asteroid.getyLocation() +  asteroid.getHeight()) {
				collision = true;
			}
		}
		return collision;
	}
	
	 
}
