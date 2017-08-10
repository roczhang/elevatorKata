import MockListner.MockListner;
import common.EventInfo;
import elevator.SimpleElevator;
import listner.ElevatorEvent;
import listner.ElevatorLister;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by I076057 on 8/10/2017.
 */
public class SimpleElevatorTest {


    SimpleElevator elevator;
    ElevatorLister listner;

    @Before
    public void init() {

        listner = new MockListner();
        elevator = new SimpleElevator(listner);
    }

    @Test
    public void testSetInitFloor() throws Exception {

        elevator.setFloor(1);
        assertThat(elevator.getCurrentFloor(), is(1));
    }

    @Test
    public void testGotoFloor() throws Exception {

        elevator.requestFloor(1, 2);

        assertThat(elevator.getFromFloor(), is(1));
        assertThat(elevator.getToFloor(), is(2));
    }

    @Test
    public void testRun() throws Exception {


        elevator.setFloor(1);
        elevator.requestFloor(1, 1);

        elevator.run();

        assertThat(elevator.getCurrentFloor(), is(1));

        List<EventInfo> eventistory = listner.getEventHistory();

        int[] elevatorPath = eventistory.stream().filter(e -> e.getEvent() == ElevatorEvent.ARRIVED).mapToInt(e -> e.getFloor()).toArray();


        assertThat(elevatorPath, is(new int[]{1,1}));


    }
}
