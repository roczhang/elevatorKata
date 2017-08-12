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


    @Parameterized.Parameters
    public  static Collection<Object[]> getModel(){

        return  Arrays.asList( new Object[][]{
                {"1. ", new int[]{}, new int[]{}, new int[]{}},
                {"2. ", new int[]{1,1}, new int[]{1,1}, new int[]{1}}
                ,
                {"3. ", new int[]{1,1}, new int[]{2,3}, new int[]{1,2,3}},
                {"4. ", new int[]{1,1}, new int[]{-2,-3}, new int[]{1,0,-1,-2,-3}},
                {"5. ", new int[]{1,1}, new int[]{-2,2}, new int[]{1,2,1,0,-1,-2}},
                {"6. ", new int[]{1,1}, new int[]{2,-2}, new int[]{1,2,1,0,-1,-2}},

                {"7. ", new int[]{2,3}, new int[]{2,3}, new int[]{1,2,3}},
                {"8. ", new int[]{2,3}, new int[]{3,4}, new int[]{1,2,3,4}},
                {"9. ", new int[]{2,3}, new int[]{4,5}, new int[]{1,2,3,4,5}},
                {"10. ", new int[]{2,3}, new int[]{2,0}, new int[]{1,2,3,2,1,0}},
                {"11. ", new int[]{2,3}, new int[]{-1,-2}, new int[]{1,2,3,2,1,0,-1,-2}},


                {"12. ", new int[]{-2,-3}, new int[]{0,1}, new int[]{1,0,-1,-2,-3,-2,-1,0,1}},
                {"13. ", new int[]{-2,-3}, new int[]{3,4}, new int[]{1,0,-1,-2,-3,-2,-1,0,1,2,3,4}},
                {"14. ", new int[]{2,3}, new int[]{-4,-5}, new int[]{1,2,3,2,1,0,-1,-2,-3,-4,-5}},


        });
    }

    @Test
    public void twoPasserTestCase() throws Exception {

        elevator.setFloor(1);
        Arrays.stream(this.from).forEach(e->elevator.requestFromFloor(e));
        Arrays.stream(this.to).forEach(e->elevator.requestTo(e));

        elevator.run();

        int[] actual = listner.getEventHistory().stream().mapToInt(e -> e.getFloor()).toArray();
        assertThat(description, actual,is(expected));
    }
}
