import java.util.Scanner;
public class Array_104{
    public static void main(String[] args){
        int totalSum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of Array: ");
        int n = scanner.nextInt();
        int[] A = new int[n];
        System.out.println("Enter the Elements of the Array: ");
        for(int i=0;i<n;i++){
            System.out.println("Enter "+i+" Element : ");
            A[i]=scanner.nextInt();
        }
        System.out.println("Enter Total Queries: ");
        int q = scanner.nextInt();
        for(int j=1;j<=q;j++){
            System.out.println("\nEnter Type of "+j+" Query : ");
            int type= scanner.nextInt();
            System.out.println("Enter the starting Index of "+j+" Query : ");
            int L = scanner.nextInt();
            System.out.println("Enter the Ending Index of "+j+" Query :");
            int r = scanner.nextInt();
            if(type==1){
                for(int i=L;i<=r;i++){
                    A[i]=((i-L+1)*A[L]);
                }
            }else if(type==2){
                int sum=0;
                for(int i=L;i<=r;i++){
                    sum = sum +A[i];
                }
                totalSum = totalSum + sum;
            }
        }
         System.out.println("Total sum of all type 2 queries : "+totalSum);
    }
}