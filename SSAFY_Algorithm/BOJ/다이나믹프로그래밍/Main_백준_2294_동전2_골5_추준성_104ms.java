import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_2294_동전2_골5_추준성_104ms {
	/*
	 * dp[i] : i원을 만들 수 있는 동전의 최소 개수
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] coins = new int[N];
		int[] dp = new int[K+1];
		
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(dp, 100001);
		dp[0] = 0; // 점화식 구조를 위해 초기화
		
		for (int i = 0; i < N; i++) {
			for (int j = coins[i]; j <= K; j++) {
				dp[j] = Math.min(dp[j-coins[i]] + 1, dp[j]);
			}
		}
		
		System.out.print(dp[K] != 100001 ? dp[K] : -1);
		
	} // end of main

} // end of class
