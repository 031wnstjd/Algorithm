import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_1753_최단경로_골5_추준성_632ms {
	
	static class Node { // 인접 리스트로 그래프 정보를 입력받기 위함
		int vertex;
		int weight;
		Node link;
		
		public Node(int vertex, int weight, Node link) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
	}
	
	static class Vertex implements Comparable<Vertex> {
		int no, minDistance;

		public Vertex(int no, int minDistance) {
			super();
			this.no = no; // 정점 번호
			this.minDistance = minDistance; // 시작점부터 해당 정점까지의 최단 거리
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDistance - o.minDistance;
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호
		
		// 인접리스트를 활용한 그래프 만들기
		Node[] adjList = new Node[V+1]; // 정점수 크기의 인접 리스트 생성 (Node 객체를 담을 배열) 
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()); // from
			int to = Integer.parseInt(st.nextToken()); // to
			int w = Integer.parseInt(st.nextToken());
			// 유향이므로 방향 존재
			adjList[from] = new Node(to, w, adjList[from]); // 기존의 head가 가리키던 노드의 주솟값을 link로 두는 Node를 만들고, 이 노드의 주솟값을 head가 가리키게 함
		}
		
		// PriorityQueue를 활용하여 Dijkstra 알고리즘 적용 & 시작지점으로부터 각 정점으로의 최단거리 출력
		int[] distance = new int[V+1]; // 출발지에서 자신으로 오는 최소비용
		boolean[] visited = new boolean[V+1]; // 최소비용 확정 여부
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>(); // 시작지점으로부터 거리가 가까운 순으로 PQ내부에서 정렬됨
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0; // 시작점 0으로 (자기 자신으로의 비용은 0)
		pQueue.offer(new Vertex(K, distance[K]));
		
		while(!pQueue.isEmpty()) {
			// 1. 최소비용이 확정되지 않은 정점 중 최소비용의 정점 선택
			Vertex current = pQueue.poll();
			
			if(visited[current.no]) continue; // 방문했으면 넘어감
			visited[current.no] = true;
			
			// 2. 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른 정점의 최소비용을 고려
			for (Node temp = adjList[current.no]; temp != null; temp = temp.link) {
				if(!visited[temp.vertex] && distance[temp.vertex] > distance[current.no] + temp.weight) {
					distance[temp.vertex] = distance[current.no] + temp.weight;
					pQueue.offer(new Vertex(temp.vertex, distance[temp.vertex]));
				}
			}
			
			
		}
		
		// 최단 경로가 존재하지 않으면 "INF"로 출력
		for (int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(distance[i]).append("\n");
		}
		
		System.out.print(sb.toString());
	} // end of main

} // end of class
