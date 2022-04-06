import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_백준_2239_스도쿠_골4_추준성_844ms {

	/*
	 * <조건> 
	 * 1. 보드 크기는 9x9로 고정 
	 * 2. 9분할된 영역(3x3)에서 원소들의 합 = 45로 고정(1,2,3,4,5,6,7,8,9)
	 * 3. 각 행, 열 또한 원소들의 합 = 45로 고정(1,2,3,4,5,6,7,8,9) 
	 * 4. 답이 여러가지면 사전식으로 81자리의 수가 최소가 돼야함
	 * 
	 * <설계> 
	 * - 경우의수 : 9*9*9*9*9*9 = 531,441 
	 * - (r,c)가 속한 같은 행과 열에 존재하는 숫자는 선택에서 제외 
	 * -
	 * (r,c)가 9개의 영역 중 어디에 속하는지에 따라 검사 범위가 정해짐
	 * 
	 * <아이디어> 
	 * - 완탐 => 잘못 됐을 경우에 다시 되돌아와야 함 => DFS(백트래킹)
	 * - board[r][c] == 0인 것만 따로 뽑아서 list로 만듦
	 */

	private static int size;
	private static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[9][9];
		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = s.charAt(j) - '0';
				if (board[i][j] == 0)
					list.add(new int[] { i, j });
			}
		}

		size = list.size();

		dfs(0, list);

	} // end of main

	static void dfs(int depth, ArrayList<int[]> list) {
		// 기저 조건
		if (depth == size) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		int r = list.get(depth)[0];
		int c = list.get(depth)[1];

		// 각 행, 열, 영역에 존재하는 숫자가 무엇인지 체크
		boolean[] present = new boolean[10];
		for (int i = 0; i < 9; i++) {
			// 행 체크
			if (board[r][i] != 0)
				present[board[r][i]] = true;
			// 열 체크
			if (board[i][c] != 0)
				present[board[i][c]] = true;
			// 영역 체크
			int startR = (r / 3) * 3;
			int startC = (c / 3) * 3;
			for (int j = startR; j < startR + 3; j++) {
				for (int k = startC; k < startC + 3; k++) {
					if (board[j][k] != 0)
						present[board[j][k]] = true;
				}
			}
		}

		// board[r][c] 안 채워져 있으면 행, 열, 영역에 포함되지 않는 수 중 가장 작은 걸 먼저 선택 후, 다음 재귀로
		for (int i = 1; i <= 9; i++) {
			if (!present[i]) {
				board[r][c] = i;
				dfs(depth + 1, list);
				board[r][c] = 0;
			}
		}
	}

} // end of class
