import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_2252_줄세우기_골3_추준성_372ms {
	/*
	 * 
	 * - 키 순서대로 줄세워야 하므로(사이클링 발생X) 위상정렬 적용
	 * 
	 * - 위상 정렬 : '순서가 정해져있는 작업'을 차례로 수행해야 할 때 그 순서를 결정해줌
	 * 0. inDegree[N+1] 배열에 각 원소들의 진입 차수 담기 
	 * 1. 진입 차수가 0(시작지점)인 원소들을 큐에 담음
	 * 2. N회 queue에서 원소들을 빼내며 해당 원소에 인접해 있는 원소들의 간선들을 끊어냄 (해당 원소의 inDegree값을 1줄임)
	 * 3. 인접한 원소들의 inDegree 값이 0이면(==진입차수가 0이면) 해당 원소를 큐에 담고 방문처리
	 * 
	 * => "진입 차수가 0인 원소를 시작 정점으로" 잡기 때문에 가능한 알고리즘
	 * 
	 */
	static class Node {
		int vertex; // 노드의 정점 번호
		Node link; // 노드가 가리키고 있는 노드의 정보
		
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[N+1]; // 인접 리스트 (N개의 노드 사이의 연결 관계를 나타냄)
		int[] inDegree = new int[N+1]; // 각 노드의 인접 차수를 담을 배열
		int[] result = new int[N+1]; // 정렬 결과 리스트
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++; // to에 해당하는 학생의 진입차수 증가
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		// 1. 인접차수가 0인 원소를 큐에 담음
		for (int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) q.add(i);
		}
		
		// 2. 큐에서 원소를 꺼낸 다음 해당 원소에 인접한 간선들을 모두 끊음
		for (int i = 1; i <= N; i++) {
			int curVertex = q.poll();
			result[i] = curVertex; // 큐에서 꺼내어 결과 배열(result)에 추가
			
			for (Node node = adjList[curVertex]; node != null; node = node.link) {
				int adjVertex = node.vertex;
				inDegree[adjVertex]--; // 간선 끊기 (진입 차수를 1차감)
				if(inDegree[adjVertex] == 0) {
					q.add(adjVertex); // 진입 차수가 0이면 큐에 담기
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.print(sb.toString());
		
	} // end of main

} // end of class
