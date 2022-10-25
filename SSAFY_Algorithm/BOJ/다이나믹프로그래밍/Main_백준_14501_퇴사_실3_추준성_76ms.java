import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14501_퇴사_실3_추준성_76ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] counsels = new int[N][2];
		int[] dp = new int[N+1]; // dp[n] : n일차 최대 수익
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			counsels[i][0] = Integer.parseInt(st.nextToken()); // 기간
			counsels[i][1] = Integer.parseInt(st.nextToken()); // 수익
		}
		
		for (int i = 0; i < N; i++) {
			int startIdx = i;
			int endIdx = i + counsels[i][0];
			
			if(endIdx <= N) dp[endIdx] = Math.max(dp[endIdx], dp[startIdx] + counsels[startIdx][1]);
			
			dp[startIdx+1] = Math.max(dp[startIdx+1], dp[i]); // 현재 날짜의 값이 업데이트 되지 않아 0일 수도 있으므로, 이전 최댓값을 고려해서 값 결정
		}
		
		System.out.println(dp[N]);
		
	} // end of main

} // end of class
