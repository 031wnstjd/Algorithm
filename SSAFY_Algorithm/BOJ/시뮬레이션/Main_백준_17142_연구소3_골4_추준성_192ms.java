import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_17142_연구소3_골4_추준성_192ms {
	/*
	 * 구하고자 하는 것 : 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간 (모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우 -1을 출력)
	 * 
	 * <특징>
	 * 1. 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변한다. - 0은 아니지만 빈 칸 취급
	 * 
	 * <설계>
	 * 1. M개의 활성 바이러스 선택 (조합)
	 * 2. 0(빈칸) 개수 카운트
	 * 3. bfs 4방 탐색하면서 빈 칸 방문하면 0 개수 하나씩 줄이기 => 0이 되면 시간 갱신 후 리턴
	 * 
	 */
	static class Virus {
		int r;
		int c;
		int time;
		
		public Virus(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
	}

	private static ArrayList<Virus> virus;
	private static int m;
	private static int n;
	private static int min;
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	private static int[][] map;
	private static Virus[] selected;
	private static int zeroCnt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 4 <= n <= 50
		m = Integer.parseInt(st.nextToken()); // 1 <= m <= 10
		
		map = new int[n][n];
		virus = new ArrayList<>();
		
		zeroCnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// virus인 경우
				if(map[i][j] == 2) {
					virus.add(new Virus(i, j, 0)); // 위치저장
				}
				if(map[i][j] == 0) {
					zeroCnt++; // 0의 개수 카운트
				}
			}
		}
		
		selected = new Virus[m]; // 활성화 바이러스 m개 뽑음
		
		min = Integer.MAX_VALUE;
		
		comb(0, 0);
		System.out.print(min == Integer.MAX_VALUE ? -1 : min);
		
	} // end of main

	static void comb(int depth, int start) {
		if(depth == m) {
			bfs(zeroCnt);
			return;
		}
		for (int i = start, numOfVirus = virus.size(); i < numOfVirus; i++) {
			selected[depth] = virus.get(i);
			comb(depth+1, i+1);
		}
	}
	
	static void bfs(int zeroCnt){
		
		Queue<Virus> queue = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		
		for (int i = 0; i < m; i++) {
			Virus tmp = selected[i];
			queue.add(tmp);
			visited[tmp.r][tmp.c] = true; // 방문체크
		}
		
		while(!queue.isEmpty()) {
			Virus cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int time = cur.time;

			if(map[r][c] == 0) {
				zeroCnt--;
			}
			
			if(zeroCnt == 0) { // 0의 개수가 0이 되면 최소 시간 갱신
				min = Math.min(min, time);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || map[nr][nc] == 1) continue;
				
				
				queue.add(new Virus(nr, nc, time+1));
				visited[nr][nc] = true;
			}
		}
	}
	
	
} // end of class
