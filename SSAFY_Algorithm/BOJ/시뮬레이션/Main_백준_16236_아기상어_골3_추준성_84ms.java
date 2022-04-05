import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_16236_아기상어_골3_추준성_84ms {
	/*
	 * 1. BFS의 기본적인 틀을 지키면서, PriorityQueue를 활용할 때, 정렬 기준을 명확히 선언하는 게 중요!(=> 일반적인 BFS 탐색 시 발생할 수 있는 '너비 순서 뒤틀림 현상'을 방지할 수 있음)  
	 * 2. BFS는 결국 모든 영역을 탐색하기 위한 도구일 뿐, 그 외에 추가적인 조건들은 우선순위큐의 정렬 기준에 따라 내부적으로 구현됨
	 */
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = { 0,-1, 1, 0};
	static int[][] map;
	
	static class SharkInfo implements Comparable<SharkInfo>{
		int row;
		int col;
		int dist;
		
		public SharkInfo(int row, int col, int dist) {
			super();
			this.row = row; // 행
			this.col = col; // 열
			this.dist = dist; // 움직인 거리
		}
		
		@Override
		public int compareTo(SharkInfo o) {
			return (this.dist != o.dist) ? this.dist - o.dist : (this.row != o.row) ? this.row - o.row : this.col - o.col; // 정렬 우선 순위 : 거리 > 윗방향 > 왼쪽방향
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		SharkInfo cur = null; // 상어의 현재 위치 정보
		
		// map 정보 입력 받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) { // 아기상어 초기 위치 정보 저장
					cur = new SharkInfo(i, j, 0);
					map[i][j] = 0; // 해당 위치의 값 0으로 초기화
				}
			}
			
		}
		
		int size = 2; // 상어의 초기 사이즈는 2
		int eat = 0; // 먹은 물고기 수
		int move = 0; // 움직인 총 거리
		
		while(true) { // BFS를 계속 돌리면서, 더이상 먹을 물고기가 없을 때까지 반복
			PriorityQueue<SharkInfo> pQueue = new PriorityQueue<>(); // 상어의 위치정보를 담을 우선순위 큐를 먹이를 먹을 때마다 새로 생성 (== 먹이를 먹을 때마다, 그 위치를 초기 위치로 하여 BFS 반복)
			boolean[][] visited = new boolean[N][N]; // 방문처리용
			
			pQueue.add(cur); // 초기 위치 정보 담기
			visited[cur.row][cur.col] = true; // 초기 위치 방문 처리
			
			boolean isAte = false; // 상어가 먹이를 먹었는지 체크할 변수
			
			// BFS를 통해 먹이를 먹을 때까지 지속 탐색
			while(!pQueue.isEmpty()) { 
				cur = pQueue.poll();
				
				// BFS 종료 조건
				if(map[cur.row][cur.col] != 0 && map[cur.row][cur.col] < size) { // 먹이가 있으면서 상어의 사이즈보다 작다면 (== BFS 탐색의 종료 조건) (queue에서 꺼낼 때 체크 => 초기 상태까지 체크 가능)
					map[cur.row][cur.col] = 0; // 먹이를 먹음
					eat++;
					move = cur.dist; // 먹이를 먹을 때까지 총 움직인 거리 따로 저장
					isAte = true; // 먹이를 먹은 걸 체크
					// 사이즈만큼 물고기를 먹었으면 사이즈 증가 & 먹은 개수 0으로 초기화
					if(size == eat) {
						size++;
						eat = 0;
					}
					break; // 먹이를 먹었으면 탈출
				}
				
				// 4방 탐색
				for (int i = 0; i < 4; i++) {
					int nr = cur.row + dr[i];
					int nc = cur.col + dc[i];
					if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && (map[cur.row][cur.col] == 0 || map[cur.row][cur.col] == size)) { // 먹이가 없거나 상어의 사이즈와 같다면
						pQueue.add(new SharkInfo(nr, nc, cur.dist+1)); // 현재 움직인 거리보다 1만큼 더 큰 값을 움직인 거리로 할당
						visited[nr][nc] = true; // 방문 처리
					}
				}
				
			}
			
			if(!isAte) break; // queue가 빌 때까지 탐색(== BFS를 통해 모든 영역을 탐색)했는데 먹이를 못 먹었으면 while문 탈출
		}
		System.out.println(move);
	} // end of main
} // end of class
