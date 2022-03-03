import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_백준_16926_배열돌리기1_실2_추준성 {
	private static int[] dr = {0, 1, 0,-1}; // 우하좌상
	private static int[] dc = {1, 0,-1, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
//		1 2 3 4 5 1 N과 M중 작은 값을 2로 나눈 걸 시작 지점의 개수로 봄 
// 		6 7 8 9 1 2 정사각행렬이 아니므로 while문으로 돌려야함
// 		2 3 4 5 6 3 
// 		7 8 9 1 2 4
// 		3 4 5 6 7 5
		
		int groupCnt = Math.min(N, M) / 2;
		// R번의 이동을 반복
		for (int i = 0; i < R; i++) {
			// group의 개수만큼 시작지점을 변경해가며 반복 (N,N)부터 시작
			for (int groupNum = 0; groupNum < groupCnt; groupNum++) {
				int r = groupNum;
				int c = groupNum;
				
				int save = map[r][c]; // 마지막에 넣을 값을 저장
				
				int idx = 0;
				// 네 방향으로 회전
				while(idx < 4) {
					// 해당 idx 방향으로의 다음 원소 저장
					int nr = r + dr[idx];
					int nc = c + dc[idx];
					
					if(nr>=groupNum && nc>=groupNum && nr<N-groupNum && nc<M-groupNum) { // 다음 원소가 범위 밖으로 벗어나지 않거나 숫자를 만난다면
						map[r][c] = map[nr][nc];
						r = nr;
						c = nc;
					} else { // 범위 밖으로 벗어나면
						idx++; // 방향전환
					}
				}
				map[groupNum+1][groupNum] = save; // 마지막 원소는 save를 저장 		
			}
		} // end of for rotation
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	} // end of main
} // end of class
