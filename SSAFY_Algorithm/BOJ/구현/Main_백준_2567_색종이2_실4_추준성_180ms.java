import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2567_색종이2_실4_추준성_180ms {
	/*
	 * 1. 모든 사각형 내부를 1로 다 채움
	 * 2. map 전체를 탐색하면서 1을 만나면 4방 탐색을 통해 테두리인지 검사 진행 & 둘레 카운트
	 * 3. 4방 탐색시 0이 존재한다면 이는 테두리이므로 카운팅
	 * 4. 주의사항 : 꼭짓점의 경우 카운팅 두 번 (0이 사방에 2개 존재하기 때문) (사각형의 한 변의 길이가 4이상일 때만 성립)
	 */
	private static int[] dr = {1,-1, 0,0};
	private static int[] dc = {0, 0,-1,1};
	private static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[101][101]; // 1~100까지 사용
		int N = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < N; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			
			// 사각형 영역 1로 채우기
			for (int r = row; r < row+10; r++) {
				for (int c = col; c < col+10; c++) {
					map[r][c] = 1;
				}
			}
			
			// 1을 만나면 4방 탐색, 4방 탐색시 0이 존재하면 cnt++;
			cnt = 0;
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map.length; c++) {
					if(map[r][c] == 1) { // 1을 만나면 4방탐색 (꼭짓점이면 카운트 두 번 됨)
						int[] dr = {1,-1, 0,0};
						int[] dc = {0, 0,-1,1};
						for (int i = 0; i < dr.length; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];
							if(map[nr][nc] == 0) {
								cnt++;
							}
						}
					}
				}
			}
		} // end of for testCase
		System.out.print(cnt);
	} // end of main

} // end of class
