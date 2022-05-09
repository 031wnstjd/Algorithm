import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2293_동전1_골5_추준성_88ms {
	/*
	 * dp[i] : i원을 만드는 데 가능한 경우의 수
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		int[] dp = new int[K+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		
		for (int i = 1; i <= N; i++) {
			for (int j = arr[i]; j <= K; j++) {
				dp[j] += dp[j-arr[i]];
			}
		}
		
		System.out.print(dp[K]);
		
	} // end of main

} // end of class
