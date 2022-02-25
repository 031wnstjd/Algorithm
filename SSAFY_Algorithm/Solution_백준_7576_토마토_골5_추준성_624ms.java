import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_백준_7576_토마토_골5_추준성_624ms {
	
	static class Node {
		int r;
		int c;
		
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	private static int dr[] = {1,-1, 0, 0};
	private static int dc[] = {0, 0, 1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		LinkedList<Node> queue = new LinkedList<>();
		int[][] map = new int[M][N];
		boolean flag = false;
		// 입력 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					queue.offer(new Node(i,j)); // 익은 토마토 위치를 queue에 담음
				}
				if(map[i][j] == 0) { // 익지 않은 토마토가 있으면 true
					flag = true;
				}
			}
		}
		
		
		if(!flag) {
			System.out.print(0); // 모든 토마토가 익은 상황이면 0을 출력
			return;
		}
		
		int days = 0;
		while(!queue.isEmpty()) {
			int qSize = queue.size();
			while(qSize-- > 0) {
				Node current = queue.poll(); // 현재 노드
				for (int i = 0; i < 4; i++) {
					int nr = current.r + dr[i];
					int nc = current.c + dc[i];
					if(0<=nr && nr<M && 0<=nc && nc<N && map[nr][nc] == 0) {
						queue.offer(new Node(nr,nc));
						map[nr][nc] = 1; // 방문처리(익음)
					}
				}		
			}
			if(!queue.isEmpty()) days++;
		}
		
		// 안 익은 토마토가 있는지 확인
		boolean flag2 = false;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) {
					flag2 = true;
				}
			}
		}
		
		if(flag2) {
			System.out.print(-1); // bfs를 돌렸는데도 불구하고 안 익은 토마토가 존재하면 -1 출력
		} else {
			System.out.print(days);
		}
		
	} // end of main

} // end of class
