import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1197_최소스패닝트리_골4_추준성 {
	/*
	 * - 최소신장트리(MST, Minimum Spanning Tree) => 크루스칼 알고리즘, 프림 알고리즘
	 * - 정점의 개수^2 >> 간선의 개수 => 희소그래프 => 크루스칼 알고리즘
	 * 
	 * <크루스칼 알고리즘>
	 * 0. 정의 : 탐욕적인 기법을 통해 네트워크 내의 모든 정점을 최소 비용으로 연결한 신장트리를 구하는 방법
	 * 1. 간선리스트를 가중치 기준으로 오름차순 정렬
	 * 2. 사이클이 발생하지 않는 조건하에 간선의 개수가 N-1이 될 때까지 순차적으로 정점을 집합에 추가하여 신장트리를 구함(== Union-Find)  
	 * 
	 */
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	private static int[] parents;
	
	// 1. 단위집합 생성
	static void makeSet(int n) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i; // 자기 자신을 부모로 삼게끔 초기화
		}
	}
	
	// 2. a의 대표자 찾기
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	// 3. a, b 두 집합 합치기 (같은 대표자이면 안 합침)
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot; // 왼쪽 원소의 대표자의 부모를 오른쪽 원소의 대표자로 두면서 두 집합을 합침
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		Edge[] edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList); // 탐욕적 기법을 활용하기 위해 가중치를 기준으로 오름차순 정렬
		makeSet(V);
		
		int result = 0;
		for (Edge edge : edgeList) {
			if(union(edge.from, edge.to)) { // 사이클이 발생하지 않으면 (== 두 정점이 서로 다른 집합이면)
				result += edge.weight; // 가중치 누적
			}
		}
		
		System.out.print(result);
	
	} // end of main
	
} // end of class
