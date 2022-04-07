import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_1953_탈주범검거_추준성_137ms {

	/*
	 * <조건 분석>
	 * - 탈주범이 있는 위치의 터널 구조물 타입에 따라 이동 가능 방향이 정해짐
	 * type == 1 : 상하좌우
	 * type == 2 : 상하
	 * type == 3 : 좌우
	 * type == 4 : 상우
	 * type == 5 : 하우
	 * type == 6 : 하좌
	 * type == 7 : 상좌
	 * 
	 * <설계>
	 * 1. 맨홀 뚜껑 위치부터 BFS 탐색 시작 (type에 따라 이동 가능 방향 정하기)
	 * 2. 경과한 시간 만큼만 BFS 돌림 (경과한 시간 지나면 while문 탈출)
	 * 3. queue에서 꺼낼 때마다 장소의 개수 누적해서 더함
	 * 
	 */
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = { 0, 0,-1, 1}; // 상 하 좌 우
	private static int[][] map;
	private static int N;
	private static int M;
	private static boolean[][] visited;
	private static Queue<int[]> q;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 지하 터널 지도 세로 크기
			M = Integer.parseInt(st.nextToken()); // 지하 터널 지도 가로 크기
			int R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 위치의 세로 좌표
			int C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 위치의 가로 좌표
			int L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간
			
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			q = new LinkedList<>();
			visited = new boolean[N][M];
			
			q.add(new int[] {R, C});
			visited[R][C] = true;
			
			int cnt = 0; // 장소의 개수
			int time = 0; // 경과한 시간
			while(!q.isEmpty()) {
				if(time == L) break; // 시간이 L시간 경과하면 while문 탈출
				int qSize = q.size();
				while(qSize-- > 0) {
					int[] cur = q.poll();
					
					cnt++; // 큐에서 꺼낼 때마다(==방문한 곳) 장소의 개수 증가
					
					int r = cur[0];
					int c = cur[1];
					
					int type = map[r][c]; // 현재 위치의 파이프 타입
					
					switch(type) {
						case 1: // 상하좌우
							search(r, c, 0);
							search(r, c, 1);
							search(r, c, 2);
							search(r, c, 3);
							break;
						case 2: // 상하
							search(r, c, 0);
							search(r, c, 1);
							break;
						case 3: // 좌우
							search(r, c, 2);
							search(r, c, 3);
							break;
						case 4: // 상우
							search(r, c, 0);
							search(r, c, 3);
							break;
						case 5: // 하우
							search(r, c, 1);
							search(r, c, 3);
							break;
						case 6: // 하좌
							search(r, c, 1);
							search(r, c, 2);
							break;
						case 7: // 상좌
							search(r, c, 0);
							search(r, c, 2);
							break;
					}
				} // end of while qSize
				++time;
			} // end of while queue
			
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n");
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main
	
	static boolean connCheck(int dr, int dc, int nextType) {
		// 상
		if(dr == -1 && dc ==  0) {
			if(nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) return true;
			else return false;
		}
		// 하
		else if(dr ==  1 && dc ==  0) {
			if(nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) return true;
			else return false;
		}
		// 좌
		else if(dr ==  0 && dc == -1) {
			if(nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) return true;
			else return false;
		}
		// 우
		else if(dr ==  0 && dc ==  1) {
			if(nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) return true;
			else return false;
		}
		return false;
	}
	
	static void search(int r, int c, int i) {
		int nr = r + dr[i];
		int nc = c + dc[i];
		if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc]==0) return;
		int nextType = map[nr][nc];
		if(connCheck(dr[i], dc[i], nextType)) {
			q.add(new int[] {nr, nc});
			visited[nr][nc] = true;
		}
	}
} // end of class
