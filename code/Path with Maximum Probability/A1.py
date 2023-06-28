from typing import List

class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start: int, end: int) -> float:
        max_prob = [0] * n
        max_prob[start] = 1

        for i in range(n - 1):
            # If there is no larger probability found during an entire round of updates,
            # stop the update process.
            has_update = False
            for j in range(len(edges)):
                u, v = edges[j]
                path_prob = succProb[j]
                if max_prob[u] * path_prob > max_prob[v]:
                    max_prob[v] = max_prob[u] * path_prob
                    has_update = True
                if max_prob[v] * path_prob > max_prob[u]:
                    max_prob[u] = max_prob[v] * path_prob
                    has_update = True
            if not has_update:
                break

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
