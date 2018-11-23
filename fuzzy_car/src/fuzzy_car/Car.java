package fuzzy_car;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Dobermann
 */
public class Car extends JPanel {

    double aCar;
    double aWheel;
    int x;
    int y;
    int r;
    double velocity;
    boolean backwards = false;

    public Car(double aCar, double aWheel, int x, int y, int r) {
        this.aCar = normalizeAngle(aCar);
        this.aWheel = aWheel;
        this.x = x;
        this.y = y;
        this.r = r;
        velocity = 0.5;
    }

    public void move() {
        int newY;
        
        if (y < 250 && backwards) { //Move backwards
            newY = (int) (y + velocity * r * Math.sin(Math.toRadians(aCar)));
        } else {
            backwards = false;
            newY = (int) (y - velocity * r * Math.sin(Math.toRadians(aCar)));
        }
        
        int newX = (int) (x + velocity * r * Math.cos(Math.toRadians(aCar)));
        double newAcar = aWheel + aCar;
        x = newX;
        y = newY;
        System.out.println(x);
        aCar = normalizeAngle(newAcar);
    }

    double normalizeAngle(double newAcar) {
       
        if (newAcar > 270) {
            double aux = newAcar - 270;
            return -90 + aux;
        }
        if (newAcar < -90) {
            double aux = newAcar - 90;
            return 270 - aux;
        }
        return newAcar;
    }

    public void draw(Graphics2D g2) {
        if(backwards){
            g2.rotate(Math.toRadians(aCar), x - r / 4, y);
        } else{
            g2.rotate(-Math.toRadians(aCar), x - r / 4, y);
        }
        

        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, r, r / 2);

        if(backwards){
            g2.rotate(Math.toRadians(aCar), x - r / 4, y);
        } else{
            g2.rotate(-Math.toRadians(aCar), x - r / 4, y);
        }
    }

}
