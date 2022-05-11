import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2096_내려가기_골4_추준성_380ms {
	private static int[][] board;
	private static int N;
	/*
	 * - 최대점수, 최소점수를 구하기 위한 dp를 두 번 실행
	 * - dp[i][j] : (i, j)에서의 최댓값 또는 최솟값
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][3];
		int[][] dp = new int[N+1][3];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sb.append(getMax(dp)).append(" ").append(getMin(dp));
		System.out.print(sb.toString());
	} // end of main

	private static int getMax(int[][] dp) {
		int max = 0;
		
		// 1. dp 테이블 만들기
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				if(j==0) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j+1]) + board[i][j];
				if(j==1) dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i-1][j+1], dp[i-1][j-1])) + board[i][j];
				if(j==2) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + board[i][j];
			}
		}
		
		// 2. 최댓값 찾기
		for (int j = 0; j < 3; j++) {
			max = Math.max(max, dp[N][j]);
		}
		
		return max;
	}
	private static int getMin(int[][] dp) {
		int min = Integer.MAX_VALUE;
		
		// 1. dp 테이블 만들기
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				if(j==0) dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j+1]) + board[i][j];
				if(j==1) dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j+1], dp[i-1][j-1])) + board[i][j];
				if(j==2) dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + board[i][j];
			}
		}
		
		// 2. 최솟값 찾기
		for (int j = 0; j < 3; j++) {
			min = Math.min(min, dp[N][j]);
		}
		
		return min;
	}


} // end of class
