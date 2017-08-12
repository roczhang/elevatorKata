package passenger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import passenger.Passenger;

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

//    @Test
//    public void test_default_status() throws Exception {
//
//        assertThat(passenger.inElevator(), is(false));
//        assertThat(passenger.getFrom(), is(1));
//        assertThat(passenger.getTo(), is(2));
//    }
//
//    @Test
//    public void test_enter_go_out_elevator() throws Exception {
//
//
//        passenger.enterElevator();
//        assertThat(passenger.inElevator(), is(true));
//
//        passenger.goOutElevator();
//        assertThat(passenger.inElevator(), is(false));
//
//    }
//
//    @Test
//    public void test_passenger_intent() throws Exception {
//
//        passenger.setGoal(1,2);
//        assertThat( passenger.isUpperStair(), is(true));
//        assertThat( passenger.isDownStair(), is(false));
//
//        passenger.setGoal(2,1);
//        assertThat( passenger.isDownStair(), is(true));
//        assertThat( passenger.isUpperStair(), is(false));
//
//    }
//
//    @Test
//    public void tests_silly_passenger() throws Exception {
//
//        passenger.setGoal(1,1);
//        assertThat(passenger.isDownStair(), is(true));
//        assertThat(passenger.isUpperStair(), is( true));
//    }
}
