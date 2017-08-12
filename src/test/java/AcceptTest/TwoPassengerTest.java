package AcceptTest;

import AcceptTest.dsl.DSL;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by I076057 on 8/10/2017.
 */
public class TwoPassengerTest {

    DSL dsl = new DSL();

    @Before
    public void init ()
    {
        dsl = new DSL();
    }

    @Test
    public void twoPersonOnSameFloor_upstair() throws Exception {

        dsl.givenElevetorOnFloor(1);
        dsl.givenGotoFloor(1,2);
        dsl.givenGotoFloor(1,5);
        dsl.whenElevatorRun();
        dsl.thenFinalState(5);
        dsl.thenElevatorGoThroughPath( new int[] {1,2,3,4,5});
    }
}
