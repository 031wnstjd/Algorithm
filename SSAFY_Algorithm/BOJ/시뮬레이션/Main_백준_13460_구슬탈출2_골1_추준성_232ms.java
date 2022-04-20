import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_13460_구슬탈출2_골1_추준성_232ms {
	/*
	 * 1. 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기 => 상하좌우 (4방탐색)
	 * 2. 빨간 구슬이 구멍에 빠지면 성공, 파란 구슬이 구멍에 빠지면 실패
	 * 3. 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패
	 * 4. 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 
	 * 5. 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지
	 * 6. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지
	 * 7. 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력
	 * 
	 * 구하고자 하는 것 : "최소 몇 번" 만에 "빨간 구슬"을 구멍을 통해 빼낼 수 있는지 
	 * 
	 * <설계>
	 * 알고리즘 : 현 상태에서 R, B의 위치를 모두 고려하여 결정 내려야함(다른 상태가 섞이면 안됨) => DFS / 백트래킹
	 * 1. 기울이는 방향 설정
	 * 2. 빈칸이면 go, 아니면 stop을 R, B에 대하여 계속 반복 
	 * - 둘 다 stop했을 때 while문 탈출 
	 * - 빨간 구슬이 O에 들어갔을 때(이땐 움직인 횟수의 최솟값 갱신  & 리턴)
	 * - 파란 구슬이 O에 들어가거나, 둘 다 들어갔을 때 continue 
	 * 3. DFS 다 돌고, 최솟값 갱신이 안되었으면 -1을 출력
	 * 
	 * <재설계>
	 * 1. 굳이 map을 복사하지 않아도 됨 (map은 R, B없는 상태로 두고 계속 반복 사용. R과 B의 위치만 매개변수로 받아서 겹치는지 비교해서 판단)
	 * 2. 이동 가능한지, O에 들어갔는지를 판별해야하므로 이에 해당하는 boolean 변수 사용
	 * 3. 'nr = redR + dr[i]' 와 'redR += dr[i]' 쓰임새의 차이를 명확히 구분하여 사용하기
	 * - 'nr = redR + dr[i]'는 임시로 nr 변수에 담고 다음 이동이 가능한지 판별할 때 사용 (4방 탐색에서 판별할 때 무조건 nr, nc 사용)
	 * - 'redR += dr[i]'는 실제로 이동을 하는 것(redR 값을 업데이트 == BFS에서 큐에 nr, nc를 담는 행위)
	 * => 목적에 맞게 구분하여 사용!!
	 * 
	 */
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	private static int min;
	private static int n;
	private static int m;
	private static Position[] pos;
	private static char[][] board;
	
	static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pos = new Position[2];
		board = new char[n][];
		for (int i = 0; i < n; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if(board[i][j] == 'R') {
					pos[0] = new Position(i, j);  
					board[i][j] = '.';
				}
				else if(board[i][j] == 'B') {
					pos[1] = new Position(i, j);  
					board[i][j] = '.';
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		
		dfs(1, pos[0], pos[1]);
		
		System.out.print(min == Integer.MAX_VALUE ? -1 : min);
		
	} // end of main
	
	static void dfs(int cnt, Position posR, Position posB) { // 시행횟수, 구슬(R&B)위치 
		if(cnt == 11) return;
		
		for (int i = 0; i < 4; i++) {
			
			int redR = posR.r;
			int redC = posR.c;
			int blueR = posB.r;
			int blueC = posB.c;
			
			boolean isPossibleRed = true; // 이동 가능한지
			boolean isPossibleBlue = true;
			boolean isGoalRed = false; // O에 들어갔는지
			boolean isGoalBlue = false;
			
			while(isPossibleRed || isPossibleBlue) { // 둘 다 이동 불가능할 때까지
				
				if(!isGoalRed) { // O에 안 들어갔으면 이 로직 실행
					int nr = redR + dr[i];
					int nc = redC + dc[i];
					
					if(0 <= nr && nr < n && 0 <= nc && nc < m) { // 범위 체크
						if(board[nr][nc] == '.'){
							if(nr == blueR && nc == blueC) isPossibleRed = false; // nr, nc가 현재 blue와 겹치면 이동 X
							else {
								isPossibleRed = true;
								redR += dr[i]; // 이동가능하면 실제로 redR, redC 업데이트
								redC += dc[i];
							}
						} else if(board[nr][nc] == 'O') {
							isPossibleRed = false;
							isGoalRed = true;
							redR = -1; // blue 이동에 영향 안 미치게끔 -1로 바꿈
							redC = -1;
						} else if(board[nr][nc] == '#'){
							isPossibleRed = false; // 이동 불가능 상태
						}
					}
				}
				
				if(!isGoalBlue) {
					int nr = blueR + dr[i];
					int nc = blueC + dc[i];
					
					if(0 <= nr && nr < n && 0 <= nc && nc < m) { // 범위 체크
						if(board[nr][nc] == '.'){
							if(nr == redR && nc == redC) isPossibleBlue = false; // nr, nc가 현재 red와 겹치면 이동 X
							else {
								isPossibleBlue = true;
								blueR += dr[i]; // 이동가능하면 실제로 blueR, blueC 업데이트
								blueC += dc[i];
							}
						} else if(board[nr][nc] == 'O') {
							isPossibleBlue = false;
							isGoalBlue = true;
							blueR = -1; // red 이동에 영향 안 미치게끔 -1로 바꿈
							blueC = -1;
						} else if(board[nr][nc] == '#'){
							isPossibleBlue = false; // 이동 불가능 상태
						}
					}
				}
			} // end of while (이동)

			if(isGoalBlue) continue; // blue가 들어가더라도, 다른 방향 탐색해봐야 하므로 return이 아닌 continue
			else if(isGoalRed) {
				min = Math.min(min, cnt); // min값 업데이트
				return; // red가 들어가면 그게 현재 행할 수 있는 최소 이므로 재귀 종료
			}
			
			dfs(cnt+1, new Position(redR, redC), new Position(blueR, blueC));
			
		} // end of for (4방 탐색)
		
	} // end of dfs
	
} // end of class
