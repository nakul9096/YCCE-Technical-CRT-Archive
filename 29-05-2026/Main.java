//input [2,8,7,10,5]
//output [2->8,8->10,10->-1,5->-1,7->-1]
//next element should be bigger using stack
public class Main {

    public static void main(String[] args) {

        int arr[] = {2, 8, 7, 10, 5};

        boolean used[] = new boolean[arr.length];

        int current = arr[0];
        int currentIndex = 0;

        used[0] = true;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > current) {

                System.out.println(
                    current + " -> " + arr[i]
                );

                current = arr[i];

                currentIndex = i;

                used[i] = true;
            }
        }

        System.out.println(current + " -> -1");

        // elements not used in chain
        for (int i = 0; i < arr.length; i++) {

            if (!used[i]) {

                System.out.println(
                    arr[i] + " -> -1"
                );
            }
        }
    }
}