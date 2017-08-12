import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utility.Convert;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by I076057 on 8/12/2017.
 */
@RunWith(Parameterized.class)
public class TwoPassengerSimpleElevatorTest2 extends  ElevatorBaseTest {


    @Parameterized.Parameters
    public  static Collection<Object[]> getModel(){

        return  Arrays.asList( new Object[][]{
                {"1. ", new int[]{}, new int[]{}, new int[]{}},
        });
    }

    private final int[] expected;
    private final String description;
    private final int[] from;
    private final int[] to;

    public TwoPassengerSimpleElevatorTest2(String discription , int [] from, int[] to, int[] expected){

        this.description = discription;
        this.from = from;
        this.to = to;
        this.expected = expected;

    }

    @Test
    public void test() throws Exception {

        elevator.setFloor(1);
        Arrays.stream(this.from).forEach(e->elevator.requestFromFloor(e));
        Arrays.stream(this.to).forEach(e->elevator.requestTo(e));

        int[] actual = listner.getEventHistory().stream().mapToInt(e -> e.getFloor()).toArray();
        assertThat(description, actual,is(expected));
    }
}
