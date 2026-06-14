//sort a stack using another only on stack & only one variable.
//-> sort  the elements of a stack in ascending order
// using only stack operations. ( no array or list )
// input [34,3,31,98,92,23]
import java.util.Scanner;
import java.util.Stack;
public class Stack_412{
    public static void sortStack(Stack<Integer> input){
        Stack<Integer> tempStack = new Stack<>();
          while (!input.isEmpty()) {
            int temp = input.pop();
            while (!tempStack.isEmpty() && tempStack.peek() > temp) {
                input.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
    while (!tempStack.isEmpty()) {
            input.push(tempStack.pop());
        }
    }
       public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        // Example input
        int[] arr = {34, 3, 31, 98, 92, 23};
        for (int num : arr) {
            stack.push(num);
        }
        sortStack(stack);
        System.out.println("Sorted stack (top to bottom):");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}