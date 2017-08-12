package utility;

import common.ElevatorDirection;
import common.NoRequestException;

import java.util.Arrays;

/**
 * Created by I076057 on 8/12/2017.
 */
public class FindNextStopFloor {
    public static int find(ElevatorDirection direction, int currentFloor, int[] from, int[] to) throws NoRequestException {

        if (direction == ElevatorDirection.NONDIRCTION) {

            if (from.length > 0) {
                return FindNearestElement.find(from, currentFloor);
            } else {
                return FindNearestElement.find(to, currentFloor);
            }
        } else if (direction == ElevatorDirection.UPPER) {
            if (Arrays.stream(from).anyMatch(e -> e >= currentFloor)) {
                return Arrays.stream(from).filter(e -> e >= currentFloor).min().getAsInt();
            } else if (Arrays.stream(to).anyMatch(e -> e >= currentFloor)) {
                return Arrays.stream(to).filter(e -> e >= currentFloor).min().getAsInt();
            }
        } else {
            if (Arrays.stream(from).anyMatch(e -> e <= currentFloor)) {
                return Arrays.stream(from).filter(e -> e <= currentFloor).max().getAsInt();
            } else if (Arrays.stream(to).anyMatch(e -> e <= currentFloor)) {
                return Arrays.stream(to).filter(e -> e <= currentFloor).max().getAsInt();
            }

        }

        throw new NoRequestException();

    }
}
