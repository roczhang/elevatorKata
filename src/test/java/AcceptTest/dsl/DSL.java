package AcceptTest.dsl;

import MockListner.MockListner;
import elevator.Elevator;
import elevator.SimpleElevator;
import elevator.ElevatorController;
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
    private Elevator elevator = new SimpleElevator(listener);
    private ElevatorController elevatorManager = new ElevatorController(elevator);
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



