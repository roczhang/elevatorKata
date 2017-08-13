package AcceptTest;

import AcceptTest.dsl.DSL2;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by I076057 on 8/13/2017.
 */
public class PassengerTakeElevatorConcernedWeight {


    DSL2 dsl;

    @Before
    public void init() {
        dsl = new DSL2();
    }



    @Test
    public void Passengers_are_overwight() throws Exception {

        dsl.give_Elevator_with_limitation_on_120KG_on_First_Floor();

        dsl.give_Bob_60KG_request_Elevator_from_1_to3();
        dsl.give_Alice_100KG_request_Elevator_from_1_to2();

        dsl.when_Elevator_Starts();
        dsl.then_Bob_Leave_Elevator_on_floor();
        dsl.then_Alice_Leave_Elevator_on_floor();
        dsl.then_elevator_go_throgh_Floor_1_2_3_2_1_2();
    }
}
