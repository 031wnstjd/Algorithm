import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main_백준_21610_마법사상어와비바라기_골5_추준성_196ms {
	/*
	 * 0. 이동 정보를 저장할 moveInfo[M] 배열
	 * 1. 물의 양을 저장할 map[N][N]
	 * 2. Cloud 객체를 담을 리스트인 ArrayList<Cloud> cloudList
	 * 3. 이동위치 저장 리스트 saveList
	 *
	 * <설계>
	 * 1. 리스트에 담긴 구름들 이동 정보에 맞게 모두 이동
	 * 2. 이동한 위치 (nr, nc)에서(경계 안이면) map[nr][nc] += 1, (nr,nc)를 saveList에 저장
	 * 3. cloudList 초기화
	 * 4. saveList 정보를 통해 물복사버그 마법 시전
	 * 5. map을 탐색하면서 값이 2이상인 위치에서 구름생성 -> cloudList에 추가
	 */
	static class MoveInfo {
		int d;
		int s;
		public MoveInfo(int d, int s) {
			super();
			this.d = d;
			this.s = s;
		}
	}
	
	static class Cloud {
		int r;
		int c;
		public Cloud(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {0, 0,-1,-1,-1, 0, 1, 1, 1};
	static int[] dc = {0,-1,-1, 0, 1, 1, 1, 0,-1};
	private static ArrayList<Cloud> cloudList;
	private static int N;
	private static int[][] map;
	private static boolean[][] isOut;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		MoveInfo[] moveInfo = new MoveInfo[M];
		cloudList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			moveInfo[i] = new MoveInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		cloudList.add(new Cloud(N-1, 0));
		cloudList.add(new Cloud(N-1, 1));
		cloudList.add(new Cloud(N-2, 0));
		cloudList.add(new Cloud(N-2, 1));
		
		for (int i = 0; i < M; i++) {
			cloudMove(moveInfo[i].d, moveInfo[i].s); // 이동방향, 이동거리
			cloudRain();
			waterCopy();
			cloudOut();
			cloudMake();
		}
		
		System.out.print(answer());
		
	} // end of main

	private static void cloudMove(int d, int s) {
		for (Cloud cloud : cloudList) {
			cloud.r = (cloud.r + N + dr[d]*(s%N))%N;
			cloud.c = (cloud.c + N + dc[d]*(s%N))%N;
		}
	}

	private static void cloudRain() {
		for (Cloud cloud : cloudList) {
			map[cloud.r][cloud.c] += 1;
		}
	}

	private static void cloudOut() {
		isOut = new boolean[N][N];
		for (Cloud cloud : cloudList) {
			isOut[cloud.r][cloud.c] = true;
		}
		cloudList.clear();
	}

	private static void waterCopy() {
		for (Cloud cloud : cloudList) {
			int r = cloud.r;
			int c = cloud.c;
			
			int cnt = 0; // 대각선 방향으로 물이 있는 바구니의 수
			for (int i = 2; i <= 8; i+=2) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 0) continue;
				
				cnt++;
			}
			
			if(cnt != 0) map[r][c] += cnt; // 대각선 방향으로 물이 있는 바구니가 존재하면 해당 개수만큼 물의 양 증가
		}
	}

	private static void cloudMake() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] >= 2 && !isOut[i][j]) {
					cloudList.add(new Cloud(i, j));
					map[i][j] -= 2;
				}
			}
		}
	}

	private static int answer() {
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) ans += map[i][j];
			}
		}
		return ans;
	}
} // end of class
