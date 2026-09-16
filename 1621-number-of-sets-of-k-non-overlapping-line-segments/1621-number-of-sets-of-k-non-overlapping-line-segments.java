class Solution {
    public int numberOfSets(int n, int k) {
        long ans = 1;
        long MOD = 1000000007;

        // Answer = C(n + k - 1, 2k)
        int N = n + k - 1;
        int R = 2 * k;

        for (int i = 1; i <= R; i++) {
            ans = ans * (N - R + i) % MOD;

            // Modular inverse using Fermat's theorem
            ans = ans * modInverse(i, MOD) % MOD;
        }

        return (int) ans;
    }

    private long modInverse(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long a, long b, long mod) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return result;
    }
}