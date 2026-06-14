public class ReverseWords {
    public static void main(String[] args) {

        String str = "JAVA Is Back End Language";

        int end = str.length();

        for(int i = str.length() - 1; i >= 0; i--) {

            if(str.charAt(i) == ' ') {

                for(int j = i + 1; j < end; j++) {
                    System.out.print(str.charAt(j));
                }

                System.out.print(" ");
                end = i;
            }
        }

        for(int i = 0; i < end; i++) {
            System.out.print(str.charAt(i));
        }
    }
}