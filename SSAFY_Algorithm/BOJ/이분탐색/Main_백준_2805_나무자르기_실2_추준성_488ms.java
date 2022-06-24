import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2805_나무자르기_실2_추준성_488ms {
	private static int ans;
	private static int N;
	private static int[] trees;
	private static int M;

	/*
	 * <설계>
	 * 1. 1 ≤ N ≤ 1,000,000 => O(N^2) 미만이어야함 
	 * 2. "이분탐색"을 통해 적어도  M미터의 나무를 가져가기 위한 절단기 설정 높이의 최댓값 H 찾기
	 * => biSect(int bottom, int top) // top : 맨 위, bottom : 맨 아래 
	 * 3. sum 변수 long형 선언
	 * 
	 * - 구하고자 하는 것 : "적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값"
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 1,000,000
		M = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 2,000,000,000
		
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxHeight = Math.max(trees[i], maxHeight);
		}
		
		ans = 0;
		biSect(0, maxHeight >> 1, maxHeight);
		
		System.out.println(ans);
		
	} // end of main

	private static void biSect(int bottom, int mid, int top) {
		// 기저 조건 (bottom이 top과 크거나 같아질 때)
		if(bottom >= top) { 
			ans = bottom - 1;
			return;
		}
		
		long sum = 0;
		for (int i = 0; i < N; i++) {
			int tmp = trees[i] - mid;
			if(tmp > 0) sum += tmp; // mid 위치에서 자를 나무가 있다면, 자른 나무의 길이를 누적
		}
		
		if(sum < M) biSect(bottom, (bottom+mid) >> 1, mid); // 목표한 양보다 적으면 자를 위치를 더 내림
		else biSect(mid + 1, (mid+1+top) >> 1, top); // 목표한 양보다 크면 자를 위치를 더 내림
	}


} // end of class
