import java.util.Scanner;
public class Q36{
public static void main(String[] args){
    System.out.println("Enter number of N Number of members: ");
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    long ways = 2*factorial(n-2);
    System.out.println("Total number of possible way of sitting="+ways);
}
static long factorial(int i){
    if(i==0 || i==1){
        return 1;
    }else{
        return i*factorial(i-1);
    }
}
}