package AcceptTest.dsl;

import MockListner.MockListner;
import elevator.SimpleElevator;
import passenger.ElevatorManger;
import passenger.Passenger;
import utility.AssertUtility;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by I076057 on 8/10/2017.
 */
public class DSL {

    Passenger passenger;

    private MockListner listener = new MockListner();
    private SimpleElevator elevator = new SimpleElevator(listener);
    private ElevatorManger elevatorManager = new ElevatorManger(new SimpleElevator(listener));
    private Passenger Bob;
    private Passenger Alice;



    public void givenGotoFloor(int from, int to) {

        elevator.requestFloor(from, to);
    }

    public void whenElevatorRun() {

        elevator.run();
    }

    public void thenFinalState(int floor) {
        assertThat(elevator.getCurrentFloor(), is(floor));
    }

    public void thenElevatorGoThroughPath(int[] expectPath) {

        AssertUtility.assertPath(listener.getEventHistory(), expectPath);
    }


    public void givenElevetorOnFloor(int floor) {
        elevator.setFloor(1);
    }
}



