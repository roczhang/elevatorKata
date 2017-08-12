import org.junit.Before;
import org.junit.Test;
import passenger.ElevatorManger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by I076057 on 8/12/2017.
 */
public class ElevatorManagerTest extends ElevatorBaseTest {

    private ElevatorManger elevatorManager;

    @Before
    public void init() {

        this.elevatorManager = new ElevatorManger(elevator);
    }

    @Test
    public void test_addPassenger() throws Exception {

        assertThat( elevatorManager.size(), is(0));


    }
}
