import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2240_자두나무_골5_추준성_76ms {
	
	/*
	 * 3가지 상태값 : T(시간), W(이동횟수), idx(현재위치) (현재 위치는 1, 2 두 개이기 때문에 홀짝으로 구분 가능)
	 * => 그냥 서 있는 경우, 옆으로 이동하는 경우
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[][] dp = new int[T+1][W+1];
		
		for (int t = 1; t <= T; t++) {
			int idx = Integer.parseInt(br.readLine());
			for (int w = 0; w <= W; w++) {
				if(w == 0) { // 안 움직이면(가만히 있으면) 1번 나무
					if(idx == 1) dp[t][w] = dp[t-1][w] + 1; // 1번 나무에 있을 대
					// 2번 나무에 있는 경우가 없음
				}
				else if(w % 2 == 0) { // 이동횟수가 짝수면 1번 나무
					if(idx == 1) dp[t][w] = Math.max(dp[t-1][w-1], dp[t-1][w]) + 1; // 1번 나무에 있을 때
					else dp[t][w] = Math.max(dp[t-1][w-1], dp[t-1][w]); // 2번 나무에 있을 때
				} 
				else { // 이동횟수가 홀수면 2번 나무
					if(idx == 2) dp[t][w] = Math.max(dp[t-1][w-1], dp[t-1][w]) + 1; // 2번 나무에 있을 때
					else dp[t][w] = Math.max(dp[t-1][w-1], dp[t-1][w]); // 1번 나무에 있을 때
				}
			}
		}
		
		int max = 0;
		for (int i = 1; i <= T; i++) {
			for (int j = 0; j <= W; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.print(max);
	
	} // end of main

} // end of class
