package utility;

/**
 * Created by I076057 on 8/11/2017.
 */
public class FindNearestElement {
    public static int find(int[] input, int base) {


        if (input.length == 1) return input[0];

        if (input.length == 2) return new CustomerCompare(base).compare(input[0], input[1]) ? input[0] : input[1];


        CustomerCompare customerCompare = new CustomerCompare(base);

        for (int i = 0; i < input.length; i++) {

            for (int j = i + 1; j < input.length; j++) {

                if (customerCompare.compare(input[j], input[i])) {
                    int tmp = input[j];
                    input[j] = input[i];
                    input[i] = tmp;
                }
            }
        }


        return input[0];
    }


}


class CustomerCompare {
    private final int base;

    public CustomerCompare(int base) {
        this.base = base;
    }

    boolean compare(int x, int y) {

        return Math.abs(x - base) < Math.abs(y - base);
    }
}