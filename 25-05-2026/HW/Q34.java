import java.util.Scanner;

public class Q34 {
    public static void main(String[] args) {
        System.out.println("Enter Value of N");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int product = 1;

        while (n != 0) {
            int digit = n % 10;
            product = product * digit;
            n = n / 10;
        }

        System.out.println(product);
    }
}