package AcceptTest.dsl;

import MockListner.MockListner;
import elevator.SimpleElevator;
import listner.ElevatorLister;
import passenger.ElevatorManger;
import passenger.Passenger;

import static javafx.scene.input.KeyCode.M;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by I076057 on 8/13/2017.
 */
public class DSL2 {

    private ElevatorLister listner = new MockListner();
    ElevatorManger elevatorManager = new ElevatorManger( new SimpleElevator( listner ));

    Passenger Bob;
    Passenger Alice;


    public void give_Elevator_on_First_Floor() {

        elevatorManager.setFloor(1);
    }

    public void give_Bob_request_Elevator_from_1_to1() {
        Bob = new Passenger("Bob", 1, 1);
        Bob.requestElevator(elevatorManager);
    }


    public void give_Alice_request_Elevator_from_1_to2() {

        Alice = new Passenger("Alice", 1, 2);
        Alice.requestElevator(elevatorManager);

    }

    public void when_Elevator_Starts() {

        elevatorManager.start();
    }

    public void then_Bob_Leave_Elevator_on_floor1(){

        assertThat(elevatorManager.getLeavePerson().get(0), is(Bob));

    }

    public void then_Alice_Leave_Elevator_on_floor2() {

        assertThat(elevatorManager.getLeavePerson().get(1), is(Alice));

    }
}
