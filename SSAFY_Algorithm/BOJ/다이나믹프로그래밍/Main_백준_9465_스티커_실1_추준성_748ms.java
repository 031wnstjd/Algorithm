import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_9465_스티커_실1_추준성_748ms {
	/*
	 * dp[i][j] : (i, j)를 택했을 때 얻을 수 있는 최댓값
	 * => j=2부터 시작해서 하나씩 정해가며 순차적으로 갱신
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 1 <= N <= 100,000
			int[][] arr = new int[2][N+1];
			int[][] dp = new int[2][N+1];
			StringTokenizer st;
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][1] = arr[0][1];
			dp[1][1] = arr[1][1];
			
			for (int i = 2; i <= N; i++) {
				dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + arr[0][i];
				dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + arr[1][i];
			}
			
			sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
			
		} // end of for testCase
		
		System.out.print(sb.toString());
		
	} // end of main

} // end of class
