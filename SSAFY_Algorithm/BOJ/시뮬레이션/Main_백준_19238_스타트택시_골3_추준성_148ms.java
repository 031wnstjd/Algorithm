import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;l;l
import java.util.StringTokenizer;

public class Main_백준_19238_스타트택시_골3_추준성_148ms {
	
	static class Nearest implements Comparable<Nearest> {
		int sr, sc, fuel; // 시작지점, 연료량

		public Nearest(int sr, int sc, int fuel) {
			super();
			this.sr = sr;
			this.sc = sc;
			this.fuel = fuel;
		}

		@Override
		public int compareTo(Nearest o) {
			return this.sr - o.sr != 0 ? this.sr - o.sr : this.sc - o.sc;
		}
	}
	
	static class Customer implements Comparable<Customer> {
		int sr, sc, ar, ac; // 시작지점, 도착지점

		public Customer(int sr, int sc, int ar, int ac) {
			super();
			this.sr = sr;
			this.sc = sc;
			this.ar = ar;
			this.ac = ac;
		}

		@Override
		public int compareTo(Customer o) {
			return this.sr - o.sr != 0 ? this.sr - o.sr : this.sc - o.sc;
		}
		
	}
	
	static class Driver {
		int r, c, fuel;

		public Driver(int r, int c, int fuel) {
			super();
			this.r = r;
			this.c = c;
			this.fuel = fuel;
		}

	}

	private static int fuel;
	private static int N;
	private static int M;
	private static Customer[] list;
	private static Driver driver;
	private static int[][] map;
	
	static int[] dr = {-1, 1, 0, 0}; 
	static int[] dc = { 0, 0,-1, 1};
	private static int idx;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 영역 길이
		M = Integer.parseInt(st.nextToken()); // 승객 수
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) - 2; // -2 : 길, -1 : 벽
			}
		}
		
		st = new StringTokenizer(br.readLine());
		driver = new Driver(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, fuel);
		
		list = new Customer[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int ar = Integer.parseInt(st.nextToken()) - 1;
			int ac = Integer.parseInt(st.nextToken()) - 1;
			
			map[sr][sc] = i; // 승객 번호 (list 인덱스에 대응 - O(1)로 승객 정보 조회 가능)
			list[i] = new Customer(sr, sc, ar, ac);
		}
		
		while(true) { // 모든 손님을 이동시키거나 연료를 다 쓸때까지 반복
			findCustomer();
			bringCustomer();
			if(M <= 0) break;
		}
		
		System.out.print(driver.fuel);
		
	} // end of main

	private static void findCustomer() {
		
		// 현재위치에 승객이 있다면
		if(map[driver.r][driver.c] > -1) {
			idx = map[driver.r][driver.c];
			map[driver.r][driver.c] = -2; // 탄 승객 위치는 길로 만듦
		} 
		// 현재 위치에 승객이 없다면
		else { 
			Queue<Driver> queue = new LinkedList<>();
			PriorityQueue<Nearest> pQueue = new PriorityQueue<>();
			boolean[][] visited = new boolean[N][N];
			queue.add(new Driver(driver.r, driver.c, driver.fuel));
			boolean isFound = false;
			visited[driver.r][driver.c] = true;
			
			
			while(!queue.isEmpty()) {
				int qSize = queue.size();
				while(qSize-- > 0) {
					Driver cur = queue.poll();
					
					if(cur.fuel <= 0) { // 도중에 연료 다 떨어졌으면 -1 출력하고 시스템 종료
						System.out.println(-1);
						System.exit(0);
					}
					
					for (int i = 0; i < 4; i++) {
						int nr = cur.r + dr[i];
						int nc = cur.c + dc[i];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == -1 || visited[nr][nc]) continue;
						
						if(map[nr][nc] != -2) { // 승객을 만난다면 태움
							pQueue.add(new Nearest(nr, nc, cur.fuel-1));
							isFound = true;
						}
						
						queue.add(new Driver(nr, nc, cur.fuel-1));
						visited[nr][nc] = true;
					}
				}
				
				if(isFound) break;
			}
			
			if(!pQueue.isEmpty()) { // 승객이 탔다면
				Nearest nearest = pQueue.poll();
				driver = new Driver(nearest.sr, nearest.sc, nearest.fuel);
				idx = map[nearest.sr][nearest.sc];
				map[nearest.sr][nearest.sc] = -2; // 탄 승객 위치는 길로 만듦
			} else {
				System.out.print(-1);
				System.exit(0);
			}
		}
		
	} // end of method findCustomer()

	private static void bringCustomer() {
		Queue<Driver> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.add(new Driver(driver.r, driver.c, driver.fuel));
		visited[driver.r][driver.c] = true;
		boolean isSuccess = false;
		int dist = 0;
		
here:	while(!queue.isEmpty()) {
			int qSize = queue.size();
			while(qSize-- > 0) {
				Driver cur = queue.poll();
				
				if(cur.fuel <= 0) { // 도중에 연료 다 떨어졌으면 -1 출력하고 시스템 종료
					System.out.println(-1);
					System.exit(0);
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == -1 || visited[nr][nc]) continue;
					
					if(nr == list[idx].ar && nc == list[idx].ac) { 
						driver = new Driver(nr, nc, cur.fuel-1+(dist+1)*2); // 목적지에 도착하면 연료 2배로 증가
						isSuccess = true;
						--M;
						break here;
					}
					
					queue.add(new Driver(nr, nc, cur.fuel-1));
					visited[nr][nc] = true;
				}
			}
			dist++;
		}
		
		if(!isSuccess) {
			System.out.print(-1);
			System.exit(0);
		}
	}
	
} // end of class
