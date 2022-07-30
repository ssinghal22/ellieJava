import java.util.Arrays;

public class InterviewPrep_Day1 {

    private static void swapReverse(int [] arr) {
        int temp;
        for(int i=0; i < arr.length/2; i++) {
            temp = arr[i];
            arr[i] = arr[(arr.length -1) - i];
            arr[(arr.length -1) - i] = temp;
        }
    }

    public static void main(String[] args) {
//		int [] arr = {24, 6, 3, 21, 1, 3};

//		find2TopNums(arr);
//		find2TopNums(new int[]{5,5,6,6,12,12});
//		find2TopNums(new int[]{-2, 33, -100});
//		find2TopNums(new int[]{-2, -33, -100});
//		find2TopNums(new int[0]);

//		int i = 0;
//		int x = 5 + i++; //after - i=1
//		System.out.println(x); // 5
//		int y = 5 + i++;//after - i = 2
//		System.out.println(y); // 6
//
//		int z = 5 + ++i;
//		System.out.println(z); // 8

//		System.out.println(printEvens(10));

        //System.out.println(findPair(new int[]{2, 5, 1, 3, 3, 8}, 6));
        System.out.println(reverseString("soniya"));

    }

    // Not the most efficient way...
    // The efficient way is for homework  ;)
    private static boolean findPair(int[] arr, int sum) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum)
                    return true;
            }
        }
        return false;
    }

    private static String printEvens(int num) {
        String result = "";
//		for(int i=0; i < num; i+=2)
//			result += i + " ";
        int i = 0;
        while (i <= num) {
            result += i;
            i += 2;
        }
        return result.trim();
    }

    private static void find2TopNums(int[] arr) {

        if (arr.length < 2) {
            System.out.print("The input array length must me at least 2");
            return;
        }

        int firstTopNum = Integer.MIN_VALUE;
        int secondTopNum = Integer.MIN_VALUE;

        for (int i: arr) {   // 1

            if (i > firstTopNum) {   //24
                secondTopNum = firstTopNum;
                firstTopNum = i;
            }
            else if (i > secondTopNum) {
                secondTopNum = i;   // 21
            }

        }

        System.out.println("The top number is '"+ firstTopNum
                +"' and the second top number is '"+ secondTopNum +"'");
    }

    private static String swapWords(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";

        for(String word: words) {
            result = word + " " + result;
        }
        return result.trim();

    }

    private static String reverseString(String str) {
        char temp;
        char[] arr = str.toCharArray();

        for(int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[(arr.length -1) - i];
            arr[(arr.length -1) - i] = temp;
        }
        return Arrays.toString(arr);
        //return new String(arr);
//		return String.valueOf(arr);
    }

    private static String reverseStr(String str) {
        String result = "";

        for(int i = str.length() - 1; i >= 0; i--) {
            result = result + str.charAt(i);
        }

        return result;
    }

}

