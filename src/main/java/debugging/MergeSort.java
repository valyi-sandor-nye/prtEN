package debugging;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    public static Random rand = new Random();

    /**
     * requires: - 'array' is not null
     * ensures:  - returns a permutation of 'array' such that for each index 'i'
     *             and index 'j', if 'i' < 'j', 'array[i]' < 'array[j]'.
     *           - 'array' is unchanged.
     **/
    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    public static int[] sort(int[] array, int from, int to) {
        if (from >= to) {
            return new int[] { array[from] };
        }

        int m = (from + to) / 2;
        int[] left = sort(array, from, m - 1);
        int[] right = sort(array, m + 1, to);

        int[] result = new int[left.length + right.length];
        int li = 0;
        int ri = 0;
        for (int i = 0; i < result.length; i++) {
            if (li < left.length
                    && (ri >= right.length || left[li] < right[ri])) {
                result[i] = left[li];
                li++;
            } else {
                result[i] = right[ri];
                ri++;
            }
        }

        return result;
    }

    public static boolean isSorted(int[] array) {
        boolean ret = true;

        for (int i = 0 ; i < array.length-1 ; i++)
            if (array[i] > array[i+1])
                ret = false;

        return ret;
    }

    public static boolean isPermutation(int[] arrayA, int[] arrayB) {
        if (arrayA.length != arrayB.length) return false;

        int []arrAsorted=arrayA;
        int []arrBsorted=arrayB;

        Arrays.sort(arrAsorted);
        Arrays.sort(arrBsorted);

        for(int i = 0; i  <  arrayA.length; i++){
            if(arrayA[i] != arrayB[i])
                return false;
        }
        return true;
    }

    public static void main(String[] arg) {
        int[] array = {1,2,10,4,42,67,8};


        if (isSorted(sort(array)) && isPermutation(array, sort(array)))
            System.out.println("The array is sorted.");
        else {
            System.out.println("The array is not sorted.");
        }
    }

}