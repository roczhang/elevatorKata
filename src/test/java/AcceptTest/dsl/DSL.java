package AcceptTest.dsl;

import MockListner.MockListner;
import elevator.SimpleElevator;
import passenger.ElevatorManger;
import passenger.Passenger;
import utility.AssertUtility;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by I076057 on 8/10/2017.
 */
public class DSL {

    Passenger passenger;
    private MockListner listener = new MockListner();
    private SimpleElevator elevator = new SimpleElevator(listener);
    private ElevatorManger elevatorManager = new ElevatorManger(elevator);
    private Passenger Bob;
    private Passenger Alice;


    public void givenElevetorOnFloor(int floor) {

        elevator.setFloor(floor);
    }

    public void givenGotoFloor(int from, int to) {

        elevator.requestFloor(from, to);
    }

    public void whenElevatorRun() {

        elevator.run();
    }

    public void thenFinalState(int floor) {
        assertThat(elevator.getCurrentFloor(), is(floor));
    }

    public void thenElevatorGoThroughPath(int[] expectPath) {

        AssertUtility.assertPath(listener.getEventHistory(), expectPath);
    }


    public void give_Bob_request_Elevator_from_1_to1() {
        Bob = new Passenger("Bob",1,1);
        Bob.requestElevator(elevatorManager);
    }



    public void give_Alice_request_Elevator_from_1_to2() {

        Alice = new Passenger("Alice",1,2);
        Alice.requestElevator(elevatorManager);

    }


    public void give_Elevator_on_First_Floor() {

        elevator.setFloor(1);
    }

    public void thenBobLeaveElevator() {

        assertThat(elevatorManager.getLeavePerson().get(0), is(Bob));

    }


    public void thenAliceLeaveElevator() {

        assertThat(elevatorManager.getLeavePerson().get(1), is(Alice));

    }
}


