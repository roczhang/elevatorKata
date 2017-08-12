package utility;

import common.EventInfo;
import listner.ElevatorEvent;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by I076057 on 8/10/2017.
 */
public class AssertUtility {


    public static void assertPath(List<EventInfo> eventHistory, int[] expect) {

        int[] elevatorPath = eventHistory.stream().filter(e -> e.getDirection() == ElevatorEvent.ARRIVED).mapToInt(e -> e.getFloor()).toArray();
        assertThat(elevatorPath, is(expect));
    }
}
