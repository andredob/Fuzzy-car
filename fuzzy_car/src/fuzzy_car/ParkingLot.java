/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy_car;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dobermann
 */
public class ParkingLot extends JPanel implements Runnable {

    private static ParkingLot singleton;

    public static synchronized ParkingLot getInstance() {
        if (singleton == null) {
            singleton = new ParkingLot();
        }
        return singleton;
    }
    Driver driver;
    Car car;
    JFrame jf = new JFrame();
    int docX;
    int docSize;

    public ParkingLot() {
        jf.setSize(new Dimension(this.getWidth(), this.getHeight()));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addCar();
        addDoc();
        driver = new Driver(car.aCar, normalize(car.x), car);
    }

    void addCar() {
        double aCar = Math.random() * (270 - 90);
        double aWeel = 0;
        int x = (int) (Math.random() * (this.getWidth() - 10));
        int y = (int) (Math.random() * (this.getHeight() - 10));
        int ray = 20;
        car = new Car(aCar, aWeel, x, y, ray);
    }

    void addDoc() {
        docSize = car.r;
        docX = (int) (Math.random() * (1000)) - docSize;
    }


    double normalize(double posX) {
        double erro = posX - docX;
        if(erro > 0){
            erro -= docSize;
        } else{
            erro -= docSize * 2.5;
        }
        posX = erro / 10;
        return posX;
    }

    void paintBackground(Graphics g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight() / 2);
        g2.setColor(Color.GREEN);
        g2.fillRect(0, this.getHeight() / 2, this.getWidth(), this.getHeight());
    }
    boolean b = true;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (b) {
            paintBackground(g2);
        }
        b = false;
        g2.setColor(Color.RED);
        g2.drawRect(docX + docSize / 2, 0, docSize, docSize);

        car.draw(g2);
    }

    @Override
    public void run() {
        boolean keep = true;
        while (keep) {
            try {
                car.move();
                repaint();
                driver.drive(car.aCar, normalize(car.x));
                Thread.sleep(20);
                if (car.y <= 20) {
                    b = true;
                    addDoc();
                    addCar();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ParkingLot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
