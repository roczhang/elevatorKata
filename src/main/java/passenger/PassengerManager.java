//package passenger;
//
//import common.ElevatorDirection;
//import common.EventInfo;
//import elevator.SimpleElevator;
//import listner.ElevatorEvent;
//import listner.ElevatorLister;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Created by I076057 on 8/10/2017.
// */
//public class PassengerManager implements ElevatorLister {
//    private final SimpleElevator elevator;
//    private List<Passenger> passengers = new ArrayList<>();
//
//    public PassengerManager(SimpleElevator elevator) {
//        this.elevator = elevator;
//    }
//
//    public void register(Passenger passenger) {
//
//        if (passenger != null) {
//            this.passengers.add(passenger);
//        }
//
//    }
//
//    public int count() {
//        return passengers.size();
//    }
//
//
//    public void notify(ElevatorEvent event, EventInfo floorStatus) {
//
//        if (event == ElevatorEvent.OPENDOOR) {
//            this.enterElevator(floorStatus);
//        } else if (event == ElevatorEvent.CLOSEDOOR) {
//            this.LeaveElevator(floorStatus);
//        }
//
//    }
//
//    private void LeaveElevator(EventInfo floorStatus) {
//
//        this.passengers = this.passengers.stream().filter(passenger -> passenger.getTo() != floorStatus.getFloor())
//                .collect(Collectors.toList());
//    }
//
//    private void enterElevator(EventInfo floorStatus) {
//
////        this.passengers.stream().filter(passenger -> !passenger.inElevator())
////                .filter(passenger -> floorStatus.getDirection() == ElevatorDirection.UPPER ? passenger.isUpperStair() : passenger.isDownStair())
////                .filter(passenger -> passenger.getFrom() == floorStatus.getFloor())
////                .forEach(passenger -> passenger.enterElevator());
////
//    }
//
////
////    private void arrivedPassengerLeaveElevator(int floor) {
////        passengers.stream().filter(passenger -> passenger.inElevator())
////                .filter(passenger -> passenger.getTo() == floor)
////                .forEach(passenger -> {
////                    passenger.goOutElevator();
////                });
////
////
////        ArrayList<Passenger> results = new ArrayList<>();
////        passengers.stream().filter(passenger -> passenger.inElevator())
////                .forEach(passenger -> results.add(passenger));
////
////        this.passengers = results;
////    }
//
//    public int[] getFromRequest() {
//
//        int[] result = passengers.stream().filter(passenger -> !passenger.inElevator())
//                .mapToInt(passenger -> passenger.getFrom())
//                .toArray();
//
//        return result;
//
//    }
//
//    public int[] getToRequest() {
//        return this.passengers.stream().filter(passenger -> passenger.inElevator())
//                .mapToInt(passenger -> passenger.getTo())
//                .toArray();
//
//    }
//
//    public boolean hasPassengerInElevator() {
//
//        return this.passengers.stream().anyMatch(passenger -> passenger.inElevator());
//
//
//    }
//
//    @Override
//    public void notify(ElevatorEvent event, int elevator) {
//
//    }
//
//    @Override
//    public List<EventInfo> getEventHistory() {
//        return null;
//    }
//}
