import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_2468_안전영역_실1_추준성_308ms {
	private static boolean[][] visited;
	private static int[][] map;
	private static int N;
	private static int cnt;

	/*
	 * <조건>
	 * 1. 내리는 비의 양에 따른 '모든' 경우 조사 => 최대 높이만큼 따져서 조사 => O(N^3)
	 * 
	 * <설계>
	 * 1. 최대 높이만큼 반복해서 조사
	 * 2. 현재 높이보다 작거나 같은 위치의 visited 값을 true로 만듦
	 * 3. 방문을 안 한 곳을 기준으로 BFS 탐색
	 * 4. visited 배열 초기화
	 */
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		int maxCnt = 0;
		for (int h = 0; h <= maxHeight; h++) { // 아무지역도 물에 잠기지 않을 수도 있으므로 h == 0일 때 포함
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] <= h) visited[i][j] = true; // 물에 잠김
				}
			}
			
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) continue;
					bfs(i, j); // 물에 잠기지 않았거나 방문 안했으면 영역 탐색
					cnt++; // 안전영역수 증가
				}
			}
			
			// visited 배열 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			
			maxCnt = Math.max(maxCnt, cnt); // maxCnt 갱신
			
		} // end of h
		
		System.out.print(maxCnt);
		
	} // end of main

	private static void bfs(int a, int b) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});		
		
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				queue.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}

} // end of class
