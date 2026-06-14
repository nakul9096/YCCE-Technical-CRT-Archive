public class Rotate2DArray90Degree {
    public static void main(String[] args){
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        print(matrix);
        transpose(matrix);
         System.out.println("Step 1: Transpose");
         print(matrix);
         reverse(matrix);
           System.out.println("Step 2: Reverse");
         print(matrix);
    }
    static void print(int[][] matrix){
        for(int[] row:matrix){
            for(int val:row){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
    static void transpose(int[][] matrix){
        int n = matrix.length;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
    }
    static void reverse(int[][] matrix){
        int n = matrix.length;
        for(int i=0;i<n;i++){
            int start=0;
            int end=n-1;
            while(start<end){
                int temp = matrix[i][start];
                matrix[i][start]=matrix[i][end];
                matrix[i][end]=temp;
                start++;
                end--;
            }
        }
    }
}
