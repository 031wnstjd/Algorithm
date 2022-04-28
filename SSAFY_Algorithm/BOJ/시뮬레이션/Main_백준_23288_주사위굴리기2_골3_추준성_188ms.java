import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_23288_주사위굴리기2_골3_추준성_188ms {
	private static int N;
	private static int M;
	private static int[][] map;

	static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌 (시계방향)
	static int[] dc = { 0, 1, 0,-1};
	private static int[] state;
	private static int r;
	private static int c;
	private static int d;
	private static int score;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로크기
		M = Integer.parseInt(st.nextToken()); // 가로크기
		int K = Integer.parseInt(st.nextToken()); // 이동횟수
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		score = 0;
		state = new int[] {1, 2, 3, 4, 5, 6}; // top 상 우 좌 하 bottom
		r = 0;
		c = 0;
		d = 1; // 동쪽
		for (int i = 0; i < K; i++) { // K번 이동
			moveDice();
			getScore();
			setMoveDir();
		}
		System.out.print(score);
	} // end of main

	private static void moveDice() {
		int nr = r + dr[d];
		int nc = c + dc[d];
		
		if(nr < 0 || nr >= N || nc < 0 || nc >= M) { // 이동방향에 칸이 없다면 이동방향 반대로
			switch(d) {
				case 0: // 상 
					d = 2;
					break;
				case 1: // 우
					d = 3;
					break;
				case 2: // 하
					d = 0;
					break;
				case 3: // 좌
					d = 1;
					break;
			}
		}
		
		r += dr[d]; // 위치 업데이트
		c += dc[d];

		switch(d) {
			case 0: // 상
				state = new int[] {state[4], state[0], state[2], state[3], state[5], state[1]};
				break;
			case 1: // 우
				state = new int[] {state[3], state[1], state[0], state[5], state[4], state[2]};
				break;
			case 2: // 하
				state = new int[] {state[1], state[5], state[2], state[3], state[0], state[4]};
				break;
			case 3: // 좌
				state = new int[] {state[2], state[1], state[5], state[0], state[4], state[3]};
				break;
		}
	}
	private static void getScore() {
		int ref = map[r][c];
		
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			cnt++;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] != ref) continue;
				
				queue.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		} // end of bfs
		
		score += ref * cnt; 
	}
	private static void setMoveDir() {
		int a = state[5];
		int b = map[r][c];
		
		if(a > b) d = (d+1)%4;
		if(a < b) d = (d+3)%4;
	}

} // end of class
