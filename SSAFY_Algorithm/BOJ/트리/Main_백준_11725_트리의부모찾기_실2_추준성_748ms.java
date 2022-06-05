import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_11725_트리의부모찾기_실2_추준성_748ms {
	/*
	 * - 루트노드는 1로 고정
	 * - 루트노드를 시작으로 연결된 노드들을 bfs로 탐색하여 현재 노드를 탐색 노드의 부모 노드로 parents 배열에 저장
	 * => 그래프 연결 관계와 루트노드가 무엇인지만 알면, 루트노드를 시작으로 계층관계(각 노드의 부모노드가 무엇인지)를 규명할 수 있다.  
	 */
	private static int N;
	private static int[] parents;
	private static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1]; // 각 노드에 연결된 노드들을 저장할 리스트
		parents = new int[N+1]; // 각 노드의 부모노드 번호를 저장할 배열
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 계층적 관계(==트리)가 아니므로 양방향(==graph)으로 저장
			graph[a].add(b);
			graph[b].add(a);
		}
		
		bfs();
		
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		
		System.out.print(sb.toString());
	} // end of main

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		queue.add(1); // 루트노드를 시작으로 탐색
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int next : graph[cur]) {
				if(visited[next]) continue; // 이미 방문한 노드이면 continue
				parents[next] = cur; // 다음(탐색) 노드의 부모노드는 현재노드
				queue.add(next); // 다음 노드를 큐에 넣기
				visited[next] = true; // 방문체크
			}
		}
	}

} // end of class
