//create a customized stack without using predefined func
import java.util.Scanner;
public class Stack{
    int n=5;
    int stack[] = new int[n];
    int top = -1;

    void push(int data) {
        if(top == n-1) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            stack[top] = data;
            System.out.println(data + " inserted");
        }
    }

    void pop() {
        if(top == -1) {
            System.out.println("Stack Underflow");
        } else {
            System.out.println("Deleted: " + stack[top]);
            top--;
        }
    }

    void peek() {
        if(top == -1) {
            System.out.println("Stack Empty");
        } else {
            System.out.println("Top element: " + stack[top]);
        }
    }

    void display() {
        if(top == -1) {
            System.out.println("Stack Empty");
        } else {
            for(int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }
    public static void main(String[] args){
        System.out.println("*******STACK MENU DRIVEN PROGRAM*********");
        System.out.println("1. Push into the stack");
        System.out.println("2. Pop element from the stack");
        System.out.println("3. Display stack element");
        System.out.println("4. peek element");
        System.out.println("Enter choice");
        int choice;
        Scanner scanner = new Scanner(System.in);
        Stack st = new Stack();
        while(true){
            System.out.println("Enter choice");
            choice = scanner.nextInt();
            switch(choice){
                case 1: System.out.println("Enter element to push into the stack");
                        int data = scanner.nextInt();
                        st.push(data);
                        break;
                case 2: st.pop();
                        break;
                case 3: st.display();
                        break;
                case 4: st.peek();
                        break;
                case 0: System.out.println("Exiting"); scanner.close(); return;
                default: System.out.println("Invalid choice");
            }
        }
    }   
}
