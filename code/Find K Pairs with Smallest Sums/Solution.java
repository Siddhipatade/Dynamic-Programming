import java.util.*;

class Solution {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> visited = new HashSet<>();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(Arrays.asList(0, 0));

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int i = top[1];
            int j = top[2];

            ans.add(Arrays.asList(nums1[i], nums2[j]));

            if (i + 1 < m && !visited.contains(Arrays.asList(i + 1, j))) {
                minHeap.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                visited.add(Arrays.asList(i + 1, j));
            }

            if (j + 1 < n && !visited.contains(Arrays.asList(i, j + 1))) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                visited.add(Arrays.asList(i, j + 1));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the elements of nums1 (space-separated): ");
        String[] nums1Str = scanner.nextLine().split(" ");
        int[] nums1 = new int[nums1Str.length];
        for (int i = 0; i < nums1Str.length; i++) {
            nums1[i] = Integer.parseInt(nums1Str[i]);
        }

        System.out.print("Enter the elements of nums2 (space-separated): ");
        String[] nums2Str = scanner.nextLine().split(" ");
        int[] nums2 = new int[nums2Str.length];
        for (int i = 0; i < nums2Str.length; i++) {
            nums2[i] = Integer.parseInt(nums2Str[i]);
        }

        System.out.print("Enter the value of k: ");
        int k = scanner.nextInt();

        List<List<Integer>> result = kSmallestPairs(nums1, nums2, k);

        System.out.println("Pairs with the smallest sums:");
        for (List<Integer> pair : result) {
            System.out.println(pair);
        }
    }
}
