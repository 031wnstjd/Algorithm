import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_10026_적록색약_골5_추준성_84ms {
	/*
	 * 1. 적록색약 : 빨간색과 초록색의 차이를 느끼지 못함 => R과 G를 동일하게 처리
	 * 2. dfs 탐색
	 * 3. 적록색약은 R과G를 동일하게 보고 방문처리 & 구역 카운트
	 * 4. 방문했으면 map상에서 그 위치의 원소를 '0'으로 변환
	 */
	private static int[] dr = {0, 0, 1,-1};
	private static int[] dc = {1,-1, 0, 0};
	private static int N;
	private static char[][] map1;
	private static char[][] map2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map1 = new char[N][];
		map2 = new char[N][];
		String line = null;
		for (int i = 0; i < N; i++) {
			line = br.readLine();
			map1[i] = line.toCharArray();
			map2[i] = line.replace('R', 'G').toCharArray();
		}
		
		// 적록 색약이 아닌 경우
		int cnt1 = 0;
		int cnt2 = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map1[r][c]!='0') { // 방문하지 않았으면 탐색
					dfs(map1, r, c);
					cnt1++;
				}
				if(map2[r][c]!='0') {
					dfs(map2, r, c);
					cnt2++;
				}
			}
		}
		
		// 적록 색약인 경우
		
		System.out.print(cnt1 + " " + cnt2);
		 
	} // end of main
	
	public static void dfs(char[][] map, int r, int c) { // 적록색약이 아닌 경우
		char current = map[r][c]; // 현재 위치의 문자
		map[r][c] = '0'; // 방문처리
		for (int i = 0, end = dr.length; i < end; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] == current) { // 탐색 지점의 문자가 현재 문자와 같으면 지속 탐색
				dfs(map, nr, nc);
			}
		}
	} // end of method dfs

} // end of class
