import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_3190_뱀_골5_추준성_112ms {
	/*
	 * 0. 벽 바깥 == 테두리 == -1
	 * 0. 사과 있는 곳 == 1
	 * 0. 뱀은 맨 처음에 1행1열에서 오른쪽을 향함
	 * 
	 * 1. 시간을 카운트하며 이동 => 특정 시간이 되었을 때 방향 전환
	 * 2. 사과를 먹었을 때와, 안 먹었을 때의 차이 적용하여 시뮬레이션
	 * 3. 벽 또는 자기 자신의 몸과 부딪히면 게임 끝
	 * 
	 * 주요 아이디어 : 뱀의 이동을, "LinkedList에 좌표를 넣고, 사과를 먹지 않았을 시 꼬리를 삭제하는 방식"으로 구현   
	 * 주의할 점 : 사과를 먹은 걸 좌표상에서 처리를 안하면 또다시 그 위치에 갔을 때 꼬리를 자르지 않고 길이가 늘어나게되므로 꼭 좌표상에서 사과 먹은 처리를 해야 함 
	 */
	static class DirecInfo {
		int time;
		char direction;
		
		public DirecInfo(int time, char direction) {
			super();
			this.time = time;
			this.direction = direction;
		}
		
	}
	
	private static int dirIdx;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[N+2][N+2]; // 테두리
		
		// map 테두리 작업
		for (int i = 0; i < N+2; i++) {
			map[i][0] = map[0][i] = map[i][N+1] = map[N+1][i] = -1; // 테두리를 -1로 채움
		}
		
		// map에 사과(1) 채우기
		StringTokenizer st = null;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int appleRow = Integer.parseInt(st.nextToken());
			int appleCol = Integer.parseInt(st.nextToken());
			map[appleRow][appleCol] = 1; // 사과가 존재하는 곳을 1로 채움
		}
		
		// 방향 전환 정보 받고, 배열에 저장
		int L = Integer.parseInt(br.readLine());
		DirecInfo[] direcInfoArr = new DirecInfo[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			direcInfoArr[i] = new DirecInfo(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		
		
		int[] dr = {0, 1, 0,-1};
		int[] dc = {1, 0,-1, 0}; // 우하좌상 (시계방향)
		
		int nr=1, nc=1;
		int time = 0;
		
		LinkedList<int[]> snake = new LinkedList<>(); // 뱀의 이동을 관리할 연결 리스트
		snake.add(new int[] {nr,nc}); // 뱀의 초기 위치를 담음
		
		dirIdx = 0; // 초기 방향은 '우'
		while(true) { // 벽에 부딪히거나 자기 자신의 몸과 부딪힐 때까지 시뮬레이션 탐색
			
			time++; // 한 번 움직일 때마다 1초 증가
			
			// 시뮬레이션 탐색을 위한 다음 좌표 할당
			nr += dr[dirIdx]; 
			nc += dc[dirIdx];
			
			// 종료 조건
			// 1. 자기 자신을 만날 때 종료
			for (int i = 0; i < snake.size(); i++) { 
				if(snake.get(i)[0] == nr && snake.get(i)[1] == nc) {
					System.out.println(time);
					return;
				}
			}
			
			// 2. 벽을 만날 때 종료
			if(map[nr][nc] == -1) {
				System.out.println(time);
				return;
			}
			
			// 시뮬레이션 탐색 진행
			if(map[nr][nc] == 0) { // 아무것도 없으면 다음 좌표를 리스트에 담고, 이전 좌표(꼬리) 삭제
				snake.add(new int[] {nr,nc});
				snake.remove(0); // 꼬리 삭제
			}
			
			if(map[nr][nc] == 1) { // 사과가 있으면 해당 좌표값을 0으로 바꾸고, 해당 좌표를 리스트에 담음
				map[nr][nc] = 0;
				snake.add(new int[] {nr,nc});
			}
			
			// 다음 이동의 방향 전환 체크
			for (int i = 0; i < L; i++) {
				// 오른쪽 90도면 dirIdx++(dirIdx 3보다 크면 dirIdx=0), 왼쪽 90도면  dirIdx--(dirIdx 음수되면 dirIdx=3)
				if(time == direcInfoArr[i].time && direcInfoArr[i].direction == 'L') {
					dirIdx--;
					if(dirIdx < 0) {
						dirIdx = 3;
					}
				}
				if(time == direcInfoArr[i].time && direcInfoArr[i].direction == 'D') {
					dirIdx++;
					if(dirIdx > 3) {
						dirIdx = 0;
					}
				}
			}

		} // end of while 
		
	} // end of main

} // end of class
