package problems;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_백준_7562_나이트의이동_실2_추준성_520ms {

	/*
	 * 1. 나이트가 이동할 수 있는 8방향 벡터 dr, dc 설정
	 * 2. 탐색한 좌표들을 queue에 담으면서 bfs 돌림 (최솟값을 빠르게 찾아야하므로 너비 우선 탐색인 bfs 활용)
	 * 3. bfs로 방문하지 않은 노드만 탐색
	 * 4. 가장 중요한 것 : 좌표 배열의 크기를 모두 "3"으로 설정 => 현재 좌표까지 이동한 횟수 정보를 저장
	 * - queue 자료형 특성상 담은 좌표까지 이동한 횟수를 구하기 위해 모듈러 연산(%)을 사용해야 하는데, 방문처리한 곳은
	 * - 방문하지 않으므로 8, 8*8, 8*8*8...로 나눈 나머지를 구하는 방식으로 이동한 횟수를 따질 수 없다.
	 * - 따라서 각 좌표에 해당 좌표까지 이동한 횟수의 정보를 별도로 저장하여 관리해야 함. 
	 * => 타겟 좌표를 만났을 때 해당 좌표의 세번째 인덱스에 해당하는 값을 출력하게끔 만들면 해당 좌표까지의 이동 횟수를 출력할 수 있음
 	 */
	
	private static int[] dr = {-1,-2,-2,-1, 1, 2, 2, 1}; // 8방향 벡터 : 좌상1 부터 좌하2까지 시계방향으로 돎
	private static int[] dc = {-2,-1, 1, 2, 2, 1,-1,-2};
	private static int[] temp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<int[]> queue = new LinkedList<>();
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 체스판의 한 변의 길이
			boolean[][] visited = new boolean[N][N]; // 각 좌표의 방문 체크를 할 배열
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " "); 
			int[] start = {Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()), 0}; // 시작 좌표 담음 e.g) [0, 0, 0] 
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " "); 
			int[] target = {Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken())}; // 목표 좌표 담음 e.g) [0, 7]
			
			int cntMove = 0; // 나이트가 목표지점에 도달하기 위한 이동 횟수 저장
			int[] current = new int[3]; // 첫 번째 인덱스 - r좌표, 두 번째 인덱스 - c좌표, 세 번째 인덱스 - 몇 번 움직였는지 저장
			queue.offer(start); // 시작 좌표를 큐에 담음
			visited[start[0]][start[1]] = true; // 시작 좌표 방문 체크
			
			while(!queue.isEmpty()){ // queue가 빌 때까지 반복
				current = queue.poll(); // 현재 위치의 좌표를 current에 반환
				if(current[0] == target[0] && current[1] == target[1]) { // current와 target이 같다면 while문 탈출
					cntMove = current[2];
					break;
				}
				// 8방 탐색할 좌표들을 queue에 담음
				for (int i = 0; i < dr.length; i++) {
					temp = new int[3]; // 탐색한 좌표를 담을 배열 객체를 반복적으로 생성(참조형이므로) (첫 번째 인덱스 - r좌표, 두 번째 인덱스 - c좌표, 세 번째 인덱스 - 몇 번 움직였는지 저장)
					temp[0] = current[0]+dr[i]; // 8방 탐색 좌표를 temp에 할당
					temp[1] = current[1]+dc[i];
					temp[2] = current[2]+1; // 다음 탐색한 좌표까지의 이동 횟수 = 현재 이동횟수 + 1
					
					if(0<=temp[0] && temp[0]<N && 0<=temp[1] && temp[1]<N && !visited[temp[0]][temp[1]]) { // 방문하지 않았을 때만 방문하여 queue에 담음						
						queue.offer(temp); // 탐색한 좌표를 queue에 담음
						visited[temp[0]][temp[1]] = true; // 탐색한 좌표 방문 처리
					}
				}
			}
			sb.append(cntMove).append("\n");
			queue.clear(); // <- 이거 하나 때문에 1시간 날림...
		} // end of tetCase
		System.out.print(sb.toString());
	} // end of main
} // end of class
