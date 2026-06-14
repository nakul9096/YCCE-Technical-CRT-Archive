import java.util.Scanner;
public class TC_132{
    public static void main(String[] args){
        System.out.println("Enter Number to find whether prime or not");
        int num;
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        boolean[] A = new boolean[num+1];
        for(int i=2;i<=num;i++){
            if(i%2==0){
                A[i]=true;
            }
            
        }
    }
}