import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_11779_최소비용구하기2_골3_추준성_336ms {
	/*
	 * - 시작 지점으로부터 도착 지점까지의 최소비용 => 다익스트라 
	 * - 시작 지점으로부터 도착 지점까지의 경로?
	 * => 시작 지점으로부터 도착지점까지의 최소 비용이 결정될 때까지 save 리스트에 정점 번호 저장
	 */
	static class Node implements Comparable<Node>{
		int no, cost;
		public Node(int no, int cost) {
			super();
			this.no = no;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost; // 최소 비용 기준 오름차순
		}
	}

	private static int N;
	private static int[][] adjMatrix;
	private static int[] minCost;
	private static int[] path;
	private static ArrayList<Integer> paths;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 1 <= N <= 1,000
		int M = Integer.parseInt(br.readLine()); // 1 <= M <= 100,000 
		adjMatrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(adjMatrix[i], Integer.MAX_VALUE); // 최댓값으로 초기화 (비용이 0이 될 수가 있음)
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			adjMatrix[s][e] = Math.min(adjMatrix[s][e], cost); // cost는 더 작은 값으로 계속 업데이트
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		
		minCost = new int[N]; // 다익스트라 최소 비용 갱신용
		Arrays.fill(minCost, Integer.MAX_VALUE); // 최댓값으로 초기화
		minCost[start] = 0; // 시작지점 비용은 0으로 초기화
		
		path = new int[N]; // 경로 역추적용 배열 (이전 방문 정점 번호를 저장)
		paths = new ArrayList<>(); // 경로 출력용 리스트
		
		dijkstra(start, end);
		
		// 다익스트라 경로 역추적
		int cur = end;
		while(cur != start) {
			paths.add(cur);
			cur = path[cur];
		}
		paths.add(start);
		
		sb.append(minCost[end]).append("\n").append(paths.size()).append("\n");
		for (int i = paths.size()-1; i >= 0; i--) {
			sb.append(paths.get(i)+1).append(" ");
		}
		System.out.print(sb.toString());
		
	} // end of main

	private static void dijkstra(int start, int end) {
		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		boolean[] checked = new boolean[N];
		
		pQueue.add(new Node(start, minCost[start]));
		
		while(!pQueue.isEmpty()) {
			Node cur = pQueue.poll();
			
			if(checked[cur.no]) continue; // 이미 최소비용 결정됐으면 continue
			checked[cur.no] = true; // 최소비용 확정 체크
			if(cur.no == end) return; // 도착지점이면 리턴
				
			for (int i = 0; i < N; i++) {
				if(!checked[i] && adjMatrix[cur.no][i] != Integer.MAX_VALUE && minCost[i] > minCost[cur.no] + adjMatrix[cur.no][i]) { // 최소비용 결정 안됐고, 현재노드와 연결되어 있고, 경유지를 거친 비용이 기존 최소비용보다 더 작다면 
					minCost[i] = minCost[cur.no] + adjMatrix[cur.no][i];
					pQueue.add(new Node(i, minCost[i]));
					path[i] = cur.no; // 현재 방문 정점 번호를 저장
				}
			}
		}
	}

} // end of class
