import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_20058_마법사상어와파이어스톰_골4_추준성_292ms {

	/*
	 * <조건>
	 * 1. 파이어스톰은 먼저 격자를 2^L × 2^L 크기의 부분 격자로 나눈다.
	 * 2. 그 후, 모든 부분 격자를 시계 방향으로 90도 회전
	 * 3. 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다. (2중 for문, 4방탐색 체크)
	 * 
	 * <구하고자 하는 것>
	 * Q번 시전 후,
	 * 1. 남아있는 얼음 A[r][c]의 합 (2중 for문)
	 * 2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수 (2중 for문, bfs 탐색, 방문체크)
	 * 
	 * - 분할, 회전 구현이 핵심 => 2중 for문 & 인덱싱 응용
	 * 
	 */
	
	private static int M;
	private static int[][] map;
	private static boolean[][] visited;
	private static Queue<int[]> queue;

	static int[] dr = { 0, 0,-1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		M = (int) Math.pow(2, N);
		map = new int[M][M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while(Q-- > 0) {
			int L = Integer.parseInt(st.nextToken());
			if(L != 0) map = devide(0, 0, (int) Math.pow(2, L)); // 분할 길이가 1이 아닐 때만 분할
			melt(); // 얼음 녹이기
		}
		
		sb.append(getTotalIce()).append("\n").append(getBiggestRegion());
		System.out.print(sb.toString());
		
	} // end of main
	
	static int[][] devide(int r, int c, int seg) { // 좌표, 분할 길이
		int[][] tmpMap = new int[M][M]; // map의 회전 결과를 임시로 저장할 2차원 배열
		for (int i = 0; i < M; i+=seg) {
			for (int j = 0; j < M; j+=seg) {
				rotate(r+i, r+j, seg, tmpMap);
			}
		}
		return tmpMap;
	}
	
	static void rotate(int r, int c, int seg, int[][] tmpMap) { // 좌표, 분할길이
		for (int i = 0; i < seg; i++) {
			for (int j = 0; j < seg; j++) {
				tmpMap[r + j][c + seg - 1 - i] = map[r + i][c + j]; // tmpMap은 참조형이므로, 매개변수로 받아도 전역변수처럼 사용 됨(원래 배열에 영향 O)
			}
		}
	}
	
	static void melt() {
		ArrayList<int[]> iceList = new ArrayList<>();
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 0) continue;
				int cntIce = 0;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= M || nc < 0 || nc >= M) continue;
					
					if(map[nr][nc] != 0) cntIce++;
				}
				if(cntIce < 3) iceList.add(new int[] {r, c}); // 얼음은 동시에 녹아야 하므로 따로 저장
			}
		}
		
		// 얼음 녹이기
		for (int[] ice : iceList) {
			map[ice[0]][ice[1]]--;
		}
	}
	
	static int bfs(int i, int j) {
		queue.add(new int[] {i, j});
		visited[i][j] = true;
		
		int cntIce = 0;
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			cntIce++;
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if(nr < 0 || nr >= M || nc < 0 || nc >= M || map[nr][nc] == 0 || visited[nr][nc]) continue;
				
				queue.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
		return cntIce;
	}
	
	static int getTotalIce() {
		int totalIce = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				totalIce += map[i][j];
			}
		}
		return totalIce;
	}
	
	static int getBiggestRegion() {
		queue = new LinkedList<>();
		visited = new boolean[M][M];
		int max = 0; // 덩어리가 없으면 0을 출력
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue;
				max = Math.max(max, bfs(i, j));
			}
		}
		return max;
	}
	
} // end of class
