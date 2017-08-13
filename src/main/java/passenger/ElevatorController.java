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
    private int weightLimitation;

    private List<Passenger> passengerManger = new ArrayList<>();
    private List<Passenger> leavedPassenger = new ArrayList<>();


    public ElevatorManger(SimpleElevator elevator) {
        this.elevator = elevator;
        elevator.register(this);
        elevator.setPassengerManager(passengerManger);
        this.weightLimitation = 150;
    }

    public void addRequestPassenger(Passenger passenger) {

        if (!passengerManger.contains(passenger)) {
            passengerManger.add(passenger);


//            int currentWeight = this.passengerManger.stream()
//                    .filter( e->e.isInElevator())
//                    .map(e -> e.getWeight())
//                    .reduce(0, (a, b) -> a + b);
//
//            if (currentWeight + passenger.getWeight() <= this.weightLimitation){
//
//                elevator.requestFromFloor(passenger.getFrom());
//            }

        }
    }

    public void start() {
        this.elevator.run();
    }

    @Override
    public void notify(ElevatorEvent event, int floor) {

        this.enterElevator(floor);
        this.LeaveElevator(floor);
        //this.enterElevator(floor);
    }

    private void enterElevator(int floor) {

        this.passengerManger.stream().filter(passenger -> passenger.getFrom() == floor)
                .filter(passenger -> !passenger.isInElevator())
                .forEach(passenger -> {

                    int currentWeight = getWeightOnPassengerInFloor(passengerManger);
                    if (currentWeight + passenger.getWeight() < this.weightLimitation) {
                        passenger.enterElevator();
                        elevator.requestTo(passenger.getTo());
                    }

                });

//        this.passengerManger.stream().filter(passenger -> passenger.getFrom() == floor)
//
//                .forEach(passenger -> {
//                    passenger.enterElevator();
//                    elevator.requestTo(passenger.getTo());
//                });

    }


    private void LeaveElevator(int floor) {

        List<Passenger> offElevatorPassenger = new ArrayList<>();
        this.passengerManger.stream().filter(passenger -> passenger.isInElevator())
                .filter(passenger -> passenger.getTo() == floor)
                .forEach(passenger -> {
                    passenger.leaveElevator();
                    this.leavedPassenger.add(passenger);
                    offElevatorPassenger.add(passenger);
                });

        offElevatorPassenger.forEach( passenger -> this.passengerManger.remove(passenger));
    }

    private int getWeightOnPassengerInFloor(List<Passenger> passengerManger) {
        return this.passengerManger.stream()
                .filter(passenger -> passenger.isInElevator())
                .map(passenger -> passenger.getWeight())
                .reduce(0, (a, b) -> a + b);

    }

    private void getWaitingPassengerFromOutsideOfElevator(int floor) {
        Object[] waitingList = this.passengerManger.stream().filter(passenger -> passenger.getFrom() == floor)
                .filter(passenger -> !passenger.isInElevator()).toArray();
    }


    public void setFloor(int floor) {
        elevator.setFloor(floor);
    }


    public int size() {
        return passengerManger.size();
    }

    public List<Passenger> getLeavePerson() {
        return this.leavedPassenger;
    }


    @Override
    public List<EventInfo> getEventHistory() {
        return this.getEventHistory();
    }

    public void setWeightLimitation(int weightLimitation) {
        this.weightLimitation = weightLimitation;
    }
}
