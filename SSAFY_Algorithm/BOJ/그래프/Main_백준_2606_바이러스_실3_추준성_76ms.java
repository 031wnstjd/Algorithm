import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2606_바이러스_실3_추준성_76ms {

	private static int cnt;
	private static boolean[] visited;
	private static int[][] adjMatrix;
	private static int N;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			adjMatrix[from][to] = adjMatrix[to][from] = 1; // 1 : 연결 O, 0 : 연결 X
		}
		
		visited = new boolean[N];
		cnt = -1; // 1번 컴퓨터는 cnt에서 제외하므로 cnt를 -1로 초기화
		
		dfs(0);
		
		System.out.print(cnt);
		
	} // end of main

	private static void dfs(int start) {
		
		visited[start] = true;
		cnt++;
		
		for (int j = 0; j < N; j++) {
			if(adjMatrix[start][j] == 0 || visited[j]) continue; // 연결 안 됐거나 이미 방문했으면 continue
			dfs(j); // 연결됐으면 dfs 탐색
		}
		
		return;
	}

} // end of class
