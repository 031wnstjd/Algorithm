import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14890_경사로_골3_추준성_100ms {
	private static int N;
	private static int L;

	/*
	 * - 총 길의 개수 : 2N - 경사로의 길이 : L - 가능한 길 1) 하나의 행, 열에서 모든 원소의 높이가 모두 같을 때 2) 경사로를
	 * 놓아 길을 만들 수 있을 때
	 * 
	 * <설계> 1. 각 행, 열 별로 탐색 2. 현재 탐색중인 원소보다 1이 더 크거나, 더 작은 원소가 다음에 나오면 길이 L만큼 미리
	 * 탐색(단, L만큼 더했을 때 N을 넘을 순 없음) (해당 원소가 길이 L이상 개수만큼 나오는지) => 미리 탐색한 결과 성립하면 길이
	 * L만큼 뛰어넘은 상태부터 재탐색(j++ 신경쓰기) => 미리 탐색한 결과 성립하지 않으면 다음 행 또는 열로 탐색 이동 => i나 j가
	 * N-1에 무사히 도달하면 지나갈 수 있는 길의 개수 cnt++ 3. 현재 탐색중인 원소와 같거나, 1이 더 크거나, 더 작지 않은 경우
	 * 무조건 다음 행, 열 탐색으로 이동
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		int[][] mapRow = new int[N][N];
		int[][] mapCol = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int height = Integer.parseInt(st.nextToken());
				mapRow[i][j] = height;
				mapCol[j][i] = height;
			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (cntPossibleWay(mapRow[i])) cnt++;
			if (cntPossibleWay(mapCol[i])) cnt++;
		}

		System.out.print(cnt);
	} // end of main

	public static boolean cntPossibleWay(int[] map) {
		boolean[] check = new boolean[N]; // 경사면 설치 유무 확인
		
		for (int j = 0; j < N - 1; j++) {
			// 다음 원소가 탐색 범위를 벗어나거나, 자기 자신과 크기가 2이상 차이나면 다음 행으로
			if (Math.abs(map[j] - map[j + 1]) >= 2) return false;
			
			// 자기 자신보다 1이 더 작은 원소가 다음에 나오면 내리막길
			else if (map[j + 1] == map[j] - 1) {
				// 다음 원소를 포함해, 다음 원소와 똑같은 크기의 원소가 연속적으로 L개 나오는지 체크
				for (int k = 1; k <= L; k++) {
					if (j + k >= N || map[j + k] != map[j + 1] || check[j + k]) return false;// 탐색 범위를 벗어나거나 같은 크기의 원소가 연속적으로 나오지 않거나 이미 경사로가 놓였으면 다음 행으로 탐색 이동
					check[j + k] = true;
				}
			}
			// 자기 자신보다 1이 더 큰 원소가 다음에 나오면 오르막길
			else if (map[j + 1] == map[j] + 1) {
				for (int k = 0; k < L; k++) {
					if (j - k < 0 || map[j - k] != map[j] || check[j - k]) return false;
					check[j - k] = true;
				}
			}
			
		}
		return true;
	}

} // end of class
