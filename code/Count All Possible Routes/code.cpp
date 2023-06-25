#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

class Solution {
public:
    int solve(vector<int>& locations, int currCity, int finish, int remainingFuel,
              vector<vector<int>>& memo) {
        if (remainingFuel < 0) {
            return 0;
        }
        if (memo[currCity][remainingFuel] != -1) {
            return memo[currCity][remainingFuel];
        }

        int ans = currCity == finish ? 1 : 0;
        for (int nextCity = 0; nextCity < locations.size(); nextCity++) {
            if (nextCity != currCity) {
                ans = (ans + solve(locations, nextCity, finish,
                                   remainingFuel - abs(locations[currCity] - locations[nextCity]),
                                   memo)) % 1000000007;
            }
        }

        return memo[currCity][remainingFuel] = ans;
    }

    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        int n = locations.size();
        vector<vector<int>> memo(n, vector<int>(fuel + 1, -1));

        return solve(locations, start, finish, fuel, memo);
    }
};

int main() {
    int n;
    cout << "Enter the number of cities: ";
    cin >> n;

    vector<int> locations(n);
    cout << "Enter the locations of cities:" << endl;
    for (int i = 0; i < n; i++) {
        cin >> locations[i];
    }

    int start, finish, fuel;
    cout << "Enter the start city: ";
    cin >> start;

    cout << "Enter the finish city: ";
    cin >> finish;

    cout << "Enter the available fuel: ";
    cin >> fuel;

    Solution solution;
    int result = solution.countRoutes(locations, start, finish, fuel);
    cout << "Number of routes from city " << start << " to city " << finish << " with " << fuel << " fuel: " << result << endl;

    return 0;
}
