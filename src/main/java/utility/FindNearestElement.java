package utility;

/**
 * Created by I076057 on 8/11/2017.
 */
public class FindNearestElement {
    public static int find(int[] input, int base) {



        CustomerCompare customerCompare = new CustomerCompare(base);

        for (int i = 0; i < input.length; i++) {

            for (int j = i + 1; j < input.length; j++) {

                if (customerCompare.compare(input[j], input[i])<0) {
                    int tmp = input[j];
                    input[j] = input[i];
                    input[i] = tmp;
                }
            }
        }

        // assume that Upper stair than Down Stair if the are the same distance to the current floor
        if( input.length >=2){
            if( customerCompare.compare(input[0], input[1]) == 0)
                return Math.max(input[0], input[1]);
        }

        return input[0];
    }


}


class CustomerCompare {
    private final int base;

    public CustomerCompare(int base) {
        this.base = base;
    }

    int compare(int x, int y) {

        return Math.abs(x - base) - Math.abs(y - base);
    }
}