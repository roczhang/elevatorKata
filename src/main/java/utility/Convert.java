package utility;

import java.util.List;

/**
 * Created by I076057 on 8/12/2017.
 */
public class Convert {


    public static int[] fromListtoArray(List<Integer> list) {

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

}


