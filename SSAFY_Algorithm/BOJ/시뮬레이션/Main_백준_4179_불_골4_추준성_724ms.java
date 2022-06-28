import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_4179_불_골4_추준성_724ms {
	/*
	 * <설계>
	 * 1. 지훈(J)이는 '.'인 곳으로만 이동 가능
	 * 2. 불(F)은 벽, 경계밖 빼고 이동 가능
	 * 3. 지훈이가 더이상 이동할 수 없으면 IMPOSSIBLE을 출력 
	 */
	static class Obj implements Comparable<Obj>{
		int r, c, t, tag;

		public Obj(int r, int c, int t, int tag) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
			this.tag = tag;
		}

		@Override
		public int compareTo(Obj o) {
			return this.t - o.t != 0 ? this.t - o.t : o.tag - this.tag; // 시간 순, 시간이 같을 경우 불이 먼저 이동
		}
		
	}
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][];
		PriorityQueue<Obj> pq = new PriorityQueue<>();
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'J') pq.add(new Obj(i, j, 0, 0)); // tag가 0이면 지훈, 1이면 불
				if(map[i][j] == 'F') pq.add(new Obj(i, j, 0, 1));
			}
		}
		
		while(!pq.isEmpty()) {
			Obj obj = pq.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = obj.r + dr[i];
				int nc = obj.c + dc[i];
				
				if(obj.tag == 0) { // 지훈이면
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
						System.out.print(obj.t+1); // 경계 밖으로 나가면 현재 시간 출력 후 시스템 종료
						System.exit(0);
					}
					
					if(map[nr][nc] != '.') continue;
					map[nr][nc] = 'J';
				} else { // 불이면
					if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '#' || map[nr][nc] == 'F') continue;
					map[nr][nc] = 'F';
				}
				
				pq.add(new Obj(nr, nc, obj.t+1, obj.tag));
			}
			
		}
		
		System.out.print("IMPOSSIBLE");
		
	} // end of main
} // end of class
