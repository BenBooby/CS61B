import java.lang.Math.*;
public class Planet {  
    // Six variables
    public double xxPos;   // current x position
    public double yyPos; // current y position
    public double xxVel; //velocity in x direction
    public double yyVel; //velocity in y direction
    public double mass; //mass
    public String imgFileName;
    public static double g = 6.67 * Math.pow(10,-11);

    // use double letter to reduce the change of typos.

    //1st constructor
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    //2nd constructor
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    // compute the distance
    public double calcDistance(Planet b){
        double r = Math.sqrt((this.xxPos - b.xxPos)*(this.xxPos - b.xxPos) + (this.yyPos - b.yyPos)*(this.yyPos - b.yyPos));
        return r;
    }

    public double calcForceExertedBy(Planet b){
        double f = (this.g * this.mass * b.mass) / (this.calcDistance(b) * this.calcDistance(b));
        return f;
    }

    public double calcForceExertedByX(Planet b){
        double f = calcForceExertedBy(b);
        return (f * (b.xxPos - this.xxPos)) / this.calcDistance(b);
    }

    public double calcForceExertedByY(Planet b){
        double f = calcForceExertedBy(b);
        return (f * (b.yyPos - this.yyPos)) / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet[] b_array){
        double netX = 0;
        for (int i = 0; i < b_array.length; i += 1){
            if(this.equals(b_array[i])){
                continue;
            }
            netX += this.calcForceExertedByX(b_array[i]);
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet[] b_array){
        double netY = 0;
        for (int i = 0; i < b_array.length; i += 1){
            if(this.equals(b_array[i])){
                continue;
            }
            netY += this.calcForceExertedByY(b_array[i]);
        }
        return netY;
    }

    public void update(double time, double fx, double fy){
        double dt = time;
        double ax = fx/this.mass;
        double ay = fy/this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;

        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;

    }

    public void draw(){
        String image = imgFileName;

		/* Clears the drawing window. */
		StdDraw.picture(xxPos,yyPos,image);

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		//StdDraw.show();
    }
}