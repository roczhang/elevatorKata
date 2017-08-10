package AcceptTest.dsl;

import elevator.SimpleElevator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by I076057 on 8/10/2017.
 */
public class DSL {

    private SimpleElevator elevator = new SimpleElevator(null);

    public void givenElevetorOnFloor(int floor) {

        elevator.setFloor(floor);
    }

    public void givenGotoFloor(int from, int to) {

        elevator.requestFloor(from, to);
    }

    public void whenElevatorRun() {

        elevator.run();
    }

    public void thenFinalState(int floor) {
        assertThat(elevator.getCurrentFloor(), is(floor));
    }
}

