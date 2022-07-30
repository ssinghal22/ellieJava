import java.util.HashMap;
import java.util.Stack;

public class InterviewPrep_Day4 {

    public static void main(String[] args) {
        int x = 12;
        int y = 2;

        //System.out.println("x is '"+ x +"' and y is '"+ y +"'");

        x = y + x;
        y = x - y;
        x = x - y;

        //System.out.println("x is '"+ x +"' and y is '"+ y +"'");

        //makeXmasTree(2, '*');
        //makeXmasTree(6, '$');
        //makeNumberXmasTree(5);

        //printPerm("xyz", "");

        System.out.println(isValidCode("[{(){}[]}]"));
//		System.out.println(isValidCode("[a{(gg){sido}[kd]}]"));
//		System.out.println(isValidCode("[[[[[]"));
//		System.out.println(isValidCode("()dkkfo<>doldks{"));
//
//		countDups(new int[]{1,5,5,6,6,10,12});
//
//		System.out.println(isPrime(113));


        // There are many examples of the solution for the Vending Machine interview code challenge
        // Here are some links you may find interesting

        // https://www.careercup.com/question?id=5672363806949376
        // https://javarevisited.blogspot.com/2016/06/design-vending-machine-in-java.html


        // Here is an example of the Vending Machine assignment which explains the design:
        // https://cs1331.gitlab.io/fall2018/hw3/hw3-vending-machine.html
    }

    private static boolean isPrime(int num) {
        if (num <= 1)
            return false;

        if (num <= 3)
            return true;

        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        for(int i = 5; i*i <= num; i = i + 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    private static void countDups(int[] numberList) {
        HashMap<Integer, Integer> itemCount = new HashMap<>();

        for(int O = 0; O < numberList.length; O++){
            int item = numberList[O];
            if (itemCount.containsKey(item)) {
                itemCount.put(item, itemCount.get(item) + 1);
            }
            else {
                itemCount.put(item, 1);
            }
        }

        for(Integer k : itemCount.keySet()) {
            if(itemCount.get(k) > 1)
                System.out.println("There were "+ itemCount.get(k) +" of item '"+ k +"'");
        }

    }

    private static boolean isValidCode(String code) {
        Stack<Character> stack = new Stack<Character>();

        HashMap<Character, Character> brackets = new HashMap<Character, Character>();
        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');

        for(int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);

            if(brackets.containsKey(ch)) {
                stack.push(brackets.get(ch));
            }
            else if(brackets.containsValue(ch)) {
                if(!stack.empty() && stack.peek().equals(ch))
                    stack.pop();
                else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    private static void printPerm(String str, String result) {
        if(str.length() == 1) {
            System.out.print(result + str + " ");
            return;
        }

        for (int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            String other = str.substring(0, i) + str.substring(i+1);

            printPerm(other, result + ch);
        }
    }

    private static void makeXmasTree(int height, char ornament) {
        for(int i = 1; i <= height; i++) {

            for(int j = height - i; j > 0; j--)
                System.out.print(" ");

            for(int k = 1; k <= i; k++)
                System.out.print(ornament + " ");

            System.out.println("");

        }

        // Trunk of the tree
        int trunk_width = height;
        if(height % 2 == 0)
            trunk_width = height - 1;

        for(int l = 0; l < 2; l++) {


            for(int z = 1; z <= (trunk_width + 1) / 2; z++)
                System.out.print(" ");
            for(int w = 0; w < trunk_width; w++)
                System.out.print("|");
            System.out.println("");
        }
    }

    private static void makeNumberXmasTree(int height) {
//		int ornament = 1;
        for(int i = 1; i <= height; i++) {

            for(int j = height - i; j > 0; j--)
                System.out.print(" ");

            for(int k = 1; k <= i; k++)
                System.out.print(k + " ");

            System.out.println("");

        }

        // Trunk of the tree
        int trunk_width = height;
        if(height % 2 == 0)
            trunk_width = height - 1;

        for(int l = 0; l < 2; l++) {


            for(int z = 1; z <= (trunk_width + 1) / 2; z++)
                System.out.print(" ");
            for(int w = 0; w < trunk_width; w++)
                System.out.print("|");
            System.out.println("");
        }
    }
}
