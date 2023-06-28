from collections import defaultdict, deque
from typing import List


class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start: int, end: int) -> float:
        graph = defaultdict(list)
        for i, (a, b) in enumerate(edges):
            graph[a].append([b, succProb[i]])
            graph[b].append([a, succProb[i]])

        max_prob = [0.0] * n
        max_prob[start] = 1.0

        queue = deque([start])
        while queue:
            cur_node = queue.popleft()
            for nxt_node, path_prob in graph[cur_node]:

                # Only update max_prob[nxt_node] if the current path increases
                # the probability of reaching nxt_node.
                if max_prob[cur_node] * path_prob > max_prob[nxt_node]:
                    max_prob[nxt_node] = max_prob[cur_node] * path_prob
                    queue.append(nxt_node)

        return max_prob[end]


# Take input from the user
n = int(input("Enter the number of vertices (n): "))
m = int(input("Enter the number of edges (m): "))
edges = []
succProb = []

for i in range(m):
    print(f"Enter the source and destination vertices of edge {i+1}:")
    u = int(input())
    v = int(input())
    edges.append([u, v])

for i in range(m):
    print(f"Enter the success probability of edge {i+1}: ")
    p = float(input())
    succProb.append(p)

start = int(input("Enter the start vertex: "))
end = int(input("Enter the end vertex: "))

# Create an instance of Solution
solution = Solution()

# Calculate maximum probability
maxProbability = solution.maxProbability(n, edges, succProb, start, end)

# Output the result
print(f"The maximum probability from vertex {start} to vertex {end} is: {maxProbability}")
