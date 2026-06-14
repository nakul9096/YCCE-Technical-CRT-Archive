import java.util.Scanner;
public class D1{
    public static void main(String[] args){
        int n = 40;
        boolean arr[] = new boolean[n+1];
        for(int i=2;i<=n;i++){
            arr[i]=true;
        }
        for(int i=2;i<=n;i++){
            if(arr[i]==true){
                System.out.println("\n Iteration for "+i);
                for(int j=i+i;j<=n;j=j+i){
                    System.out.println("Removing: "+j);
                }
            }
        }
    }
}