import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_2636_치즈_골4_추준성_108ms {
	/*
	 * 1. (0,0)부터 BFS탐색 진행 -> 값이 0이고 방문을 안한 곳만 방문
	 * 2. 값이 1인 곳을 만나면 값을 0으로 바꾸고 방문처리
	 * 3. 방문처리 초기화
	 * 4. 1,2,3 반복
	 */
	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1,-1};
	private static int[][] map;
	private static boolean[][] visited;
	private static boolean isVoid;
	private static int lastCount;
	private static int N;
	private static int M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map	= new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isVoid = false; // bfs 탐색을 통해 치즈를 만나지 못하면 true
		lastCount = 0; // 매시간 치즈 개수 갱신 값(count가 0이면 갱신 X - 마지막 치즈 개수를 알 수 있음)
		int time = 0; // 시간
		
		while(!isVoid) {
			
			bfs(0, 0);
			
			// visited 배열 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			
			time++;
		}
		
		System.out.println(time-1); // 치즈가 없는 상태에서의 탐색시간인 1을 빼줌
		System.out.println(lastCount);
		
	} // end of main

	public static void bfs(int startR, int startC) {
		
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] {startR, startC});
		visited[startR][startC] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
				
				if(map[nr][nc] == 0) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
				if(map[nr][nc] == 1) {
					map[nr][nc] = 0;
					visited[nr][nc] = true;
					count++;
				}
			}
		}
		
		if(count == 0) isVoid = true;
		else lastCount = count;
	}
} // end of class
