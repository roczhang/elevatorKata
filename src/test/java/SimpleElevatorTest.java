import MockListner.MockListner;
import elevator.SimpleElevator;
import listner.ElevatorLister;
import org.junit.Before;
import org.junit.Test;
import utility.AssertUtility;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by I076057 on 8/10/2017.
 */



public class SimpleElevatorTest extends  ElevatorBaseTest {



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
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1});
    }

    @Test
    public void testRun_fromCurrentFloorToUpStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(1, 2);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(2));
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1,2});
    }


    @Test
    public void testRun_fromCurrentFloorToDownStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(1, -1);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(-1));
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1,0,-1});
    }



    @Test
    public void testRun_fromDifferentFloorToDifferentFloor_upperStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(2, 3);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(3));
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1,2,3});
    }


    @Test
    public void testRun_fromDifferentFloorToDifferentFloor_downStair() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(0,-1);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(-1));
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1,0,-1});
    }

    @Test
    public void testRun_elevatorChangeDirecction() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(0,3);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(3));
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1,0,1,2,3});

    }


    @Test
    public void testRun_elevatorChangeDirecction2() throws Exception {

        elevator.setFloor(1);
        elevator.requestFloor(3,1);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(1));
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1,2,3,2,1});

    }


    @Test
    public void testTwoPerson_from_the_direction() throws Exception {


        elevator.setFloor(1);
        elevator.requestFloor(1,1);
        elevator.requestFloor(1,1);
        elevator.run();
        assertThat(elevator.getCurrentFloor(), is(1));
        AssertUtility.assertPath(listner.getEventHistory(),new int[]{ 1});


    }




}
