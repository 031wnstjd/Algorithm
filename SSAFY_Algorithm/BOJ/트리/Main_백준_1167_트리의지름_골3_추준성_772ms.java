import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1167_트리의지름_골3_추준성_772ms {
	/*
	 * <조건 분석>
	 * 1. 트리의 지름 : 임의의 두 점 사이의 거리 중 가장 긴 것
	 * 2. V <= 100,000
	 * => 인접 리스트 활용
	 * 
	 * <설계>
	 * 1. 각 정점으로부터 탐색할 수 있는 끝까지 탐색하며 가중치 더한 후, 방문할 수 있는 노드 없으면 최댓값 갱신
	 * => 겹치는 부분 발생 => 시간초과 => 임의의 정점으로부터 가장 멀리 떨어진 정점을 기준으로 dfs 탐색하기
	 * 2. 방문한 정점은 방문처리
	 * 
	 */
	static class Node {
		int vertex;
		int dist;
		public Node(int vertex, int dist) {
			super();
			this.vertex = vertex;
			this.dist = dist;
		}
	}

	private static ArrayList<Node>[] tree;
	private static int N;
	private static int max;
	private static int endVertex;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int vertex = Integer.parseInt(st.nextToken());
				if(vertex == -1) break;
				int dist = Integer.parseInt(st.nextToken());
				tree[v].add(new Node(vertex, dist));
			}
		}
		
		boolean[] visited = new boolean[N+1];
		int sum = 0;
		max = 0;
		endVertex = 0;
		dfs(1, sum, visited); // endVertex 찾기
		Arrays.fill(visited, false); // 초기화
		
		dfs(endVertex, sum, visited); // 최댓값 찾기
		
		System.out.print(max);
		
	} // end of main

	private static void dfs(int v, int sum, boolean[] visited) {
		if(sum > max) {
			max = sum;
			endVertex = v;
		}
		visited[v] = true;
		
		for (int i = 0; i < tree[v].size(); i++) {
			Node node = tree[v].get(i);
			if(visited[node.vertex]) continue;
			dfs(node.vertex, sum+node.dist, visited);
		}
	}

} // end of class
