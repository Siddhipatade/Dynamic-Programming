import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> headWorkers = new PriorityQueue<>();
        PriorityQueue<Integer> tailWorkers = new PriorityQueue<>();

        // headWorkers stores the first k workers.
        // tailWorkers stores at most last k workers without any workers from the first k workers.
        for (int i = 0; i < candidates; i++) {
            headWorkers.add(costs[i]);
        }
        for (int i = Math.max(candidates, costs.length - candidates); i < costs.length; i++) {
            tailWorkers.add(costs[i]);
        }

        long answer = 0;
        int nextHead = candidates;
        int nextTail = costs.length - 1 - candidates;

        for (int i = 0; i < k; i++) {
            if (tailWorkers.isEmpty() || !headWorkers.isEmpty() && headWorkers.peek() <= tailWorkers.peek()) {
                answer += headWorkers.poll();

                // Only refill the queue if there are workers outside the two queues.
                if (nextHead <= nextTail) {
                    headWorkers.add(costs[nextHead]);
                    nextHead++;
                }
            } else {
                answer += tailWorkers.poll();

                // Only refill the queue if there are workers outside the two queues.
                if (nextHead <= nextTail) {
                    tailWorkers.add(costs[nextTail]);
                    nextTail--;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of workers: ");
        int n = scanner.nextInt();

        int[] costs = new int[n];
        System.out.println("Enter the costs of hiring each worker:");
        for (int i = 0; i < n; i++) {
            costs[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of workers to hire: ");
        int k = scanner.nextInt();

        System.out.print("Enter the number of candidates in each session: ");
        int candidates = scanner.nextInt();

        Solution solution = new Solution();
        long totalCost = solution.totalCost(costs, k, candidates);
        System.out.println("Total cost to hire " + k + " workers: " + totalCost);

        scanner.close();
    }
}
