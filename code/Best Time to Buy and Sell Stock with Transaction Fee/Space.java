import java.util.Scanner;

public class Space {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of days: ");
        int n = scanner.nextInt();

        int[] prices = new int[n];
        System.out.println("Enter the prices for each day:");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        System.out.print("Enter the transaction fee: ");
        int fee = scanner.nextInt();

        int maxProfit = maxProfit(prices, fee);
        System.out.println("Maximum Profit: " + maxProfit);
    }

    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int free = 0;
        int hold = -prices[0];

        for (int i = 1; i < n; i++) {
            int tmp = hold;
            hold = Math.max(hold, free - prices[i]);
            free = Math.max(free, tmp + prices[i] - fee);
        }

        return free;
    }
}
