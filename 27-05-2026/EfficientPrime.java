public class EfficientPrime {
    public static void main(String[] args) {
        int n = 40;
        boolean arr[] = new boolean[n + 1];

        for(int i = 2; i <= n; i++) {
            arr[i] = true;
        }

        for(int i = 2; i <= n; i++) {

            if(arr[i] == true) {

                System.out.println("\nIteration for " + i);

                for(int j = i + i; j <= n; j = j + i) {
                    System.out.println("Removing: " + j);
                    arr[j] = false;
                }
            }
        }

        System.out.println("\nPrime Numbers:");

        for(int i = 2; i <= n; i++) {
            if(arr[i] == true) { 
                System.out.print(i + " ");
            }
        }
    }
}