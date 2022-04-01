import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_4485_녹색옷은입은애가젤다지_골4_추준성_252ms {
	/*
	 * 각 지점을 bfs로 탐색할 때, 동일한 너비 탐색일 경우 각 좌표를 방문했을 때
	 * 잃게 되는 최소 루피 값을 각 좌표의 값에 저장 (갱신)
	 * 
	 * if(dp[r][c] + map[nr][nc] < dp[nr][nc]){
	 * 
	 * }
	 * 
	 */
	
	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = 1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			int[][] map = new int[N][N];
			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			dp[0][0] = map[0][0];
			
			LinkedList<int[]> queue = new LinkedList<>();
			queue.add(new int[] {0, 0});
			
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				int r = cur[0];
				int c = cur[1];
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || dp[r][c] + map[nr][nc] >= dp[nr][nc]) continue;
					
					dp[nr][nc] = dp[r][c] + map[nr][nc];
					queue.add(new int[] {nr, nc});
				}
			}
			
			sb.append("Problem").append(" ").append(testCase).append(":").append(" ").append(dp[N-1][N-1]).append("\n");
			
			testCase++;
		} // end of while testCase
		
		System.out.print(sb.toString());
		
	} // end of main

} // end of class
