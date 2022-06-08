import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_9095_123더하기_실3_추준성_76ms {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < tc; testCase++) {
			int n = Integer.parseInt(br.readLine());
			
			if(n <= 3) {
				if(n == 1) sb.append(1).append("\n");
				if(n == 2) sb.append(2).append("\n");
				if(n == 3) sb.append(4).append("\n");
				continue;
			}
			
			int[] dp = new int[n+1];
			
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for (int i = 4; i <= n; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			sb.append(dp[n]).append("\n");
			
		} // end of for testCase
		
		System.out.print(sb.toString());
	} // end of main

} // end of class
