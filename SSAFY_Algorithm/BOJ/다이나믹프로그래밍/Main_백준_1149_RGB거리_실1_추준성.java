import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1149_RGB거리_실1_추준성 {
	/*
	 * - 상향식
	 * - 조건 1 : 연속되는 집의 색깔이 서로 달라야함
	 * - 조건 2 : 모든 집을 칠하는 비용이 최소가 되어야 함
	 * 
	 * - dp[N+1][3] : n번째 집까지 색칠하는 최소 비용
	 * 
	 * => 앞에서 어떤 색을 선택했는지에 따라 최소 비용이 계속 달라질 수 있음 (모든 경우를 다 따져야함)
	 * => 완전 탐색으로는 시간이 터지므로, 상향식으로 순차적으로 최적의 해를 찾아나가면서 dp[][]에 담음
	 *
	 *	dp[n][0] = Math.min(dp[n-1][1], dp[n-1][2]) + cost[n][0]
	 *	dp[n][1] = Math.min(dp[n-1][0], dp[n-1][2]) + cost[n][1]
	 *	dp[n][2] = Math.min(dp[n-1][0], dp[n-1][1]) + cost[n][2]
	 *
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] cost = new int[N+1][3];
		int[][] dp = new int[N+1][3];
		
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 0;
		dp[0][1] = 0;
		dp[0][2] = 0;
		
		for (int n = 1; n <= N; n++) {
			dp[n][0] = Math.min(dp[n-1][1], dp[n-1][2]) + cost[n][0];
			dp[n][1] = Math.min(dp[n-1][0], dp[n-1][2]) + cost[n][1];
			dp[n][2] = Math.min(dp[n-1][0], dp[n-1][1]) + cost[n][2];
		}
		
		int temp = Math.min(dp[N][0], dp[N][1]);
		System.out.println(Math.min(dp[N][2], temp));
		
	} // end of main

} // end of class
