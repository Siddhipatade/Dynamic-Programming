import java.util.*;

class ShortPath {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Map.Entry<Integer, Double>>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            double pathProb = succProb[i];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(v, pathProb));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(u, pathProb));
        }

        double[] maxProb = new double[n];
        maxProb[start] = 1d;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            for (Map.Entry<Integer, Double> neighbor : graph.getOrDefault(curNode, new ArrayList<>())) {
                int nxtNode = neighbor.getKey();
                double pathProb = neighbor.getValue();

                // Only update maxProb[nxtNode] if the current path increases
                // the probability of reaching nxtNode.
                if (maxProb[curNode] * pathProb > maxProb[nxtNode]) {
                    maxProb[nxtNode] = maxProb[curNode] * pathProb;
                    queue.offer(nxtNode);
                }
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

        // Create an instance of ShortPath
        ShortPath shortPath = new ShortPath();

        // Calculate maximum probability
        double maxProbability = shortPath.maxProbability(n, edges, succProb, start, end);

        // Output the result
        System.out.printf("Maximum probability: %.5f%n", maxProbability);
    }
}
