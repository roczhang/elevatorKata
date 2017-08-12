package passenger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by I076057 on 8/10/2017.
 */
@Ignore
public class PassengerTest {


    private Passenger passenger;

    @Before
    public void init() {

        passenger = new Passenger(1, 2);
    }

    @Test
    public void test_default_status() throws Exception {

        assertThat(passenger.isInElevator(), is(false));
        assertThat(passenger.getFrom(), is(1));
        assertThat(passenger.getTo(), is(2));
    }

    @Test
    public void test_enter_go_out_elevator() throws Exception {


        passenger.enterElevator();
        assertThat(passenger.isInElevator(), is(true));

        passenger.leaveElevator();
        assertThat(passenger.isInElevator(), is(false));

    }
}