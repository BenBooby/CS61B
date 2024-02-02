public class NBody{
    public static double readRadius(String filename){
        In input = new In(filename);
        int n_planets = input.readInt();
        double radius = input.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename){
        In input = new In(filename);
        //int lines = input.readAllLines().length;
        

        int n_planets = input.readInt();
        double radius = input.readDouble();
        Body[] all_Bodies = new Body[n_planets];

        // i can't append those to the list... how can I find the array size? 
        for (int i = 0; i < n_planets; i += 1){
            double xP = input.readDouble();
            double yP = input.readDouble();
            double xV = input.readDouble();
            double yV = input.readDouble();
            double m = input.readDouble();
            String img = input.readString();
            all_Bodies[i] = new Body(xP, yP, xV, yV, m, img);
        }

        return all_Bodies;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        /*Get all the info from the note */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] allBodies = readBodies(filename);
        double radius = readRadius(filename);

        String backdrop = "starfield.jpg";

        StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */

        for (double time = 0; time <= T;time += dt){
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];
            for (int i = 0; i < allBodies.length ; i+= 1){
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }

            for (int i = 0; i < allBodies.length ; i+= 1){
                allBodies[i].update(dt, xForces[i],yForces[i]);
            }

            /* Clears the drawing window. */
            StdDraw.clear();
            StdDraw.picture(0,0,backdrop);

            for (int i =0; i < allBodies.length;i += 1){
                allBodies[i].draw();
            }
            /* Shows the drawing to the screen, and waits 2000 milliseconds. */

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
            allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);   

        }
    }
}