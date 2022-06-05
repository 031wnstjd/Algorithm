import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1926_그림_실1_추준성_444ms {

	private static int drawCnt;
	private static int maxArea;
	private static int[][] map;
	private static int N;
	private static int M;

	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		drawCnt = 0;
		maxArea = 0;
		
		bfs();
		
		sb.append(drawCnt).append("\n").append(maxArea);
		System.out.print(sb.toString());
	} // end of main

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if(map[i][j] == 0 || visited[i][j]) continue; // 값이 0이거나 이미 그린 그림에 포함되면 continue
				int tmpArea = 0;
				
				queue.add(new int[] {i, j});
				visited[i][j] = true;
				tmpArea++; // 영역 넓이 카운트
				drawCnt++; // 그림 개수 카운트

				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					
					for (int k = 0; k < 4; k++) {
						int nr = cur[0] + dr[k];
						int nc = cur[1] + dc[k];
					
						if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 0 || visited[nr][nc]) continue;
						
						queue.add(new int[] {nr, nc});
						visited[nr][nc] = true;
						tmpArea++;
					}
					
				}
				maxArea = Math.max(maxArea, tmpArea); // 최대 넓이 갱신
			}
		}
	}

} // end of class
