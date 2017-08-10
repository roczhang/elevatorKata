import MockListner.MockListner;
import common.EventInfo;
import elevator.SimpleElevator;
import listner.ElevatorEvent;
import listner.ElevatorLister;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
    public void testRun_fromCurrentFLoorToSameFloor() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(1, 1);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(1));
        assertPath(listner.getEventHistory(),new int[]{ 1});
    }

    @Test
    public void testRun_fromCurrentFloorToUpStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(1, 2);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(2));
        assertPath(listner.getEventHistory(),new int[]{ 1,2});
    }


    @Test
    public void testRun_fromCurrentFloorToDownStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(1, -1);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(-1));
        assertPath(listner.getEventHistory(),new int[]{ 1,0,-1});
    }



    @Test
    public void testRun_fromDifferentFloorToDifferentFloor_upperStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(2, 3);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(3));
        assertPath(listner.getEventHistory(),new int[]{ 1,2,3});
    }


    @Test
    public void testRun_fromDifferentFloorToDifferentFloor_downStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(0,-1);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(-1));
        assertPath(listner.getEventHistory(),new int[]{ 1,0,-1});
    }

    private void assertPath(List<EventInfo> eventHistory, int[] expect) {

        int[] elevatorPath = eventHistory.stream().filter(e -> e.getEvent() == ElevatorEvent.ARRIVED).mapToInt(e -> e.getFloor()).toArray();
        assertThat(elevatorPath, is(expect));
    }
}
