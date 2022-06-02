import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_1916_최소비용구하기_골5_추준성_340ms {

	/*
	 * - 다익스트라 : 시작 정점으로부터 다른 모든 정점까지의 최단거리를 구함 (단, 가중치 양수) 
	 * - 시간복잡도 : O(V^2+E)
	 * - 간선 수 많음(1 <= M <= 100,000) => 인접행렬 사용
	 * - 한 지점에서 다른 지점으로 가는 버스가 여러 대일 수 있음 => 최솟값으로 유지 또는 갱신 필요
	 */
	
	static class Node implements Comparable<Node>{
		int no; // 정점 번호
		int minCost; // 시작지점으로부터의 비용 (pQueue 정렬용으로 필요)
		public Node(int no, int minCost) {
			super();
			this.no = no;
			this.minCost = minCost;
		}
		@Override
		public int compareTo(Node o) {
			return this.minCost - o.minCost; // pQueue 정렬용
		}
	}
	
	private static int[] minCost;
	private static int[][] adjMatrix;
	private static int N;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(adjMatrix[i], Integer.MAX_VALUE); // 최댓값으로 초기화 (거리가 아니라 비용이므로 0이 가능하기 때문)
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			adjMatrix[s][e] = Math.min(adjMatrix[s][e], cost);
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken())-1; // 시작지점 
		int end = Integer.parseInt(st.nextToken())-1; // 도착지점
		
		minCost = new int[N]; // 각 정점까지의 최소비용 저장 및 갱신용 배열
		Arrays.fill(minCost, Integer.MAX_VALUE); // 최댓값으로 초기화
		minCost[start] = 0; // 시작지점은 0으로 초기화
		
		dikjstra(start, end); // 시작정점으로부터 모든 정점까지의 최소비용 갱신
		
		System.out.print(minCost[end]);
		
	} // end of main

	private static void dikjstra(int start, int end) {
		
		PriorityQueue<Node> pQueue = new PriorityQueue<>(); // 우선순위 큐 - 시작지점으로부터의 비용이 가장 작은 Node를 뽑기 위함 (== 최소비용 확정할 놈을 뽑기 위함)
		boolean[] checked = new boolean[N]; // 각 정점 최소비용 확정 여부 체크 배열
		
		pQueue.add(new Node(start, minCost[start]));
		
		while(!pQueue.isEmpty()) {
			Node cur = pQueue.poll();
			
			if(checked[cur.no]) continue; // 이미 최소 비용이 확정된 상태라면 다음 걸 pQueue에서 뽑음
			checked[cur.no] = true; // 최소 비용 확정
			if(cur.no == end) return; // 목적지까지의 최소비용이 확정됐다면 리턴
			
			for (int i = 0; i < N; i++) {
				if(!checked[i] && adjMatrix[cur.no][i] != Integer.MAX_VALUE && minCost[i] > minCost[cur.no] + adjMatrix[cur.no][i]) { // 최소비용이 확정되지 않은 정점이고, cur 노드와 연결된 정점이며, cur 노드를 경유하는 게 기존 최소 비용보다 더 작은 경우
					minCost[i] = minCost[cur.no] + adjMatrix[cur.no][i];
					pQueue.add(new Node(i, minCost[i]));
				}
			}
		}
	}

} // end of class
