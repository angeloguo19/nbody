

/**
 * Celestial Body class for NBody
 * @author angeloguo
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor

		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		// TODO: complete constructor

		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}

	/**
	 * Return x-position of this Body.
	 * @return value of x-position.
	 */
	public double getX() {
		// TODO: complete method

		return myXPos;
	}

	/**
	 * Return y-position of this Body.
	 * @return value of y-position.
	 */
	public double getY() {
		// TODO: complete method

		return myYPos;
	}

	/**
	 * Return x-velocity of this Body.
	 * @return value of x-velocity.
	 */
	public double getXVel() {
		// TODO: complete method

		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method

		return myYVel;
	}

	/**
	 * Return mass of this Body.
	 * @return value of mass
	 */
	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 * Return file name of this Body.
	 * @return filename
	 */
	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method
		double r = (myXPos - b.getX()) * (myXPos - b.getX()) + (myYPos - b.getY()) * (myYPos - b.getY());
		return Math.sqrt(r);
	}

	/**
	 * Return the force exerted on this Body by another
	 * @param b the other body which exerts the force
	 * @return the force exerted on this body
	 */
	public double calcForceExertedBy(CelestialBody b) {
		// TODO: complete method
		double g = 6.67 * Math.pow(10, -11);
		return (g * myMass * b.getMass()) / (calcDistance(b) * (calcDistance(b)));
	}

	/**
	 * Return the force exerted on the x-axis
	 * @param b the other body which exerts the force
	 * @return the x-component of the force exerted
	 */
	public double calcForceExertedByX(CelestialBody b) {
		// TODO: complete method
		double f = calcForceExertedBy(b)*(b.getX() - myXPos)/calcDistance(b);
		return f;
	}

	/**
	 * Return the force exerted on the y-axis
	 * @param b the other body which exerts the force
	 * @return the y-component of the force exerted
	 */
	public double calcForceExertedByY(CelestialBody b) {
		// TODO: complete method
		double f = calcForceExertedBy(b)*(b.getY() - myYPos)/calcDistance(b);
		return f;
	}

	/**
	 * Return the next force exerted by all bodies on the x-axis
	 * @param bodies all bodies exerting a force on this body
	 * @return the next force on the x-axis
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double f = 0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				f += calcForceExertedByX(b);
			}
		}
		return f;
	}

	/**
	 * Return the next force exerted by all bodies on the y-axis
	 * @param bodies all bodies exerting a force on this body
	 * @return the next force on the y-axis
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		// TODO: complete method
		double f = 0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				f += calcForceExertedByY(b);
			}
		}
		return f;
	}

	/**
	 * updates the mass, x-y velocities, and x-y coordinates
	 * @param deltaT step value
	 * @param xforce net forces exerted on this body in x-axis
	 * @param yforce net forces exerted on this body in y-axis
	 */
	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}

	/**
	 * draws the celestial body using current values
	 */
	public void draw() {
		// TODO: complete method
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
