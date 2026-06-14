import java.util.*;

public class Infosys_PS {

    static final long MOD = 1000000007;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] a = new long[n];

        for(int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        int q = sc.nextInt();

        long totalAnswer = 0;

        while(q-- > 0) {

            int type = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();


            if(type == 1) {

                long first = a[l];

                int k = 1;

                for(int i = l; i <= r; i++) {
                    a[i] = (k * first) % MOD;
                    k++;
                }

            }
            else if(type == 2) {

                long sum = 0;

                for(int i = l; i <= r; i++) {
                    sum = (sum + a[i]) % MOD;
                }

                totalAnswer = (totalAnswer + sum) % MOD;
            }
        }

        System.out.println(totalAnswer);
    }
}