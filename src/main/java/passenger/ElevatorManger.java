package passenger;

import common.EventInfo;
import elevator.SimpleElevator;
import listner.ElevatorEvent;
import listner.ElevatorLister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I076057 on 8/12/2017.
 */
public class ElevatorManger implements ElevatorLister {

    private final SimpleElevator elevator;

    private List<Passenger> passengerManger = new ArrayList<>();
    private List<Passenger> leavedPassenger = new ArrayList<>();


    public ElevatorManger(SimpleElevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void notify(ElevatorEvent event, int floor) {

        this.enterElevator(floor);
        this.LeaveElevator(floor);
       // this.enterElevator(floor);
    }

    private void enterElevator(int floor) {

        this.passengerManger.stream().filter(passenger -> passenger.getFrom() == floor)
                .forEach(passenger -> {
                     passenger.enterElevator();
                });

    }


    public void addRequestPassenger(Passenger passenger) {
        passengerManger.add(passenger);
    }

    public int size() {
        return passengerManger.size();
    }

    public List<Passenger> getLeavePerson() {
        return this.leavedPassenger;
    }

    private void LeaveElevator(int floor) {

        this.passengerManger.stream().filter(passenger -> passenger.isInElevator())
                .filter(passenger -> passenger.getTo() == floor)
                .forEach(passenger -> {
                    passenger.leaveElevator();
                    this.leavedPassenger.add(passenger);
                });

        this.passengerManger.removeIf(passenger -> passenger.getTo() == floor);
    }

    @Override
    public List<EventInfo> getEventHistory() {
        return this.getEventHistory();
    }
}