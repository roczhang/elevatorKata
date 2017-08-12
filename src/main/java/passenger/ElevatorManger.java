package passenger;

import common.EventInfo;
import elevator.SimpleElevator;
import listner.ElevatorEvent;
import listner.ElevatorLister;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by I076057 on 8/12/2017.
 */
public class ElevatorManger implements ElevatorLister {

    private final SimpleElevator elevator;
    List<Passenger> passengerManger = new ArrayList<>();
    private List<Passenger> leavedPassenger = new ArrayList<>();

    public ElevatorManger(SimpleElevator elevator){
        this.elevator = elevator;
    }

    public void addRequestPassenger(Passenger passenger) {
        passengerManger.add(passenger);
    }

    public int size() {
        return passengerManger.size();
    }

    public Passenger getLeavePerson() {
        return passengerMangerg.get(0);
    }

    @Override
    public void notify(ElevatorEvent event, int floor) {

        this.LeaveElevator(floor);
        this.InElevator(floor);
    }

    private void InElevator(int floor) {

        this.passengerManger.stream().filter(passenger->passenger.getFrom() == floor)
                .forEach( passenger -> {
                    elevator.requestFromFloor(passenger.getTo());
                    passenger.enterElevator();
                });

    }

    private void LeaveElevator(int floor) {

        this.passengerManger.stream().filter(passenger->passenger.isInElevator())
                .filter(passenger -> passenger.getTo() == floor)
                .forEach(passenger -> {
                    this.leavedPassenger.add(passenger);
                });

    }

    @Override
    public List<EventInfo> getEventHistory() {
        return null;
    }
}
