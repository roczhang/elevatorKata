import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;
import utility.AssertUtility;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by I076057 on 8/10/2017.
 */
public class TwoPassengerSimpleElevatorTest extends ElevatorBaseTest {

    @Test
    public void test_hasRequest() throws Exception {
        assertThat(elevator.hasRequest(), is(false));

        elevator.requestFloor(1, 2);
        assertThat(elevator.hasRequest(), is(true));

    }


    @Test
    public void test_hasRequest_FromRequet_empty() throws Exception {
        elevator.requestTo(1);
        assertThat(elevator.hasRequest(), is(true));

    }

    @Test
    public void test_hasRequest_ToRequest_empty() throws Exception {
        elevator.requestFromFloor(1);
        assertThat(elevator.hasRequest(), is(true));

    }


    @Test
    public void testTwoPerson_from_to_same_floor() throws Exception {


        elevator.setFloor(1);
        elevator.requestFloor(1, 1);
        elevator.requestFloor(1, 1);
        elevator.run();
        Assert.assertThat(elevator.getCurrentFloor(), is(1));
        AssertUtility.assertPath(listner.getEventHistory(), new int[]{1});


    }

    @Ignore
    public void testTwoPerson_from_the_direction_uppper() throws Exception {


        elevator.setFloor(1);
        elevator.requestFloor(1, 2);
        elevator.requestFloor(1, 3);
        elevator.run();
        Assert.assertThat(elevator.getCurrentFloor(), is(3));
        AssertUtility.assertPath(listner.getEventHistory(), new int[]{1, 2, 3});


    }



    @Test
    public void test_addElevatorRequest() throws Exception {

        Assert.assertThat(elevator.getFromFloors().size(), is(0));
        Assert.assertThat(elevator.getToFloors().size(), is(0));

        elevator.requestFloor(1,2);

        Assert.assertThat(elevator.getFromFloors().size(), is(1));
        Assert.assertThat(elevator.getToFloors().size(), is(1));

        elevator.requestFloor(1,2);


        Assert.assertThat(elevator.getFromFloors().size(), is(1));
        Assert.assertThat(elevator.getToFloors().size(), is(1));

    }

}
