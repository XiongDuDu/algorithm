/**
 * 莱温斯坦距离
 *
 * @author duwei7
 * @time 2020/11/4 14:51
 */
public class Lev {
    /**
     * dp[i][j] 代表 a[0...i] -> b[0...j]的距离
     */
    static int[][] dp;

    public static void main(String[] args) {
        String a = "kitten";
        String b = "sitting";
        int lena = a.length();
        int lenb = b.length();
        dp = new int[lena + 1][lenb + 1];

        for (int i = 0; i <= lena; ++i) {
            for (int j = 0; j <= lenb; ++j) {
                if (i == 0) {
                    dp[0][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = i;
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        System.out.println(dp[lena][lenb]);

        System.out.println(new StringBuilder().append(".").append(""));
    }
}
