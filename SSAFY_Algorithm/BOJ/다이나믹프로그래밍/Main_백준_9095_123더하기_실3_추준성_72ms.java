import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_9095_123더하기_실3_추준성_72ms {
	/*
	 * 최적 부분 구조 & 하위 문제 중복 => DP
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		int[] dp = new int[11];
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for (int i = 4; i < 11; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		for (int testCase = 0; testCase < tc; testCase++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		} // end of for testCase
		
		System.out.print(sb.toString());
	} // end of main

} // end of class
