import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_1238_파티_골3_추준성_164ms {
	/*
	 * <조건 분석>
	 * 1. 파티에 참석했다가 자기 위치로 '최단거리'로 돌아옴 => BFS / 다익스트라
	 * 2. 구하고자 하는 것 : N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생 소요시간 => 다 돌고 최댓값 갱신 
	 * 3. 정점 수 >> 간선 수 => 인접리스트
	 * 
	 * - 다익스트라 : 한 정점으로부터 모든 정점까지의 최단 거리를 구하는 알고리즘
	 * => 현재는 '모든' 정점들 사이의 관계가 궁금한 게 '아니기' 때문에 X정점을 기준으로 생각!!
	 * => dijkstra(revadjList) : 모든 정점에서 X로 가는 최단 거리 구하기 (입력을 반대로 받은 배열 revadjList를 정의)
	 * => dijkstra(adjList) : X에서 모든 정점에 대한 최단거리 구하기
	 * => revadjList를 정의하는 게 핵심!! (모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.)
	 * => 다익스트라 두 번 돌리면 끝
	 * 
	 */
	static class Node implements Comparable<Node>{
		int vertex;
		int dist;
		public Node(int vertex, int dist) {
			super();
			this.vertex = vertex;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	private static int N;
	private static int X;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] adjList = new ArrayList[N+1];
		ArrayList<Node>[] revadjList = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			revadjList[i] = new ArrayList<>(); // 모든 정점으로부터 X까지의 최단거리를 구하기 위해 정의
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adjList[s].add(new Node(e, t));
			revadjList[e].add(new Node(s, t)); 
		}
		
		int max = 0;
		int[] go = dijkstra(revadjList);
		int[] back = dijkstra(adjList);
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, go[i]+back[i]);
		}
		
		System.out.print(max);
		
	} // end of main

	private static int[] dijkstra(ArrayList<Node>[] list) {
		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		boolean[] checked = new boolean[N+1];
		int[] minDistance = new int[N+1];
		
		Arrays.fill(minDistance, 987654321); // 시작 정점으로부터 각 정점까지의 최단 거리를 최댓값으로 초기화
		minDistance[X] = 0; // 시작 지점은 0으로 초기화
		
		pQueue.add(new Node(X, 0));
		
		while(!pQueue.isEmpty()) {
			Node cur = pQueue.poll();
			
			if(checked[cur.vertex]) continue; // 이미 최단 거리가 정해졌으면 continue;
			checked[cur.vertex] = true; // 시작 정점으로부터 curVertex와 연결된 모든 정점까지의 최단 거리가 정해졌음을 체크

			// 다익스트라 : 시작 정점으로부터 curVertex와 연결되어있는 각 정점들까지의 최단 거리를 업데이트 (curVertex를 경유지로 하는 상황을 고려하여 최단 거리 갱신)
			for (Node next : list[cur.vertex]) {
				if(minDistance[next.vertex] <= next.dist + minDistance[cur.vertex]) continue;
				
				minDistance[next.vertex] = next.dist + minDistance[cur.vertex];
				pQueue.add(new Node(next.vertex, minDistance[next.vertex]));
			}
		}
		
		return minDistance;
	}

} // end of class
