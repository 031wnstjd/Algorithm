package study.day17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1865_웜홀_골3_추준성_1860ms {
	/*
	 * 도로 : 이동시 시간 늘어남(+ : 가중치 양수), 방향 X
	 * 웜홀 : 이동시 시간 줄어듦(- : 가중치 음수), 방향 O
	 * 
	 * 구하고자 하는 것 : 시간이 줄어들면서 출발 위치로 돌아오는 것이 가능하면 YES, 불가능하면 NO
	 * => 모든 지점에 대해서 따져봐야 함 => 플로이드와샬 
	 * => i==j(자기 자신으로 돌아오는 경우)가 되는 지점들 중 최대한 시간이 많이 줄어드는 경우(최솟값)를 선택
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 정점개수, 1 <= N <= 500
			int M = Integer.parseInt(st.nextToken()); // 도로개수, 1 <= M <= 2500
			int W = Integer.parseInt(st.nextToken()); // 웜홀개수, 1 <= W <= 200
			
			int[][] adjMatrix = new int[N+1][N+1];
			
			for (int i = 1; i <= N; i++) {
				Arrays.fill(adjMatrix[i], 25000000); // 최댓값으로 초기화 (2500 * 10000)
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()); // 시작 정점
				int e = Integer.parseInt(st.nextToken()); // 도착 정점
				int t = Integer.parseInt(st.nextToken()); // 걸린 시간, 0 <= t <= 10,000
				if(adjMatrix[s][e] < t) continue; // 경로 두 개 이상 존재할 수 있으므로, 작은 경우만 담음
				adjMatrix[s][e] = adjMatrix[e][s] = t; // 방향성 x
			}
			
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()); // 시작 정점
				int e = Integer.parseInt(st.nextToken()); // 도착 정점
				int t = Integer.parseInt(st.nextToken()); // 걸린 시간
				adjMatrix[s][e] = -t; // 방향성 O
			}
			
			boolean isValid = false; // 시간이 줄어드는 경로 체크용
here:		for (int k = 1; k <= N; k++) { // 경유지
				for (int i = 1; i <= N; i++) {
					if(k == i) continue;
					for (int j = 1; j <= N; j++) {
						if(j == k) continue;
						if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
							adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
						}
						if(i==j && adjMatrix[i][j] < 0) { // 자기 자신으로 돌아오는 경로가 음수면 탈출 (가지치기)
							isValid = true;
							break here;
						}
					}
				}
			}
			
			sb.append(isValid ? "YES" : "NO").append("\n");
			
		} // end of for testCase
		
		System.out.print(sb.toString());
		
	} // end of main

} // end of class
