import java.util.ArrayList;
import processing.core.*;

/**
 * Controlling the spaceship to avoid being crashed by asteroids.
 * @author Zijia Hu
 */
public class PlayAsteroids extends PApplet{
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	private static boolean flag = false;

	
	private final int BACKGROUND = this.color(0,0,0); 
	
	private PImage img;

	public final static int MARGIN = 60;	
	public final static int NUM_ASTEROID_START = 5;
	
	ArrayList<Integer> integers = new ArrayList<>();
	int luckNum1;
	int luckNum2;
	int luckNum3;
	int luckNum4;
	
	public int gift;
	
	int totalAsteroids = 10; 	
		
	private int x = 10;
	private int y = 10;
	
	private Spaceship spaceship;
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	private ArrayList<Bullet> bullets = new  ArrayList<Bullet>();


	public void settings() {
		this.size(PlayAsteroids.WIDTH, PlayAsteroids.HEIGHT);
	}
	

	public void setup() {
		this.background(this.BACKGROUND);		
		this.spaceship = new Spaceship(this);
		
		for (int i = 0; i < NUM_ASTEROID_START; i++) {
			// set a random x location to an asteroid
			int x = (int) (Math.random()* PlayAsteroids.getWIDTH());
			Asteroid asteroid = new Asteroid(x, 0, this);
			this.asteroids.add(asteroid);
		}
		
		
		int luckNum1 = (int)(Math.random()*totalAsteroids + 1);
		int luckNum2 = (int)(Math.random()*totalAsteroids + 1);
		int luckNum3 = (int)(Math.random()*totalAsteroids + 1);
		int luckNum4 = (int)(Math.random()*totalAsteroids + 1);
//		
//		integers.add(luckNum1);
//		integers.add(luckNum2);
//		integers.add(luckNum3);
//		integers.add(luckNum4);
//		
//		int tem = 0;
//		for (int i = 0; i < integers.size(); i ++) {
//			for (int j = i; j<integers.size(); j++) {
//				if(integers.get(i) == integers.get(j)) {
//					tem = (int)(Math.random()*totalAsteroids + 1);
//				}
//			}
//		}
		System.out.println(luckNum1);
		System.out.println(luckNum2);
		System.out.println(luckNum3);
		System.out.println(luckNum4);


	}

	
	public void draw() {
		if(flag) {
			System.out.println("You Crashed");
			System.exit(0);
		}
		else {
			
			this.background(this.BACKGROUND);
			
			// draw the spaceship
			this.spaceship.draw();
			
			
			// make the asteroids move, and remove the asteroid object when it exceeds the height of the canvas
			for (int i = 0; i < this.asteroids.size(); i++) {
				Asteroid asteroid = this.asteroids.get(i);
				asteroid.move();
				asteroid.draw();
				if(asteroid.getyLocation() - asteroid.getHeight() >= PlayAsteroids.getHEIGHT()) {
					asteroid.kill();
				}
			}
			
			
			// loop through ArrayList of bullets
			for (int i=0; i<this.bullets.size(); i++) {
				Bullet bullet = this.bullets.get(i);
				bullet.move(); 
				bullet.draw(); 
			}
			
	
			// hold those are going to be removed
			ArrayList<Asteroid> asteroidToRemove = new  ArrayList<Asteroid>(); 
			ArrayList<Bullet> bulletsToRemove = new  ArrayList<Bullet>(); 
			
			for (Bullet bullet : this.bullets) {
				for (Asteroid asteroid : this.asteroids) {
					if (Bullet.isCollision(bullet,  asteroid)) {
						asteroidToRemove.add(asteroid);
						bulletsToRemove.add(bullet);
						totalAsteroids = totalAsteroids - 1;
					}
				}
			}
			
			if(asteroids.size() == 0) {
//				pg.text(string1,WIDTH/2,HEIGHT/2);
				flag = true;
			}
			
			
			if(totalAsteroids == luckNum1) {
				gift ++;
				System.out.println(gift);
			}
			else if(totalAsteroids == luckNum2) {
				gift ++;
				System.out.println(gift);
			}
			else if(totalAsteroids == luckNum3) {
				gift ++;
				System.out.println(gift);
			}
			else if(totalAsteroids == luckNum4) {
				gift ++;
				System.out.println(gift);
			}
			
			// remove what needed to be removed
			for (Asteroid asteroid : asteroidToRemove) {
				asteroid.kill(); 
			}
			
			for (Bullet bullet:bulletsToRemove) {
				bullet.kill(); 
			}
		
			
			// check whether the asteroid and spaceship collide
			for(Asteroid asteroid: this.asteroids) {
				if(Spaceship.isCollision(spaceship, asteroid)) {
					//this.loadImage(PlayAsteroids.COLLISION);
	//				noLoop();
	//				fill(0);
	//				text(string1,10,200);
	//				System.exit(-1);
					flag = true;
					break;
				}
			}
		}
	}
	
	
	
	/**
	 * Called whenever a key is pressed by the user.  Move the spaceship left or right or fires a bullet, depending on the key.
	 */
	public void keyPressed() {
		if (this.key == PConstants.CODED) {
			switch (this.keyCode) {
				case PConstants.LEFT:
					this.spaceship.goLeft();
					break;
				case PConstants.RIGHT:
					this.spaceship.goRight();
					break;
				case PConstants.UP:
					this.spaceship.goUp();
					break;
				case PConstants.DOWN:
					this.spaceship.goDown();
					break;
			}
		}
		else if (key == ' ') {
			this.spaceship.shoot();
		}
	}	
	

	/**
	 * @return the spaceship
	 */
	public Spaceship getSpaceship() {
		return this.spaceship;
	}

	/**
	 * @param spaceship the spaceship to set
	 */
	public void setSpaceship(Spaceship spaceship) {
		this.spaceship = spaceship;
	}

	/**
	 * @return the asteroids
	 */
	public ArrayList<Asteroid> getAsteroids() {
		return this.asteroids;
	}

	/**
	 * @param asteroids the asteroids to set
	 */
	public void setAsteroids(ArrayList<Asteroid> asteroids) {
		this.asteroids = asteroids;
	}
	
	/**
	 * Getter for the ArrayList of Bullet objects currently on the screen
	 * @return ArrayList of Bullet objects
	 */
	public ArrayList<Bullet> getBullets() {
		return this.bullets;
	}

	/**
	 * Setter for the ArrayList of Bullet objects currently on the screen.
	 * @param aliens An ArrayList of Bullet objects
	 */
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	/**
	 * Get the width of the background
	 * @return WIDTH the width of the background
	 */
	public static int getWIDTH() {
		return WIDTH;
	}
	
	/**
	 * Get the height of the background
	 * @return HEIGHT the width of the background
	 */
	public static int getHEIGHT() {
		return HEIGHT;
	}
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		PApplet.main("PlayAsteroids");
		
	}
}
