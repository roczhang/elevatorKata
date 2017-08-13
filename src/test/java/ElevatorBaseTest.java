import MockListner.MockListner;
import elevator.SimpleElevator;
import listner.ElevatorLister;
import org.junit.Before;

/**
 * Created by I076057 on 8/10/2017.
 */
public class ElevatorBaseTest {


    SimpleElevator elevator;
    ElevatorLister listner;

    @Before
    public void baseInit() {

        listner = new MockListner();
        elevator = new SimpleElevator(listner);
    }
}
