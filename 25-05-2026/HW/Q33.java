public class Q33 {
    public static int countElements(int[] arr, int n) {
        int count = 1; 
        int max = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                count++;
                max = arr[i];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 8, 2, 9};
        int n = arr.length;

        System.out.println(countElements(arr, n));
    }
}