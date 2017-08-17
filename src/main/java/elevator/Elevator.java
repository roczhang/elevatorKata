package elevator;

import listner.ElevatorLister;
import passenger.Passenger;

import java.util.List;

/**
 * Created by I076057 on 8/13/2017.
 */
public interface Elevator {


    void register(ElevatorLister listener);
    void setPassengerManager(List<Passenger> passengerManger);

    void setFloor(int floor);
    int getCurrentFloor();

    void run();

    void requestFloor(int from, int to);
    void requestTo(int to);
}
