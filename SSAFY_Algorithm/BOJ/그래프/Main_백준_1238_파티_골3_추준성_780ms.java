import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_1238_파티_골3_추준성_780ms {
	/*
	 * <조건 분석>
	 * 1. 파티에 참석했다가 자기 위치로 '최단거리'로 돌아옴 => BFS / 다익스트라 => BFS 선택
	 * 2. 구하고자 하는 것 : N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생 소요시간 => 다 돌고 최댓값 갱신 
	 * 3. 정점 수 >> 간선 수 => 인접리스트
	 * => null로 방문처리 - 메모리초과
	 * => 전역에 visited 배열 선언 - 불필요
	 * => Node 객체에 visit 변수를 설정하고, 재탐색 전에 이를 초기화
	 */
	static class Node implements Comparable<Node>{
		int vertex;
		int time;
		boolean visit; // 방문 체크용
		public Node(int vertex, int time) {
			super();
			this.vertex = vertex;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return this.time - o.time; // 시간 오름차순
		}
	}
	private static int N;
	private static int M;
	private static ArrayList<Node>[] adjList;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생수, 1 <= N <= 1,000
		M = Integer.parseInt(st.nextToken()); // 도로개수, 1 <= M <= 10,000
		int X = Integer.parseInt(st.nextToken()); // 도착지 번호, 1 <= X <= N
		
		// 1. 인접리스트 초기화
		adjList = new ArrayList[N+1]; 
		for (int i = 1; i <= N ; i++) {
			adjList[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adjList[s].add(new Node(e, t));
		}
		
		// 2. 오고 가는데 가장 오래 걸리는 학생의 소요시간 찾기
		int max = 0;
		for (int i = 1; i <= N; i++) {
			if(i == X) continue; // 도착지에 있는 학생의 경우 건너 뜀 (어차피 값 0)
			max = Math.max(max, getMinTime(i, X) + getMinTime(X, i));
		}
		
		System.out.print(max);
		
	} // end of main
	private static int getMinTime(int start, int end) {
		
		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		for (int i = 0; i < adjList[start].size(); i++) {
			Node node = adjList[start].get(i);
			pQueue.add(node);
			node.visit = true; // 방문처리
		}
		
		while(!pQueue.isEmpty()) {
			Node cur = pQueue.poll();
			int cv = cur.vertex;
			int ct = cur.time;
			
      // 시작지점으로 돌아오면 값을 리턴
			if(cv == end) {
				for (int i = 1; i <= N; i++) {
					for (Node node : adjList[i]) {
						node.visit = false; // 다음 탐색을 위해 방문체크 초기화
					}
				}
				return ct;
			}
			
			for (int i = 0; i < adjList[cv].size(); i++) {
				Node node = adjList[cv].get(i);
				if(node.visit) continue; // 방문한 노드면 continue;
				int nv = node.vertex;
				int nt = node.time;
				pQueue.add(new Node(nv, nt+ct));
				node.visit = true; // 방문 처리
			}
		}
		
		return 0;
	}

} // end of class
