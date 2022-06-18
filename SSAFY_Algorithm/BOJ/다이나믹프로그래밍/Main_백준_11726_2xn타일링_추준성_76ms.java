import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_11726_2xn타일링_추준성_76ms {
	/*
	 * bottom-up 방식으로 규칙 찾기 => dp[i] = dp[i-1] + dp[i-2]
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long[] dp = new long[n+1];
		
		if(n <= 2) {
			System.out.print(n);
			return;
		}

		dp[1] = 1;
		dp[2] = 2;
		
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
			dp[i] %= 10007; // 정수형 범위를 넘어가지 않게 10007로 나눈 나머지를 할당
		}
		
		System.out.print(dp[n]);
	} // end of main

} // end of class
