import java.util.Scanner;
public class Q31{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of Days: ");
        int n = scanner.nextInt();
        int sunday = n/7;
        int remainingDays = n%7;
        int maximumDays = sunday+(remainingDays>0?1:0);
        System.out.println("Minimum Possible Sundays: "+sunday);
        System.out.println("Maximum number of sundays: "+maximumDays);
    }
}