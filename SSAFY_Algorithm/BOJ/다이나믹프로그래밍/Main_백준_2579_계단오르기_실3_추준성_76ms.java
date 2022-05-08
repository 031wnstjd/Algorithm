import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_2579_계단오르기_실3_추준성_76ms {
	/*
	 * dp[i] : i번째 계단까지 올랐을 때 얻을 수 있는 총 점수의 최댓값
	 * => "i번째" 상태를 정의하는 게 dp의 핵심
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1 <= N <= 300
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if(N >= 1) dp[0] = arr[0];
		if(N >= 2) dp[1] = arr[0]+arr[1];
		if(N >= 3) dp[2] = Math.max(arr[0], arr[1]) + arr[2];
		
		for (int i = 3; i < N; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3]+arr[i-1]) + arr[i]; // Math.max(dp[i-2], dp[i-3]+arr[i-1]) : 연속해서 3번 오르는 경우를 배제해야 하므로 dp[i-3]+arr[i-1]로 써야 함
		}
		
		System.out.print(dp[N-1]);
		
	} // end of main

} // end of class
