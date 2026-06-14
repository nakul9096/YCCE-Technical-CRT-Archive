import java.util.*;

public class InfosysMedium {
    private static int calculateMex(List<Integer> team) {
        Set<Integer> set = new HashSet<>(team);
        int mex = 0;
        while (set.contains(mex)) {
            mex++;
        }
        return mex;
    }

    private static int solve(int[] arr, int start, Map<Integer, Integer> memo) {
        if (start >= arr.length) return 0;
        if (memo.containsKey(start)) return memo.get(start);

        int maxSum = 0;
        List<Integer> team = new ArrayList<>();

        for (int end = start; end < arr.length; end++) {
            team.add(arr[end]);
            int mex = calculateMex(team);
            int total = mex + solve(arr, end + 1, memo);
            maxSum = Math.max(maxSum, total);
        }

        memo.put(start, maxSum);
        return maxSum;
    }

    public static int maxExpertNumber(int[] arr) {
        return solve(arr, 0, new HashMap<>());
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 2, 1};
        System.out.println(maxExpertNumber(arr1));

        int[] arr2 = {0, 1, 2, 1, 0};
        System.out.println(maxExpertNumber(arr2));
    }
}