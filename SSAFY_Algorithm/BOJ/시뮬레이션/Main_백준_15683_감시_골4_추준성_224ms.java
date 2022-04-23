import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_15683_감시_골4_추준성_224ms {
	/*
	 * <설계>
	 * 1. 각 CCTV의 방향 정하기 (dfs 순열)
	 * 2. 해당 방향으로 감시(bfs 탐색)
	 * 3. 0의 개수 카운트
	 */
	static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dc = { 0, 1, 0,-1};
	static int[][][] cameraDir = {
									{{0}}, // index 맞추기 위해 0은 의미 없는 값
									{{0}, {1}, {2}, {3}}, // 1번 cctv 감시 방향
									{{0, 2}, {1, 3}}, // 2번 cctv 감시 방향
									{{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번 cctv 감시 방향
									{{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}}, // 4번 cctv 감시 방향
									{{0, 1, 2, 3}} // 5번 cctv 감시 방향
								};
	private static ArrayList<Camera> cameraList;
	private static int min;
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] copyMap;
	
	static class Camera {
		int r;
		int c;
		int num;
		public Camera(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cameraList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0 || map[i][j] == 6) continue;
				cameraList.add(new Camera(i, j, map[i][j]));
			}
		}
		
		
		
		int[][] selectedDir = new int[cameraList.size()][]; // 선택된 각 cctv의 방향 정보 저장용 (각 cctv의 방향정보가 다 다르므로 두 번째 배열의 크기는 미정 - 동적할당)
		min = Integer.MAX_VALUE;
		
		solve(0, selectedDir);
		
		System.out.print(min);
	} // end of main

	static void solve(int depth, int[][] selectedDir) {
		if(depth == cameraList.size()) {
			copyMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, M);
			}
			for (int i = 0; i < selectedDir.length; i++) {
				for (int j = 0; j < selectedDir[i].length; j++) { // i번 째 카메라의 감시 방향
					observe(cameraList.get(i), selectedDir[i][j]); // 선택된 방향으로 감시
				}
			}
			getNumOfBlind(); // 사각지대 최솟값 갱신
			return;
		}
		
		for (int j = 0, cameraNum = cameraList.get(depth).num; j < cameraDir[cameraNum].length; j++) { // depth번째 카메라 번호에 해당하는 카메라의 방향 뽑기
			selectedDir[depth] = cameraDir[cameraNum][j]; // j번째 방향을 뽑음
			solve(depth+1, selectedDir);
		}
	}
	
	static void observe(Camera camera, int dir) {
		int r = camera.r;
		int c = camera.c;
		
		while(true) {
			r += dr[dir];
			c += dc[dir];
			
			if(r < 0 || r >= N || c < 0 || c >= M || copyMap[r][c] == 6) return;
			
			if(copyMap[r][c] == 0) copyMap[r][c] = -1;
		}
	}
	
	static void getNumOfBlind() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) cnt++;
			}
		}
		min = Math.min(min, cnt);
	}
	
} // end of class
