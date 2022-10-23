import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14499_주사위굴리기_골4_추준성_136ms {

	static int[] dr = {0, 0,-1, 1}; // 동 서 북 남
	static int[] dc = {1,-1, 0, 0};
	private static int[] values;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] directions = new int[K];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			directions[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		/* values의 인덱스 별 의미 (top을 기준으로 한 주사위의 각 면들을 인덱스에 대응) - 고정적(절대좌표계)
		 * 1 : top 
		 * 2 : top의 윗쪽
		 * 3 : top의 오른쪽
		 * 4 : top의 왼쪽
		 * 5 : top의 아랫쪽
		 * 6 : bottom
		 */
		
		values = new int[7]; // 주사위의 각 면(인덱스)에 대응되는 값들을 저장
		
		int bottom = 6; // 바닥면 인덱스
		int top = 1; // 상단면 인덱스
		
		for (int i : directions) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue; // 바깥으로 이동하려는 경우, 해당 명령 무시
			
			moveDice(i); // 주사위 이동 (각 면들의 값 변경)
			
			// 이동한 칸에 쓰여진 수가 0이면 
			if(map[nr][nc] == 0) {
				map[nr][nc] = values[bottom];
			} 
			// 이동한 칸에 쓰여진 수가 0이 아니면
			else {
				values[bottom] = map[nr][nc];
				map[nr][nc] = 0;
			}
			
			r = nr;
			c = nc;
			
			System.out.println(values[top]);
		}
		
		
	} // end of main

	private static void moveDice(int direction) {
		int top = values[1];
		int up = values[2];
		int right = values[3];
		int left = values[4];
		int down = values[5];
		int bottom = values[6];
		
		switch(direction) {
			case 0: // 동
				values[1] = left;
				values[3] = top;
				values[4] = bottom;
				values[6] = right;
				break;
			case 1: // 서
				values[1] = right;
				values[3] = bottom;
				values[4] = top;
				values[6] = left;
				break;
			case 2: // 북
				values[1] = down;
				values[2] = top;
				values[5] = bottom;
				values[6] = up;
				break;
			case 3: // 남
				values[1] = up;
				values[2] = bottom;
				values[5] = top;
				values[6] = down;
				break;
		}
		
		return;
	}

} // end of class
