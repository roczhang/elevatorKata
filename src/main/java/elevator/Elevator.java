package elevator;

import listner.ElevatorLister;
import passenger.ElevatorController;
import passenger.Passenger;

import java.util.List;

/**
 * Created by I076057 on 8/13/2017.
 */
public interface Elevator {

    void register(ElevatorLister elevatorController);

    void setPassengerManager(List<Passenger> passengerManger);

    void setFloor(int floor);

    void run();

    int getCurrentFloor();

    void requestFloor(int from, int to);

    void requestTo(int to);
}
