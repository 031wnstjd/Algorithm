import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_14503_로봇청소기_골5_추준성_88ms {
	/*
	 * <조건 분석>
	 * 1. 왼쪽 방향으로 계속 회전하면서 다음 청소 구역 여부 탐색
	 * 2. 1번이 연속으로 네 번 실행되었을 경우(탐색마다 회전 수 카운트해야함), 뒤쪽이 벽이면 작동 멈춤
	 * 3. 뒤쪽이 벽이 아니면 후진 
	 * 4. 로봇 청소기가 청소하는 칸의 개수 출력
	 * 
	 * d == 0 : 북
	 * d == 1 : 동
	 * d == 2 : 남
	 * d == 3 : 서
	 * 
	 */
	static class Robot {
		int r;
		int c;
		int d;
		
		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
	}
	
	private static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
	private static int[] dc = { 0, 1, 0,-1};
	private static int[][] map;
	private static int N;
	private static int M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int initR = Integer.parseInt(st.nextToken()); // 현재 로봇 r 좌표
		int initC = Integer.parseInt(st.nextToken()); // 현재 로봇 c 좌표
		int initD = Integer.parseInt(st.nextToken()); // 현재 로봇 방향
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// bfs 탐색
		bfs(initR, initC, initD);
		
		// 2의 개수(청소 구역 개수) 출력
		print();
		
	} // end of main
	
	static void bfs(int initR, int initC, int initD) {
		Queue<Robot> q = new LinkedList<>();
		q.add(new Robot(initR, initC, initD));
		map[initR][initC] = 2; // 청소하면 2
		
		while(!q.isEmpty()) {
			
			Robot cur = q.poll();
			
			int r = cur.r;
			int c = cur.c;
			int curDir = cur.d;
			
			int changeDirCnt = 0;
			// 방향 전환 최대 4번
			while(changeDirCnt < 4) {
				int leftDir = setLeftDir(curDir);
				
				int nr = r + dr[leftDir];
				int nc = c + dc[leftDir];
				
				// 현재 위치의 왼쪽이 비어 있고, 청소하지 않은 구역이면 큐에 넣고 while문 탈출
				if(map[nr][nc] == 0) {
					q.add(new Robot(nr, nc, leftDir));
					map[nr][nc] = 2;
					break;
				// 그렇지 않다면 방향만 전환
				} else {
					curDir = leftDir;
				}
				changeDirCnt++;
			}
			
			// 방향 전환을 네 번 했다면 작동 중지 or 후진 실행
			if(changeDirCnt == 4) {
				int nr = r - dr[curDir];
				int nc = c - dc[curDir];
				if(map[nr][nc] == 1) break; // 뒤쪽이 벽이면 작동을 멈춤
				q.add(new Robot(nr, nc, curDir)); // 벽이 아니면 후진
			} 
		}
	} // end of main
	
	static int setLeftDir(int dir) {
		switch(dir) {
			case 0:
				dir = 3;
				break;
			case 1:
				dir = 0;
				break;
			case 2:
				dir = 1;
				break;
			case 3:
				dir = 2;
				break;
		}
		return dir;
	} 
	
	static void print() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 2) cnt++;
			}
		}
		
		System.out.print(cnt);
	} 
	
} // end of class
