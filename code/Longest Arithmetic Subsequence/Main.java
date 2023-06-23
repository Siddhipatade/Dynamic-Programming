import java.util.HashMap;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("unchecked")
    public static int longestArithSeqLength(int[] nums) {
        int maxLength = 0;
        HashMap<Integer, Integer>[] dp = new HashMap[nums.length];
        for (int right = 0; right < nums.length; ++right) {
            dp[right] = new HashMap<>();
            for (int left = 0; left < right; ++left) {
                int diff = nums[left] - nums[right];
                dp[right].put(diff, dp[left].getOrDefault(diff, 1) + 1);
                maxLength = Math.max(maxLength, dp[right].get(diff));
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int result = longestArithSeqLength(nums);
        System.out.println("Length of the longest arithmetic subsequence: " + result);
    }
}
