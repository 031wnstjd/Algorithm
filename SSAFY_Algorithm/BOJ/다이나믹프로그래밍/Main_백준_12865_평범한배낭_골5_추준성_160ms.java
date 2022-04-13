import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_12865_평범한배낭_골5_추준성_160ms {
	/*
	 * <설계>
	 * 1. N개의 물건, 무게 W, 가치 V
	 * 2. 구하고자 하는 것 : 배낭에 넣을 수 있는 물건들의 가치의 최댓값 (넣거나, 안넣거나 -> 0/1 knapsack)
	 * 3. DP
	 * => 물건을 하나씩 더 고려하면서 각 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 갱신 
	 * => dp[i][j] : i번째 물건까지 고려했을 때, 가방무게가 j일 때 물건들의 가치합의 최댓값
	 * => dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W] + V);
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][K+1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j <= K; j++) {
				if(j-W < 0) {
					dp[i][j] = dp[i-1][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W] + V);
			}
		}
		
		System.out.print(dp[N][K]);
		
	} // end of main

} // end of class
