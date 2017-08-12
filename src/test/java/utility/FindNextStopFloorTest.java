package utility;

import common.ElevatorDirection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by I076057 on 8/12/2017.
 */
@RunWith(Parameterized.class)
 public class FindNextStopFloorTest {

    ;

    private final String discription;
    private int expected;
    private int[] from;
    private int[] to;

    public FindNextStopFloorTest(String discription, int[] from, int[] to, int expected) {

        this.discription = discription;
        this.from = from;
        this.to = to;
        this.expected = expected;

        System.out.println(discription);
    }

    @Parameterized.Parameters(name = "{index}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {"0. from 1 ", new int[]{1}, new int[]{}, 1},
                {"1. to 1 ", new int[]{}, new int[]{1}, 1},

                {"2. from 1 ", new int[]{2}, new int[]{}, 2},
                {"3. to 1 ", new int[]{}, new int[]{2}, 2},

                {"4. uppe to same direction  ", new int[]{}, new int[]{2, 3}, 2},
                {"5. down to same direction  ", new int[]{}, new int[]{-1, -2}, -1},
                {"6. upper to same direction  ", new int[]{}, new int[]{2, -1}, 2},
                {"7. upstair over down stair ", new int[]{}, new int[]{-1, 3}, 3},
                {"8. upstair over down stair ", new int[]{}, new int[]{3, -1}, 3},


                {"9. from diffrent floor ", new int[]{2, 3}, new int[]{}, 2},
                {"10. from different floor ", new int[]{-2, -1}, new int[]{}, -1},
                {"11. from diffrent floor ", new int[]{2, -3}, new int[]{}, 2},
                {"12. from different floor ", new int[]{0, 2}, new int[]{}, 2},
                {"13. from different floor ", new int[]{2, 0}, new int[]{}, 2},

        });
    }

    @Test
    public void getSmartFloor_nonDirection() throws Exception {

        int nextTargertFloor = FindNextStopFloor.find(ElevatorDirection.NONDIRCTION, 1, this.from, this.to);
        assertThat(nextTargertFloor, is(expected));
    }


    @Test()
    public void getSmartFloor_upperStair() throws Exception {
        try {

            int nextTargertFloor = FindNextStopFloor.find(ElevatorDirection.UPPER, 1, this.from, this.to);
            assertThat(nextTargertFloor, is(expected));
        } catch (NoRequestException e) {

            assertThat(Arrays.stream(from).allMatch(x -> x < 1), is(true));
            assertThat(Arrays.stream(to).allMatch(x -> x < 1), is(true));

        }
    }

//
//    @Test
//    public void getSmartFloor_downStair() throws Exception {
//
//        try {
//            int nextTargertFloor = FindNextStopFloor.find(ElevatorDirection.DOWN, 1, this.from, this.to);
//            assertThat(nextTargertFloor, is(expected));
//        } catch (NoRequestException e) {
//
//            assertThat(Arrays.stream(from).allMatch(x -> x > 1), is(true));
//            assertThat(Arrays.stream(to).allMatch(x -> x > 1), is(true));
//        }
//    }

}