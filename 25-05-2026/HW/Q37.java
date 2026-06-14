import java.util.Scanner;

public class Q37 {

    static int digitSum(int n) {

        int sum = 0;

        while (n > 0) {
            sum = sum + (n % 10);
            n = n / 10;
        }

        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter N:");
        int n = sc.nextInt();

        System.out.println("Enter R:");
        int r = sc.nextInt();

        for (int i = 1; i <= r; i++) {

            n = digitSum(n);
            if (n < 10) {
                break;
            }
        }

        System.out.println("Answer: " + n);
    }
}