import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_20056_마법사상어와파이어볼_골4_추준성_852ms {
	/*
	 * 구하고자 하는 것 : 마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합
	 * 
	 * <설계>
	 * 1. command() : while문을 통해 k번 명령을 반복
	 * 2. move() : list에 있는 파이어볼들 1번 조건 실행 (이동)
	 * 3. process() : 매번 이동이 끝나는 시점에 2번 조건 실행 
	 * 
	 * 위치 비교? => ArrayList<FireBall>[][] map = new ArrayList<>()[N][N] 활용 (해당 위치에 FireBall 객체 주입)
	 * 
	 * <핵심 구현 테크닉>
	 * 1. 자료구조 : ArrayList<FireBall>[][] map = new ArrayList<>()[N][N];
	 * 2. 모듈러 연산 : int nr = (r + N + dr[d] * (s % N)) % N;
	 * 3. 내장메소드 활용 : list.remove(fireBall); // 해당 객체를 list에서 삭제
	 * 
	 */
	static class FireBall {
		int r;
		int c;
		int m;
		int s;
		int d;
		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	private static ArrayList<FireBall> list;
	private static ArrayList<FireBall>[][] map;
	private static int N;
	private static int M;
	private static int K;
	
	static int[] dr = {-1,-1, 0, 1, 1, 1, 0,-1};
	static int[] dc = { 0, 1, 1, 1, 0,-1,-1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		
		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new FireBall(r, c, m, s, d));
		}
		
		command();
		
		int ans = 0;
		for (FireBall fireBall : list) {
			ans += fireBall.m;
		}
		
		System.out.print(ans);
	} // end of main

	static void command() {
		while(K-- > 0) {
			move();
			process();
		}
	}
	
	static void process() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c].size() <= 1) {
					map[r][c].clear();
					continue; // 파이어볼이 1개 이하면 continue
				}
				
				int nums = map[r][c].size();
				int sumMass = 0;
				int sumVel = 0;
				
				// 연산
				int oddCnt = 0;
				int evenCnt = 0;
				for (FireBall fireBall : map[r][c]) {
					sumMass += fireBall.m;
					sumVel += fireBall.s;
					list.remove(fireBall); // 해당 객체 list에서 삭제
					if(fireBall.d % 2 == 0) evenCnt++;
					else oddCnt++;
				}
				
				map[r][c].clear();
				int mass = sumMass / 5;
				int vel = sumVel / nums;
				
				if(mass == 0) continue; // 질량이 0인 파이어볼들은 소멸
				
				if(evenCnt == nums || oddCnt == nums) { // 0 2 4 6
					for (int dir = 0; dir < 8; dir += 2) {
						list.add(new FireBall(r, c, mass, vel, dir));
					}
				} else { // 1 3 5 7
					for (int dir = 1; dir < 8; dir += 2) {
						list.add(new FireBall(r, c, mass, vel, dir));
					}
				}
			}
		}
	}
	
	static void move() {
		for (FireBall fireBall : list) {
			int r = fireBall.r;
			int c = fireBall.c;
			int s = fireBall.s;
			int d = fireBall.d;
			
			int nr = (r + N + dr[d] * (s % N)) % N; // 빙글빙글 돎 -> 모듈러 연산
			int nc = (c + N + dc[d] * (s % N)) % N; // 속력3이 곱해져서 좌표값이 마이너스가 될 수 있으므로 N을 더하고, 속력에도 모듈러 연산을 해줌
			
			// 이동 위치 업데이트
			fireBall.r = nr;
			fireBall.c = nc;
			
			map[nr][nc].add(fireBall); // (r,c)에 FireBall 추가
		} // 이동 종료
	}
} // end of class
