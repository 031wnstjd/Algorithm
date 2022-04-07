import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_SWEA_1249_보급로_D4_추준성_159ms {
	/*
	 * <조건 분석>
	 * 1. 이동하는 시간에 비해 복구하는데 필요한 시간은 매우 크다고 가정
	 * => 거리 고려 X => 오로지 복구 시간을 최소로하여 도착지점에 도착하는 것만 생각
	 * 2. S(0,0)에서 G(N-1,N-1)까지 가야 함
	 * 3. 구하고자 하는 것 : 복구 작업에 드는 시간이 가장 작은 경로의 복구 시간을 출력
	 * 
	 * <설계>
	 * - 현재 가장 작은 복구시간을 다음 경로로 선택해도 그 다음이 최적이라는 보장 X
	 * => 완탐 
	 * => dp[][] (메모이제이션) (그냥 완탐으로는 시간 터짐)
	 * => 복구 시간을 누적하면서 4방 탐색을 하되, 대소 비교하면서 최솟값으로 갱신  (단, 기존 map[][]과 dp[][]은 따로 둬야 함)
	 * => dp[][] 배열 값은 충분히 큰 값으로 초기화
	 * => dp[r+dr[i]][c+dc[i]] = Math.min(dp[r][c] + map[r+dr[i]][c+dc[i]], dp[r+dr[i]][c+dc[i]]);
	 * => dp[N-1][N-1] 출력
	 * 
	 */
	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
					dp[i][j] = 1000000; // 복구 시간 최대값 설정
				}
			}
			
			dp[0][0] = 0;
			
			// 4방 탐색
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {0, 0});
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				
				int r = cur[0];
				int c = cur[1];
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if(dp[r][c] + map[nr][nc] < dp[nr][nc]) { // 최솟값 갱신 & 방문처리 효과
						dp[nr][nc] = dp[r][c] + map[nr][nc];
						q.add(new int[] {nr, nc});
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(dp[N-1][N-1]).append("\n");
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main

} // end of class
