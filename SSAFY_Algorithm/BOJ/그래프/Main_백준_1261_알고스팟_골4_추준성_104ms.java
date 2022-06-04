import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_1261_알고스팟_골4_추준성_104ms {
	/*
	 * 기준이 '최단 거리'가 아닌, '벽을 깬 최소 횟수'이므로 pq를 활용해, 벽을 깬 횟수 기준으로 오름차순 정렬
	 */
	static class Node implements Comparable<Node>{
		int r, c, cnt;

		public Node(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt; // 벽을 깬 횟수를 기준으로 오름차순 정렬
		}
	}

	private static int[][] map;
	private static int M;
	private static int N;
	
	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1,-1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 1 <= N, M <= 100
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		System.out.print(bfs());
		
	} // end of main

	private static int bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][M];
		
		pq.add(new Node(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.r == N-1 && cur.c == M-1) return cur.cnt; 
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
				if(map[nr][nc] == 1) {
					pq.add(new Node(nr, nc, cur.cnt + 1));
				} else {
					pq.add(new Node(nr, nc, cur.cnt));
				}
				visited[nr][nc] = true; 
			}
		}
		return 0;
	}
	
} // end of class
