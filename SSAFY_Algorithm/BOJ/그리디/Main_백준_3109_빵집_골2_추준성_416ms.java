import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_3109_빵집_골2_추준성_416ms {
	
	/*
	 * 1. 첫째열의 시작점을 돌며 우, 우상, 우하로 이동
	 * 2. 건물이 있으면 그 지점으로 이동 불가 (건물 좌표값 == 'X', 건물이 없으면 '.')
	 * 3. 그리디 - 우상 > 우 > 우하 순으로 우선순위 적용. (방문한 곳은 'X'로 만들기)
	 * 4. 3과 동일한 내용 - 여러 탐색 조합 중에 가장 윗쪽에 있는 길을 선택
	 */

	private static int R;
	private static int C;
	private static int dr[] = {-1, 0, 1}; // 우상, 우, 우하
	private static int dc[] = { 1, 1, 1};
	private static char[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int r = 0; r < R; r++) {
			map[r][0] = 'x'; // 현재위치 방문처리
			dfs(0, r, 0); // 행의 위치만 바뀌면서 재귀 탐색
			flag = false; // flag 초기화
		}
		
		System.out.print(cnt);
	} // end of main
	
	private static int cnt = 0; // 파이프 라인의 개수
	private static boolean flag;
	public static void dfs(int depth, int r, int c) { 
		// 기저 조건
		if(depth == C-1) { // 마지막 지점까지 도달하면 cnt++;
			flag = true; // 마지막까지 도달했음을 알림
			cnt++;
			return;
		}
		
		// 우상 > 우 > 우하 순서로 우선순위를 적용하여 탐색, 
		for (int i = 0; i < dc.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != 'x') { // 범위를 벗어나지 않고 x를 만나지 않으면
				map[nr][nc] = 'x'; // 해당 지점 방문 처리
				dfs(depth+1, nr, nc); // 해당 지점 탐색
				if(flag) { // 마지막까지 도달했으면 다음 재귀는 돌지 않음
					return;
				}
			}
		} 
	} // end of method dfs
	
} // end of class
