package passenger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by I076057 on 8/12/2017.
 */
public class ElevatorManger {

    List<Passenger> passengerManger = new ArrayList<>();

    public void addRequestPassenger(Passenger passenger) {


        passengerManger.add(passenger);
    }

    public int size() {
        return passengerManger.size();
    }

    public Passenger getLeavePerson() {
        return passengerManger.get(0);
    }
}
