package AcceptTest;

import AcceptTest.dsl.DSL;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by I076057 on 8/10/2017.
 */
public class SimplePassengerTest {

    public DSL dsl;

    @Before
    public  void init(){
        dsl = new DSL();
    }

    @Test
    public void Both_Passerge_and_elevator_on_same_floor_then_go_upstair() throws Exception {

        dsl.givenElevetorOnFloor(1);
        dsl.givenGotoFloor(1,1);
        dsl.whenElevatorRun();
        dsl.thenElevatorDontStart(1);

    }


    @Test
    public void Both_Passerge_and_elevator_on_same_floor_then_go_downStair() throws Exception {

        dsl.givenElevetorOnFloor(1);
        dsl.givenGotoFloor(1,-1);
        dsl.whenElevatorRun();
        dsl.thenElevatorDontStart(1);

    }


    @Test
    public void Both_Passerge_and_elevator_on_same_floor_then_go_sameFloor() throws Exception {

        dsl.givenElevetorOnFloor(1);
        dsl.givenGotoFloor(1,1);
        dsl.whenElevatorRun();
        dsl.thenElevatorDontStart(1);

    }



}
