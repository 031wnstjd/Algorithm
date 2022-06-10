import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_7569_토마토_골5_추준성_644ms {
	/*
	 *  1 : 익은토마토
	 *  0 : 익지 않은 토마토
	 * -1 : 토마토가 들어있지 않은 칸
	 * 
	 *  구하고자 하는 것 : 토마토가 모두 익을 때까지 최소 며칠이 걸리는지
	 *  (만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력)
	 *  
	 *  <설계>
	 *  - M*N*H => 3개의 상태? => 3차원 배열로 상태 표현
	 *  - BFS => 방문처리 배열을 따로 두지 않고, 값이 0인 지점만 탐색, 방문했으면 1로 바꿈
	 *  	  => 위, 아래도 탐색 범위로 고려
	 *  
	 */

	private static int[][][] map;
	private static int H;
	private static int N;
	private static int M;
	private static LinkedList<int[]> queue;
	
	static int[] dr = {0, 0, 1,-1, 0, 0};
	static int[] dc = {1,-1, 0, 0, 0, 0};
	static int[] dh = {0, 0, 0, 0, 1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로, 2 <= M <= 100
		N = Integer.parseInt(st.nextToken()); // 세로, 2 <= N <= 100
		H = Integer.parseInt(st.nextToken()); // 높이, 1 <= H <= 100
		
		map = new int[N][M][H];
		
		queue = new LinkedList<>();
		
		boolean isAllRipe = true;
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					// 익은 토마토이면 queue에 해당 좌표 넣기
					if(map[i][j][k] == 1) queue.add(new int[] {i, j, k});
					// 익지 않은 토마토가 있으면 isAllRipe를 false로
					if(map[i][j][k] == 0) isAllRipe = false;
				}
			}
		}
		
		// 모두 익었으면 0을 출력하고 종료
		if(isAllRipe) {
			System.out.print(0);
			System.exit(0);
		}
		
		System.out.print(bfs());
		
	} // end of main

	private static int bfs() {
		int days = -1;
		while(!queue.isEmpty()) {
			int qSize = queue.size();
			while(qSize-- > 0) {
				int[] cur = queue.poll();
				int r = cur[0];
				int c = cur[1];
				int h = cur[2];
				
				for (int i = 0; i < 6; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					int nh = h + dh[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || nh < 0 || nh >= H || map[nr][nc][nh] != 0) continue;
					
					map[nr][nc][nh] = 1; // 방문처리 (익음)
					queue.add(new int[] {nr, nc, nh});				
				}
			}
			days++;
		}
		
		// 다 익었는지 검사
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j][k] == 0) return -1; // 익지 않은 토마토가 존재하면 -1을 리턴
				}
			}
		}
		
		return days;
	}

} // end of class
