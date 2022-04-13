import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_1로만들기_1463_실3_추준성_104ms {
	/*
	 * - 상향식
	 * - 1부터 시작해서 X까지 각 숫자로부터 1을 만들기 위해 필요한 연산의 최소 횟수를 dp[]에 저장
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		int[] dp = new int[X+1]; // dp : index에 해당하는 숫자로부터 1을 만들기 위해 필요한 연산의 최소 횟수(최적해)
		dp[1] = 0; // 1을 만들기 위해 필요한 연산의 횟수는 0
		
		for (int i = 2; i <= X; i++) {
			int min = Integer.MAX_VALUE;
			if(i%3 == 0 && dp[i/3] + 1 < min) min = dp[i/3] + 1;
			if(i%2 == 0 && dp[i/2] + 1 < min) min = dp[i/2] + 1;
			if(dp[i-1] + 1 < min) min = dp[i-1] + 1;
			dp[i] = min;
		}
		
		System.out.println(dp[X]);
				
	} // end of main

} // end of class
