package listner;

import common.EventInfo;
import elevator.SimpleElevator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I076057 on 8/10/2017.
 */
public interface ElevatorLister {
    public void notify( ElevatorEvent event, SimpleElevator elevator);
    List<EventInfo> getEventHistory();
}
