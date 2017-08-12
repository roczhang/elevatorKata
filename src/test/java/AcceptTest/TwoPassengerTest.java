package AcceptTest;

import AcceptTest.dsl.DSL;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by I076057 on 8/10/2017.
 */
public class TwoPassengerTest {

    DSL dsl = new DSL();

    @Before
    public void init() {
        dsl = new DSL();
    }

    @Test
    public void twoPersonOnSameFloor_upstair() {

        dsl.givenElevetorOnFloor(1);
        dsl.givenGotoFloor(1, 2);
        dsl.givenGotoFloor(1, 5);
        dsl.whenElevatorRun();
        dsl.thenFinalState(5);
        dsl.thenElevatorGoThroughPath(new int[]{1, 2, 3, 4, 5});
    }

    // todo
    // accept test is simlar to the unit test, so ignore it.
    // in future ,it would be implement by the cucumber in easy way, like junit support parameterized
}
