import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_정올_1681_해밀턴순환회로_추준성_138ms {

	public static boolean[] visited;
	public static int answer;
	private static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		// 인접행렬 입력 받기
		int[][] adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 
		visited = new boolean[N];
		visited[0] = true; // 시작 지점 : 0 (방문처리) 
		answer = Integer.MAX_VALUE;
		perm(1, 0, 0, adjMatrix); // 다 돌고 나면 answer에 최소 비용이 담김 (첫 번째는 선택됐으므로 depth == 1부터 시작)
		System.out.print(answer);
		
	} // end of main
	public static void perm(int depth, int current, int sum, int[][] adjMatrix) {
		if(depth == N) {
			if(adjMatrix[current][0] == 0) return; // current에서 시작점으로 돌아올 수 없으면 리턴
			answer = Math.min(answer, sum + adjMatrix[current][0]); // 기존의 최소 비용과, 현재 지점으로부터 시작점까지 도달하는 경우를 더한 것 사이의 비교 => 최소 비용 갱신
			return;
		}
		
		for (int i = 1; i < N; i++) { // i=0은 항상 시작지점이므로 i=1부터
			if(!visited[i] && adjMatrix[current][i]!=0 && sum+adjMatrix[current][i] < answer) { // i를 방문하지 않았고, 현재 정점과 연결되어 있다면, 그리고 갱신되어있는 최소 비용보다 현재까지 비용 합이 더 작으면
				visited[i] = true; // 방문처리
				perm(depth+1, i, sum+adjMatrix[current][i], adjMatrix);
				visited[i] = false;
			}
		}
		
	} // end of method perm
} // end of class
