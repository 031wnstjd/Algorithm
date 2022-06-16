import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17144_미세먼지안녕_골4_추준성_312ms {
	
	private static int[][] map;
	private static int R;
	private static int C;
	
	static class Circulator {
		int r;
		int c;
		public Circulator(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = { 0, 1, 0,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		int[] rows = new int[2];
		
		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) rows[idx++] = i;; // 공기청정기 위치
			}
		}
		
		for (int time = 0; time < T; time++) {
			diffuse();
			circulate(0, rows[0], 0); // 위
			circulate(1, rows[1], 0); // 아래
		}
		
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == -1) continue;
				sum += map[i][j];
			}
		}
		
		System.out.print(sum);
		
		
	} // end of main

	private static void diffuse() {
		int[][] resMap = new int[R][C]; // 확산된 미세먼지를 임시로 저장할 배열
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == -1 || map[r][c] < 5) {
					resMap[r][c] += map[r][c]; // resMap 업데이트만 해주고 continue
					continue;
				}
				
				int dust = map[r][c];
				int cnt = 0;
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1) continue;
					
					resMap[nr][nc] += dust/5;
					cnt++;
				}
				
				resMap[r][c] += dust - (dust/5) * cnt; // 확산 후 남아있는 먼지양 resMap에 업데이트
			}
		}
		
		map = resMap; // resMap으로 갈아끼우기
	}

	private static void circulate(int type, int r, int c) {
		int sr = r;
		int sc = c;
		if(type == 0) { // 윗쪽
			// 위
			while(true) {
				int nr = r - 1;
				int nc = c;
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) break;
				if(map[r][c] == -1) map[nr][nc] = 0; // 공기청정기로 내려오면 사라짐
				else {
					map[r][c] = map[nr][nc]; // 공기청정기가 아니면 먼지가 내려옴
					map[nr][nc] = 0;
				}
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
			// 오른쪽
			while(true) {
				int nr = r;
				int nc = c + 1;
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) break;
				
				map[r][c] = map[nr][nc];
				map[nr][nc] = 0;
				
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
			// 아래
			while(true) {
				int nr = r + 1;
				int nc = c;
				if(nr < 0 || nr > sr || nc < 0 || nc >= C) break;
				
				map[r][c] = map[nr][nc]; 
				map[nr][nc] = 0;
				
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
			// 왼쪽
			while(true) {
				int nr = r;
				int nc = c - 1;
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1) break;
				
				map[r][c] = map[nr][nc]; 
				map[nr][nc] = 0;
				
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
		} else { // 아랫쪽
			// 아래
			while(true) {
				int nr = r + 1;
				int nc = c;
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) break;
				if(map[r][c] == -1) map[nr][nc] = 0; // 공기청정기로 내려오면 사라짐
				else {
					map[r][c] = map[nr][nc]; // 공기청정기가 아니면 먼지가 올라옴
					map[nr][nc] = 0;
				}
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
			// 오른쪽
			while(true) {
				int nr = r;
				int nc = c + 1;
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) break;
				
				map[r][c] = map[nr][nc]; 
				map[nr][nc] = 0;
				
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
			// 위
			while(true) {
				int nr = r - 1;
				int nc = c;
				if(nr < sr || nr >= R || nc < 0 || nc >= C) break;
				
				map[r][c] = map[nr][nc]; 
				map[nr][nc] = 0;
				
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
			// 왼쪽
			while(true) {
				int nr = r;
				int nc = c - 1;
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1) break;
				
				map[r][c] = map[nr][nc]; // 공기청정기가 아니면 먼지가 내려옴
				map[nr][nc] = 0;
				
				r = nr; // 탐색 위치 업데이트
				c = nc;
			}
		}
	}

} // end of class
