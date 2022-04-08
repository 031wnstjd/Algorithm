package study.day9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1005_ACMCraft_골3_추준성_1236ms {
	/*
	 * <설계>
	 * 1. 정해진 건설 순서 규칙이 주어짐 => "순서가 정해져 있는 작업을 차례로 수행" => 위상정렬
	 * 2. 간선을 끊고나서 진입차수가 0인 원소들 중 "건설시간이 최대인 원소의 시간만 카운트" (다른 원소들의 시간은 이에 포함되므로 고려 X, 물론 큐에 담기는 함) 
	 * 
	 */
	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물의 개수
			int K = Integer.parseInt(st.nextToken()); // 건설 순서 규칙의 총 개수
			Node[] nodeList = new Node[N+1]; // 건물 순서 정보 (인접 리스트)
			int[] timeList = new int[N+1]; // 건설 시간 정보
			int[] result = new int[N+1]; // 각 건물들을 짓는데 걸리는 시간 정보 저장 (지속적 갱신)
			int[] inDegree = new int[N+1]; // 각 건물들의 진입 차수 정보
					
			// 각 건물 번호에 따른 건설 시간 정보 리스트
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				timeList[i] = Integer.parseInt(st.nextToken());
			}
			
			// 인접 리스트 만들기
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				nodeList[from] = new Node(to, nodeList[from]);
				inDegree[to]++; // 진입 차수 카운트
			}
			
			int W = Integer.parseInt(br.readLine()); // 백준이가 승리하기 위해 건설해야 할 건물의 번호
			
			Queue<Integer> q = new LinkedList<Integer>();
			
			// 1. 진입차수가 0인 건물 찾아서 해당 건물 번호 큐에 담기 (==위상정렬 시작 노드 설정)
			for (int i = 1; i <= N; i++) {
				if(inDegree[i] == 0) {
					q.add(i);
					result[i] = timeList[i]; // 초기 시간 정보 담기
				}
			}
			
			// 2. 위상정렬 (큐에 담긴 진입차수 0인 원소와 인접한 노드들 사이의 간선을 끊음 -> 인접한 노드의 진입차수가 0이 되면 큐에 담음) 
			for (int i = 1; i <= N; i++) {
				int cur = q.poll(); // 진입차수가 0인 건물 큐에서 빼기
				for (Node node = nodeList[cur]; node != null; node = node.link) {
					int adjVertex = node.vertex;
					inDegree[adjVertex]--; // cur에 인접한 노드 간선 끊기
					result[adjVertex] = Math.max(result[adjVertex], result[cur]+timeList[adjVertex]);
					if(inDegree[adjVertex] == 0) q.add(adjVertex);
				}
			}
			
			sb.append(result[W]).append("\n");
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main
	

} // end of class
