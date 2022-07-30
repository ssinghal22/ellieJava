import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterviewPrep_Day2 {

    public static void main(String[] args) {
//		System.out.println(findPair(new int[]{2, -3, 1, 3, 3, 8}, -1));

        System.out.println(Arrays.toString(filterDups(new int[]{2, -3, 1, 3, 3, 8})));

        int[] input = new int[]{2, -3, 1, 0, 3, 3, 8, -8};
        int[] input2 = new int[]{0};
        bubbleSort(input);
        System.out.println(Arrays.toString(input));
        bubbleSort(input2);
        System.out.println(Arrays.toString(input2));

        System.out.println(isNumPalindrome(373));
        System.out.print(Integer.MAX_VALUE);

        // 1 1 2 3 5 8 13 21
        System.out.println(nthNumFibonacci(4));

        System.out.println(recursionFibonacci(4));
    }

    private static int recursionFibonacci(int n) {
        int nthFibonacci = 1;
        if(n < 1)
            return 0;
        if(n  == 1 || n == 2)
            return nthFibonacci;

        return recursionFibonacci(n - 1) + recursionFibonacci(n - 2);
    }

    private static int nthNumFibonacci(int n) {
        int nthFibonacci = 1;
        if(n < 1)
            return 0;
        if(n  == 1 || n == 2)
            return nthFibonacci;

        int fib1 = 1, fib2 = 1;

        for(int i = 3; i <= n; i++) {
            nthFibonacci = fib1 + fib2;

            fib1 = fib2;
            fib2 = nthFibonacci;
        }
        return nthFibonacci;
    }

    private static boolean isNumPalindrome(int original_num) {
        if(original_num > 2147447412 || original_num < 0) {
            return false;
        }
        int num = original_num;
        int reverse = 0;
        int ones;

        while(num > 0){ //do != 0 to handle negative numbers
            ones = num % 10;
            num = num / 10;
            reverse = reverse * 10 + ones;
        }

        if(reverse == original_num) {
            return true;
        }
        return false;
    }

    private static void bubbleSort(int[] lst) {
        for(int i = 0; i < lst.length - 1; i++) {
            for(int k = i+1; k < lst.length; k++){
                if(lst[i] > lst[k]) {
                    int temp = lst[i];
                    lst[i] = lst[k];
                    lst[k] = temp;
                }
            }
        }
    }

    private static int[] filterDups(int[] arr) {
        Set<Integer> set = new HashSet<>(arr.length);
        for (int i : arr) {
            set.add(i);
        }
        int [] result = new int[set.size()];
        int i = 0;
        for (Integer num: set) {
            result[i++] = (int)num;
        }
        return result;
    }
    //System.out.println(findPair(new int[]{2, 3, 1, 3, 3, 9, 13}, 15));

    private static boolean findPair(int[] lst, int sum) {
        if (lst.length < 2) {
            return false;
        }
        Set nums = new HashSet(lst.length);
        for(int num: lst){
            int diff = sum - num;
            if(nums.contains(diff)){
                return true;
            }
            nums.add(num);
        }
        return false;
    }
}