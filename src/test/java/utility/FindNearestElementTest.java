package utility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created by I076057 on 8/11/2017.
 */

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;



@RunWith(Parameterized.class)
public class FindNearestElementTest {

    @Parameterized.Parameters(name = "{index}: Elevator({1}) = {2} ")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1}, 1, 1},
                {new int[]{1,2}, 1, 1},
                {new int[]{1,3}, 2, 3},
                {new int[]{1,2}, 2, 2},
                {new int[]{1,4}, 3, 4},
                {new int[]{1,4}, 2, 1},
                {new int[]{1,2,3,4}, 2, 2},
                {new int[]{4,2,1,3}, 2, 2},
                {new int[]{4,2,1,3}, 8, 4},
                {new int[]{4,2,1,3}, -8, 1},
        });
    }


    private final int[] input;
    private final int base;
    private final int expected;

   public FindNearestElementTest(int[] input, int base, int expected){
        this.input = input;
        this.base = base;
        this.expected = expected;
    }

    @Test
    public void test_siimpleElement() throws Exception {

        assertThat( FindNearestElement.find(input, base), is( expected));
    }
}
