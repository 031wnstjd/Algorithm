import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_12851_숨바꼭질2_골5_추준성 {
	/*
	 * 1. N == K 이면 끝
	 * 2. N > K 이면 걷기
	 * 3. N < K 이면 순간이동 OR 걷기
	 * => 현재 위치에서 순간이동(단, 순간이동은 N!=0일 때만) or 걷기 후, 이동 후 위치를 queue에 담음 (queue가 빌 때까지 반복 - BFS)
	 * 	  - 만약 이동 후 위치(==큐에서 꺼낸 것)가 K보다 크다면 K에 도달할 때까지 걷기
	 * 	  - 만약 이동 후 위치(==큐에서 꺼낸 것)가 K보다 작다면 K에 도달할 때까지 순간이동 or 걷기
	 */
	
	/*
	 * 백트래킹 미적용시 : 344ms
	 * 백트래킹 적용시 : 196ms
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		LinkedList<Integer> queue = new LinkedList<>();
		int time = 0; // 시간
		int cnt = 0; // 방법의 수
		int min = Integer.MAX_VALUE;
		int current;
		boolean[] visited = new boolean[200001];
		
		queue.offer(N); // 초기 위치
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				current = queue.poll();
				visited[current] = true;
				if(current == K) { // 현재 위치가 K이면 최소 시간 갱신
					if(time == min) cnt++; 
					if(time < min) {
						min = time; // 최소시간 갱신
						cnt = 1; // 최소시간 보다 작으면 방법의 수 1로 초기화
					}
				}
				if(K < current && current < 200001 && time < min) { // 갱신된 최소 시간보다 작아야 함 (백트래킹)
					// 걷기
					if(!visited[current-1]) queue.offer(current-1);
				}
				if(0 <= current && current < K && time < min) { // 갱신된 최소 시간보다 작아야 함 (백트래킹)
					// 순간이동 or 걷기
					if(current!=0) {
						if(!visited[2*current]) queue.offer(2*current);
						if(!visited[current-1]) queue.offer(current-1);
					}
					if(!visited[current+1]) queue.offer(current+1);
				}
			}
			time++;
		}
		
		System.out.println(min);
		System.out.println(cnt);
	} // end of main

} // end of class
