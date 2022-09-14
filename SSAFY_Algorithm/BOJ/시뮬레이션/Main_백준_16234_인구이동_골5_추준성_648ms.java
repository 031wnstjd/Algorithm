import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_16234_인구이동_골5_추준성_648ms {
	private static boolean[][] visited;
	private static int[][] map;
	private static int N;
	private static int L;
	private static int R;
	private static int days;
	private static boolean isMove;
	private static ArrayList<Node> list;
	
	/*
	 * 리스트를 통해 좌표를 저장(기억)하고 이에 값을 할당하는 게 중요한 문제
	 */
	
	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 <= N <= 50
		L = Integer.parseInt(st.nextToken()); // 1 <= L <= R <= 100
		R = Integer.parseInt(st.nextToken()); 
		
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		days = 0;
		while(true) { // 인구이동 끝날 때까지 반복
			visited = new boolean[N][N]; // 초기화
			isMove = false; // 인구이동 했는지
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) continue; // 이미 방문(연합)했으면 continue
					move(bfs(i, j));
				}
			}
			days++;
			if(!isMove) break; // 인구이동 안 했으면 break
		}
		
		System.out.print(--days);
		
	} // end of main

	private static void move(int avg) {
		for (Node node : list) {
			map[node.r][node.c] = avg; // 저장한 좌표들에 평균 인구수 할당
		}
	}

	private static int bfs(int sr, int sc) {
		Queue<Node> queue = new LinkedList<>();
		list = new ArrayList<>(); // 연합한 영역 좌표 기억할 리스트
		
		queue.add(new Node(sr, sc));
		visited[sr][sc] = true;
		int res = 0; // 인구수 총합
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			list.add(cur); // 연합에 포함되는 곳 리스트에 추가
			res += map[cur.r][cur.c];
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
				
				int diff = Math.abs(map[cur.r][cur.c] - map[nr][nc]);
				if(diff < L || diff > R) continue;
				
				queue.add(new Node(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		if(list.size() == 1) visited[sr][sc] = false; // 인접 연합 없다면 방문 초기화
		else isMove = true;
		
		return res / list.size();
	}


} // end of class
