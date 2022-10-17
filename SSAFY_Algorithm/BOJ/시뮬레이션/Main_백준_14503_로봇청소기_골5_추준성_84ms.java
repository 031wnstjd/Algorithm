import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_14503_로봇청소기_골5_추준성_84ms {

	private static int[][] map;
	private static boolean[][] visited;
	private static int N;
	private static int M;
	
	static class Robot {
		int r, c, d; // 현재 위치, 방향
		
		public Robot(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d; // 0 : 북, 1 : 동, 2 : 남, 3 : 서
		}
	}
	
	static int[] dr = {-1, 0, 1, 0}; // 북동남서
	static int[] dc = { 0, 1, 0,-1};
	public static void main(String[] args)  throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 0 : 빈칸, 1 : 벽
			}
		}
		
		Queue<Robot> queue = new LinkedList<>();
		visited = new boolean[N][M];
		
		queue.add(new Robot(sr, sc, sd));
		visited[sr][sc] = true;
		
here:		while(!queue.isEmpty()) {
			
			Robot robot = queue.poll();
			int r = robot.r;
			int c = robot.c;
			int d = robot.d;
			
			for (int i = 0; i < 4; i++) {
				int nd = turnLeft(d);
				int nr = r + dr[nd];
				int nc = c + dc[nd];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 1) {
					if(i == 3) { // 네 방향이 다 청소됐거나 벽이면
						nr = r - dr[nd];
						nc = c - dc[nd];
						
						if(map[nr][nc] == 1) break here; // 뒤쪽이 벽이면 작동 중지
						
						queue.add(new Robot(nr, nc, nd)); // 뒤쪽이 벽이 아니면 후진
					}
					d = nd;
					continue;
				}
				
				queue.add(new Robot(nr, nc, nd));
				visited[nr][nc] = true;
				break;
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j] == true) cnt++;
			}
		}
		
		System.out.println(cnt);
		
	} // end of main

	private static int turnLeft(int d) {
		
		switch (d) {
			case 0: // 북
				d = 3;
				break;
			case 1: // 동
				d = 0;
				break;
			case 2: // 남
				d = 1;
				break;
			case 3: // 서
				d = 2;
				break;
		}
		
		return d;
	}

	
} // end of class
