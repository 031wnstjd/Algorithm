import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2225_합분해_골5_추준성_72ms {
	
	/*
	 * N = 6, K = 4
	 * 6 = (K-1번 더해서 6이 되는 수) + 0
	 * 6 = (K-1번 더해서 5가 되는 수) + 1
	 * . . .
	 * 6 = (K-1번 더해서 0이 되는 수) + 6
	 * 
	 * => i-1개의 수의 합이 j-k인 값에 k를 더하면 i개를 더해 합이 j인 경우를 찾을 수 있음
	 */
	
	/*
	 * dp[i][j] : i개의 수를 더해 j를 만드는 경우의 수
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 200
		int K = Integer.parseInt(st.nextToken()); // 1 <= K <= 200
		
		int[][] dp = new int[K+1][N+1];
		
		for (int i = 1; i <= K; i++) {
			dp[i][0] = 1; // i개의 수를 더해 0을 만드는 경우의 수는 항상 1
		}
		
		for (int j = 0; j <= N; j++) {
			dp[1][j] = 1; // 1개의 수를 더해 j를 만드는 경우의 수는 항상 1
		}
		
		
		for (int i = 2; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
				dp[i][j] %= 1000000000;
			}
		}
		
		System.out.println(dp[K][N]);
		
		
	} // end of main

} // end of class
