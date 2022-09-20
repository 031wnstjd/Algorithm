import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_9451_파도반수열_실3_추준성_76ms {
	
	/*
	 * - 여섯 번 주기로 나선형 반복
	 * - dp[i] = dp[i-1] + dp[i-5]
	 * - long형 사용해야 overflow 안 남
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 1 <= N <= 100
			long[] dp = new long[N];
			
			if(N >= 1) dp[0] = 1;
			if(N >= 2) dp[1] = 1;
			if(N >= 3) dp[2] = 1;
			if(N >= 4) dp[3] = 2;
			if(N >= 5) dp[4] = 2;
			
			for (int i = 5; i < N; i++) {
				dp[i] = dp[i-1] + dp[i-5];
			}
			
			sb.append(dp[N-1]).append("\n");
			
		} // end of for testCase
		
		System.out.print(sb.toString());
		
	} // end of main

} // end of class
