package data.structures.algorithms.hard.leetCode;

public class N44WildCardMatching {
    public static void main(String[] args) {
        String s = "aa", p = "*";
        N44WildCardMatching sol = new N44WildCardMatching();
        System.out.println("Is Matching ? " + sol.isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char cs = s.charAt(i - 1);
                char cp = p.charAt(j - 1);

                if (cp == cs || cp == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}
