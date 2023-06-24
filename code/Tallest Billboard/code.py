def helper(rods, left_index, right_index):
    states = set([(0, 0)])
    for i in range(left_index, right_index):
        r = rods[i]
        new_states = set()
        for p in states:
            new_states.add((p[0] + r, p[1]))
            new_states.add((p[0], p[1] + r))
        states.update(new_states)

    dp = {}
    for p in states:
        left, right = p[0], p[1]
        diff = left - right
        dp[diff] = max(dp.get(diff, 0), left)
    return dp


def tallest_billboard(rods):
    n = len(rods)
    first_half = helper(rods, 0, n // 2)
    second_half = helper(rods, n // 2, n)

    answer = 0
    for diff in first_half.keys():
        if -diff in second_half:
            answer = max(answer, first_half[diff] + second_half[-diff])

    return answer


# Interactive input
n = int(input("Enter the number of rods: "))
rods = []
print("Enter the lengths of rods:")
for _ in range(n):
    rod = int(input())
    rods.append(rod)

result = tallest_billboard(rods)
print("Largest possible height:", result)
