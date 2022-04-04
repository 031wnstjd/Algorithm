import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_SWEA_1767_프로세서연결하기_추준성_195ms {
	/*
	 * 4^12 = 2^24 = 천만
	 * 
	 * <조건 분석>
	 * 전선이 엉키지 않으면서 전선의 길이의 합이 최소가 되게끔 해야함
	 * "전선이 엉키지 않아야 함" : 모든 경우의 수 다 따져봐야 함 => 완탐  => 안되면 다시 되돌아와야 하므로 DFS(백트래킹)
	 * "전선 길이의 합 최소" : 전선 연결이 다 이루어지면 최솟값 따지고 갱신
	 * 
	 * <설계>
	 * 0. 테두리 core는 제외하고 전선 연결 (1~N-2)
	 * 1. 입력 받을 때 core 위치를 따로 리스트에 담음 (탐색에 이용)
	 * 2. DFS(백트래킹) 탐색하면서 전선 채움
	 * 3. 전선 연결이 실패되면 리턴돼서 돌아왔을 때 채웠던 전선 다시 공백으로 리셋 
	 * 4. 4방 탐색을 다 했음에도 불구하고 전선이 카운트가 안되면 다음 core로 이동
	 * 
	 * 재귀함수 : makeWires(coreIdx, wireLength)
	 * 
	 */
	
	static class Core {
		int r;
		int c;
		
		public Core(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static int minWireLength;
	private static int maxCoreCnt;
	private static int totalCoreCnt;
	private static ArrayList<Core> cores;
	private static int[] dr = {1,-1, 0, 0};
	private static int[] dc = {0, 0, 1,-1};
	private static int[][] map;
	private static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			// 입력 받기
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<Core>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i==0 || j==0 || i==N-1 || j==N-1) continue; // 테두리는 core로 고려 X (전선 필요 X)
					if(map[i][j] == 1) cores.add(new Core(i, j)); // core 위치 리스트에 저장
				}
			} 
			
			// 전선 길이의 합 구하기
			totalCoreCnt = cores.size();
			maxCoreCnt = -1;
			minWireLength = Integer.MAX_VALUE;
			
			makeWires(0, 0, 0);
			
			sb.append("#").append(testCase).append(" ").append(minWireLength).append("\n");
		} // end of for testCase
		System.out.println(sb.toString());
	} // end of main

	public static void makeWires(int coreIdx, int wireLength, int coreCnt) {
		// 기저 조건
		if(coreIdx == totalCoreCnt) {
			// core 수가 많은 게 전선 길이의 합이 큰 것 보다 우선순위
			if(coreCnt > maxCoreCnt) { // 코어수가 더 많으면  wireLength를 그냥 갱신 
				maxCoreCnt = coreCnt;
				minWireLength = wireLength;
			} 
			else if(coreCnt == maxCoreCnt && wireLength < minWireLength) { // 코어수가 같으면 wireLength 최솟값 갱신
				minWireLength = wireLength;
			}
			return;
		}
		
		// coreIdx에 해당하는 코어 4방향 DFS 탐색(전선 채우기)
		Core core = cores.get(coreIdx);
		for (int i = 0; i < 4; i++) {
			int cntWire = 0;
			int nr = core.r;
			int nc = core.c;
			
			// 전선 채우기 시도
			while(true) {
				nr += dr[i]; // dr[i] 방향으로 계속 이동
				nc += dc[i];

				// 종료 조건
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) { // 범위 밖으로 벗어나면 전선 연결 완료
					makeWires(coreIdx+1, wireLength + cntWire, coreCnt+1);
					break; // DFS 탐색 종료 후 while문 탈출
				}
				if(map[nr][nc] == 1) break; // 다른 코어나 전선 만나면 다른 방향 탐색
				
				// 전선 채우기 (1로 채움)
				map[nr][nc] = 1;
				
				cntWire++; // 전선 길이 증가
			}
			
			// 해당 방향으로 채웠던 전선 모두 리셋 (while문의 종료 조건을 만난 경우)
			while(cntWire > 0) {
				nr -= dr[i];
				nc -= dc[i];
				map[nr][nc] = 0;
				cntWire--;
			}
		}
		
		makeWires(coreIdx+1, wireLength, coreCnt); // 해당 코어를 포함하지 않는 경우
		
	} // end of method makeWires
	
} // end of class
