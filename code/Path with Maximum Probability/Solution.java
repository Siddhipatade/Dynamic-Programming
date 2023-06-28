// Approach 1: Bellman-Ford Algorithm
import java.util.Scanner;

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] maxProb = new double[n];
        maxProb[start] = 1.0;

        for (int i = 0; i < n - 1; i++) {
            boolean hasUpdate = false;
            for (int j = 0; j < edges.length; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                double pathProb = succProb[j];
                if (maxProb[u] * pathProb > maxProb[v]) {
                    maxProb[v] = maxProb[u] * pathProb;
                    hasUpdate = true;
                }
                if (maxProb[v] * pathProb > maxProb[u]) {
                    maxProb[u] = maxProb[v] * pathProb;
                    hasUpdate = true;
                }
            }
            if (!hasUpdate) {
                break;
            }
        }

        return maxProb[end];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input values
        System.out.print("Enter the number of vertices (n): ");
        int n = scanner.nextInt();
        System.out.print("Enter the number of edges (m): ");
        int m = scanner.nextInt();
        int[][] edges = new int[m][2];
        double[] succProb = new double[m];

        for (int i = 0; i < m; i++) {
            System.out.println("Enter the source and destination vertices of edge " + (i + 1) + ":");
            edges[i][0] = scanner.nextInt(); // Source vertex
            edges[i][1] = scanner.nextInt(); // Destination vertex
        }

        for (int i = 0; i < m; i++) {
            System.out.print("Enter the success probability of edge " + (i + 1) + ": ");
            succProb[i] = scanner.nextDouble(); // Success probability
        }

        System.out.print("Enter the start vertex: ");
        int start = scanner.nextInt();
        System.out.print("Enter the end vertex: ");
        int end = scanner.nextInt();

        // Create an instance of Solution
        Solution solution = new Solution();

        // Calculate maximum probability
        double maxProbability = solution.maxProbability(n, edges, succProb, start, end);

        // Output the result
        System.out.printf("Maximum probability: %.5f%n", maxProbability);
    }
}
