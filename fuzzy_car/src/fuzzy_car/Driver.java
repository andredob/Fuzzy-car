package fuzzy_car;

/**
 *
 * @author Dobermann
 */
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Driver {

    public Driver(double aCar, double xError, Car car) {
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        JFuzzyChart.get().chart(fis);
        fis.setVariable("carAngle", aCar);
        fis.setVariable("centered", xError);
        fis.evaluate();
        Variable wAngle = fis.getVariable("newWheelAngle");
        //JFuzzyChart.get().chart(wAngle, wAngle.getDefuzzifier(), true);
    }

    String fileName = "src/tipper/tipper.fcl";
    FIS fis = FIS.load(fileName, true);

    void drive(double aCar, double x) {
        fis.setVariable("carAngle", aCar);
        fis.setVariable("centered", x);
        System.out.println(x);

        fis.evaluate();
        Variable wAngle = fis.getVariable("newWheelAngle");
        ParkingLot.getInstance().car.aWheel = wAngle.defuzzify();
    }
}
