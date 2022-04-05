import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
	

public class Main_백준_2667_단지번호붙이기_실1_추준성_124ms{
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = {-1,0,1, 0}; // 상우하좌
	private static int[] dc = { 0,1,0,-1};
	private static int cnt;
	private static int cntGroup;	
	/* 
	 * 현재 위치 방문 처리
	 * 현재 위치 좌표에 1이 담겨있고 방문하지 않았다면 재귀적으로 상하좌우 4방 탐색 (DFS)
	 * 방문하지 않은 곳만 방문하고, 방문 처리. 집의 수 카운트 증가 cnt++.
	 * 방문 체크를 통해 더이상 방문할 곳이 없으면 재귀문 탈출, 단지 수 카운트 증가 cntGroup++; 누적된 cnt 값을 cntArr 배열에 담음  & cnt값 초기화
	 * cntArr 배열을 오름차순으로 정렬하여 cnt 값들을 출력
	 * 주의 사항1 : 집이 한 개라면 연결되어있지 않은 것 = 단지 카운트에 반영 x
	 * 주의 사항2 : 어떤 집의 "좌우" "아래위" 연결이 적어도 하나 있어야 함 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] cntArr = new int[N*N];
		String s;
		map = new int[N+2][N+2]; // 띠 두른 map (4방 탐색시 indexOutOfBoundsException을 피하기 위함)
		visited = new boolean[N+2][N+2]; // 각 좌표 방문 체크용 배열
		for (int i = 1; i <= N; i++) {
			s = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = s.charAt(j-1) - '0';
			}
		}
		
		// 시작점을 결정하는 반복문
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) { // 현재 위치 좌표에 1이 담겨있고 방문하지 않았다면 재귀적으로 상하좌우 4방 탐색 (DFS)
					cnt = 0;
					dfsSearch(i, j);
					cntArr[cntGroup++] = cnt; // cntArr 배열에 순차적으로 cnt값을 담음
				}
			}
		}
		
		Arrays.sort(cntArr); // 오름차순 정렬
		sb.append(cntGroup).append("\n"); // 배열의 사이즈가 곧 단지의 개수
		for (int i = cntArr.length-cntGroup; i < cntArr.length; i++) { // 오름차순으로 정렬하면 앞의 cntArr.length-cntGroup-1만큼은 0이므로 그 다음부터 마지막까지 출력
			sb.append(cntArr[i]).append("\n");
		}
		System.out.print(sb.toString());
	} // end of main
	
	public static void dfsSearch(int r, int c) { // 탐색 시작 지점을 입력으로 받음
		visited[r][c] = true; // 현재 지점 방문 처리
		cnt++; // 집의 개수 카운트
		
		for (int i = 0; i < dc.length; i++) { // 4방 탐색 시작
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(map[nr][nc] == 1 && !visited[nr][nc]) { // 탐색 좌표에 1이 담겨져 있고 방문하지 않았다면 방문
				dfsSearch(nr, nc); // dfs 다음 좌표를 입력
			}
		}
	} // end of method
} // end of class
