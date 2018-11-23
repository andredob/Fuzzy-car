
package fuzzy_car;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Dobermann
 */
public class Fuzzy_car {

    
    public static void main(String[] args) {
        int w = 500;
        int h = 500;
        JFrame jf = new JFrame();
        jf.setSize(new Dimension(w, h));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ParkingLot pl = ParkingLot.getInstance();
        
        jf.getContentPane().add(pl);
        jf.setVisible(true);
        
        Thread t = new Thread(pl);
        t.start();
    }
    
}
