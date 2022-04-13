import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_9205_맥주마시면서걸어가기_실1_추준성_92ms {
	
	static class Coor {
		int x;
		int y;

		public Coor(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static int n;
	private static Coor home;
	private static Coor target;
	private static Coor[] stores;
	
	/*
	 * <설계>
	 * 1. 완탐
	 * 2. 현재 위치에서 갈 수 있는 편의점(dist(cur, store) <= 1000) 모두 탐색
	 * 3. bfs 탐색으로 target에 도달할 수 있으면 true, 없으면 false를 반환
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= tc; testCase++) {
			n = Integer.parseInt(br.readLine());
			home = null; // 집
			target = null; // 페스티벌 
			stores = new Coor[n];
			StringTokenizer st = null;
			
			st = new StringTokenizer(br.readLine());
			home = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				stores[i] = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			target = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			if(bfs()) sb.append("happy").append("\n");
			else sb.append("sad").append("\n");
		
			
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main

	
	static boolean bfs() {
		
		Queue<Coor> q = new LinkedList<>();
		boolean[] visited = new boolean[n]; // 편의점 방문 체크용
		
		q.add(home);
		
		while(!q.isEmpty()) {
			
			Coor cur = q.poll();
			
			if(isPossible(dist(cur, target))) return true; // target까지 갈 수 있으면 true 리턴
			
			for (int i = 0; i < n; i++) {
				if(visited[i] || !isPossible(dist(cur, stores[i]))) continue;
				
				q.add(stores[i]);
				visited[i] = true;
			}
		}
		
		return false; // target까지 못 가면 false 리턴
	}
	
	static int dist(Coor a, Coor b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	static boolean isPossible(int dist) {
		if(1000 - dist >= 0) return true;
		return false;
	}
	
} // end of class
