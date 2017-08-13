package AcceptTest;

import AcceptTest.dsl.DSL;
import AcceptTest.dsl.DSL2;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by I076057 on 8/12/2017.
 */
public class PassengerTakeElevator {

    DSL2 dsl;

    @Before
    public void init() {
        dsl = new DSL2();
    }


    @Test
    public void onePassengerTakeElevator() throws Exception {

        dsl.give_Elevator_on_First_Floor();
        dsl.give_Bob_request_Elevator_from_1_to1();
        dsl.when_Elevator_Starts();
        dsl.then_Bob_Leave_Elevator_on_floor1();
    }

    @Test
    public void TwoPassengerTakeElevator() throws Exception {

        dsl.give_Bob_request_Elevator_from_1_to1();
        dsl.give_Alice_request_Elevator_from_1_to2();
        dsl.give_Elevator_on_First_Floor();
        dsl.when_Elevator_Starts();
        dsl.then_Bob_Leave_Elevator_on_floor1();
        dsl.then_Alice_Leave_Elevator_on_floor2();
    }

}
