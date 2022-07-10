import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1012_유기농배추_실2_추준성_128ms {
	/*
	 * <설계>
	 * - 영역의 개수 == 최소의 배추흰지렁이 마리 수
	 */
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	private static boolean[][] visited;
	private static int M;
	private static int N;
	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로길이
			N = Integer.parseInt(st.nextToken()); // 세로길이
			int K = Integer.parseInt(st.nextToken()); // 배추가 심어져 있는 위치 개수
			
			map = new int[N][M];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = 1; // 배추가 심어져 있음
			}
			
			int cnt = 0;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0 || visited[i][j] == true) continue;
					bfs(i ,j);
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.print(sb.toString());
		
	} // end of main

	private static void bfs(int sr, int sc) {
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;
				
				visited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
			}
		}
	} // end of method bfs()

} // end of class
