import listner.ElevatorEvent;
import org.junit.Before;
import org.junit.Test;
import passenger.ElevatorManger;
import passenger.Passenger;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by I076057 on 8/12/2017.
 */
public class ElevatorManagerTest extends ElevatorBaseTest {

    Passenger Bob;
    Passenger Alice;

    private ElevatorManger elevatorManager;

    @Before
    public void init() {
        this.elevatorManager = new ElevatorManger(elevator);
        this.Bob = new Passenger("Bob", 1, 1);
        this.Alice = new Passenger("Alice", 1,2);
    }

    @Test
    public void test_addPassenger() throws Exception {
        assertThat(elevatorManager.size(), is(0));
        elevatorManager.addRequestPassenger(Bob);
        assertThat(elevatorManager.size(), is(1));

        elevatorManager.addRequestPassenger(Alice);
        assertThat(elevatorManager.size(), is(2));
    }



    @Test
    public void test_addPassenger_one_usr_only_one_time() throws Exception {
        elevatorManager.addRequestPassenger(Bob);
        assertThat(elevatorManager.size(), is(1));


        elevatorManager.addRequestPassenger(Bob);
        assertThat(elevatorManager.size(), is(1));
    }

    @Test
    public void test_notify() throws Exception {

        elevatorManager.addRequestPassenger(Bob);
        elevatorManager.notify( ElevatorEvent.ARRIVED, 1);
        assertThat(elevatorManager.size(), is(0));
        assertThat(elevatorManager.getLeavePerson().get(0), is(Bob ));
    }


    @Test
    public void test_notify3() throws Exception {

        elevatorManager.addRequestPassenger(Bob);
        elevatorManager.notify( ElevatorEvent.ARRIVED, 2);
        assertThat(elevatorManager.size(), is(1));
        assertThat(elevatorManager.getLeavePerson(), is( new ArrayList<Passenger>()));
    }

    @Test
    public void test_notify2() throws Exception {

        elevatorManager.addRequestPassenger(Bob);
        elevatorManager.addRequestPassenger(Alice);
        elevatorManager.notify( ElevatorEvent.ARRIVED, 1);
        assertThat(elevatorManager.size(), is(1));
        assertThat(elevatorManager.getLeavePerson().get(0), is(Bob ));

        elevatorManager.notify(ElevatorEvent.ARRIVED, 2);
        assertThat(elevatorManager.getLeavePerson().get(1), is(Alice ));
    }


    @Test
    public void test_run_elevator() throws Exception {

        elevatorManager.setFloor(1);
        Bob.requestElevator(elevatorManager);
        elevatorManager.start();

        assertThat( elevatorManager.size(), is(0));
        assertThat(  elevatorManager.getLeavePerson().size(), is(1));
        assertThat(  elevatorManager.getLeavePerson().get(0), is(Bob));
    }
}
