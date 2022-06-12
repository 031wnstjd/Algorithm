import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1912_연속합_실2_추준성_200ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dp = new int[N+1]; // dp[i] = Math.max(dp[i-1] + num, num) (num : i번째 원소의 값) 
		int max = -1000;
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(dp[i-1]+num, num);
			max = Math.max(max, dp[i]); // max값 갱신
		}
		System.out.print(max);
	} // end of main

} // end of class
