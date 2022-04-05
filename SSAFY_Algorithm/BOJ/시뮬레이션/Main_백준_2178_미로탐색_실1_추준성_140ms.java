import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_2178_미로탐색_실1_추준성_140ms {
	
	private static int[] dr = {1,-1, 0, 0}; // 하상좌우
	private static int[] dc = {0, 0,-1, 1};
	private static int[][] map;
	private static boolean[][] visited;
	private static LinkedList<int[]> q;
	private static int N;
	private static int M;
	/*
	 * bfs로 도착지점까지 도달하는 최소 칸 수 구하기
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		q = new LinkedList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2]; // 띠 두른 맵
		visited = new boolean[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j-1) - '0';
			}
		}		
		bfs();
		System.out.print(map[N][M]); // 저장된 이동횟수 값 출력
	} // end of main
	
	public static void bfs() {		
		q.offer(new int[] {1,1}); // 시작 지점 추가
		visited[1][1] = true; // 시작지점 방문처리
		while(!q.isEmpty()) { // q가 빌 때까지 반복
			int[] current = q.poll(); // q에서 뽑은 현재 좌표
			if(current[0] == N && current[1] == M) { // 도착지점에 도달하면 함수 종료
				return;
			}
			// 4방 탐색
			for (int j = 0; j < dc.length; j++) {
				int nr = current[0] + dr[j];
				int nc = current[1] + dc[j];
				if(map[nr][nc] == 1 && !visited[nr][nc]) { // 범위 밖으로 벗어나지 않고(=0을 만나지 않고), 1을 만나면 지속적으로 bfs 탐색
					q.offer(new int[] {nr, nc}); // q에 해당 좌표 추가
					map[nr][nc] = map[current[0]][current[1]] + 1; // 이동횟수 저장
					visited[nr][nc] = true; // 탐색한 지점 방문 처리
				}
			}
		} // end of while
	} // end of method bfs

} // end of class
