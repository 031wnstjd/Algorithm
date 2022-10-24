import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14500_테트로미노_골4_추준성_672ms {
	private static int N;
	private static int M;
	private static int[][] board;
	private static int max;

	static int[] dr = {0,-1, 0, 1, 0,-1}; // 현상우하좌
	static int[] dc = {0, 0, 1, 0,-1, 1}; // 현상우하좌
	
	static class Tetromino {
		int[] dir;

		public Tetromino(int[] dir) {
			super();
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		game();
		for (int i = 0; i < 3; i++) {
			board = rotate(); // board 회전
			game();
		}
		
		System.out.print(max);
		
	} // end of main

	private static int[][] rotate() {
		int[][] newBoard;
		
		// board가  N * M인 경우
		if(board.length == N) {
			newBoard = new int[M][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					newBoard[j][N-1-i] = board[i][j];
				}
			}
		} 
		// board가 M * N인 경우
		else {
			newBoard = new int[N][M];
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					newBoard[j][M-1-i] = board[i][j];
				}
			}
			
		}

		return newBoard;
	}

	private static void game() {
		
		Tetromino[] tetrominos = new Tetromino[8];
		
		// 대칭 고려해서 총 8개 도형
		tetrominos[0] = new Tetromino(new int[] {0, 2, 2, 2});
		tetrominos[1] = new Tetromino(new int[] {0, 3, 3, 3});
		tetrominos[2] = new Tetromino(new int[] {0, 2, 3, 4});
		tetrominos[3] = new Tetromino(new int[] {0, 3, 3, 2});
		tetrominos[4] = new Tetromino(new int[] {0, 3, 3, 4});
		tetrominos[5] = new Tetromino(new int[] {0, 3, 2, 3});
		tetrominos[6] = new Tetromino(new int[] {0, 3, 4, 3});
		tetrominos[7] = new Tetromino(new int[] {0, 2, 3, 5});
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				for (Tetromino tetromino : tetrominos) {
					int nr = i;
					int nc = j;
					int sum = 0;
					for (int k = 0; k < 4; k++) {
						nr += dr[tetromino.dir[k]];
						nc += dc[tetromino.dir[k]];
						
						if(nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) break;
						
						sum += board[nr][nc];
					}
					max = Math.max(max, sum);
				}
			}
		}
	}

} // end of class
