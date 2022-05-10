import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1932_정수삼각형_실1_추준성_240ms {
	/*
	 * - 왼쪽 대각선 인덱스 : 현재 인덱스  + (현재 있는 층의 길이(인덱스))
	 * - 오른쪽 대각선 인덱스 : 현재 인덱스  + (현재 있는 층의 길이(인덱스)) + 1
	 * 
	 * dp[i] : i번째 인덱스의 최댓값
	 * 
	 * dp[i+layer] = Math.max(dp[i+layer], dp[i]+arr[i+layer])
	 * dp[i+layer+1] = Math.max(dp[i+layer+1], dp[i]+arr[i+layer+1])
	 * 
	 */
	
	static class Node {
		int num;
		int layer;
		public Node(int num, int layer) {
			super();
			this.num = num;
			this.layer = layer; // 층수 저장
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = N*(N+1)/2;
		Node[] arr = new Node[M+1];
		int[] dp = new int[M+1];
		
		int idx = 1;
		for (int i = 1; i <= N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for (int j = 1; j <= i; j++) {
				 arr[idx++] = new Node(Integer.parseInt(st.nextToken()), i);
			 }
		}
		
		dp[1] = arr[1].num; // 초기값
		
		
		// 1. dp 테이블 만들기
		for (int i = 1; i <= M; i++) {
			int layer = arr[i].layer;
			if(i+layer <= M) dp[i+layer] = Math.max(dp[i+layer], dp[i]+arr[i+layer].num);
			if(i+layer+1 <= M) dp[i+layer+1] = Math.max(dp[i+layer+1], dp[i]+arr[i+layer+1].num);
		}
		
		// 2. 마지막 layer에서 최댓값 찾기
		int max = 0;
		for (int i = M; i > M-N; i--) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.print(max);
	} // end of main

} // end of class
