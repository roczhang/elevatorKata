package AcceptTest;

import AcceptTest.dsl.DSL;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by I076057 on 8/12/2017.
 */
public class PassengerTakeElevator {

    DSL dsl;

    @Before
    public void init() {
        dsl = new DSL();
    }


    @Test
    public void onePassengerTakeElevator() throws Exception {

        dsl.give_Bob_request_Elevator_from_1_to1();
        dsl.give_Elevator_on_First_Floor();
        dsl.whenElevatorRun();
        dsl.thenBobLeaveElevator();
    }

    @Test
    public void TwoPassengerTakeElevator() throws Exception {

        dsl.give_Bob_request_Elevator_from_1_to1();
        dsl.give_Alice_request_Elevator_from_1_to2();

        dsl.give_Elevator_on_First_Floor();
        dsl.whenElevatorRun();
        dsl.thenBobLeaveElevator();
        dsl.thenAliceLeaveElevator();

    }
}
