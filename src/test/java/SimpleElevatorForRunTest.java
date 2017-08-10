import MockListner.MockListner;
import common.EventInfo;
import elevator.SimpleElevator;
import listner.ElevatorEvent;
import listner.ElevatorLister;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by I076057 on 8/10/2017.
 */
@RunWith(Parameterized.class)
public class SimpleElevatorForRunTest extends ElevatorBaseTest {



    private int [] input;
    private int [] expect;

    public SimpleElevatorForRunTest(int[] input, int[] expect) {
        this.input = input;
        this.expect = expect;
    }

    @Parameterized.Parameters(name = "{index}: Elevator({1}) = {2} ")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { new int[]{1,1}, new int[]{1}},

                { new int[]{1,2}, new int[]{1,2}},

                { new int[]{1,-1}, new int[]{1,0,-1}},

                 { new int[]{2,3}, new int[]{1,2,3}},

                { new int[]{0,-1}, new int[]{1,0,-1}},
                { new int[]{0,3}, new int[]{1,0,1,2,3}},

                { new int[]{3,1}, new int[]{1,2,3,2,1}}

        });
    }


    @Test
    public void testRunFunction() throws Exception {


        System.out.println(input[0]);
        System.out.println(expect[0]);

        elevator.setFloor(1);
        elevator.requestFloor(input[0], input[1]);

        elevator.run();

        this.assertPath(listner.getEventHistory(), expect);
 }


    private void assertPath(List<EventInfo> eventHistory, int[] expect) {

        int[] elevatorPath = eventHistory.stream().filter(e -> e.getEvent() == ElevatorEvent.ARRIVED).mapToInt(e -> e.getFloor()).toArray();
        assertThat(elevatorPath, is(expect));
    }
}
