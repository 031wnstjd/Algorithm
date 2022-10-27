import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14889_스타트와링크_실2_추준성_260ms {

	private static int[][] board;
	private static int N;
	private static int[] input;
	private static int[] startTeam;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = i;
		}
		
		startTeam = new int[N >> 1];
		
		min = Integer.MAX_VALUE;
		combination(0, 0);
		
		System.out.print(min);
		
	} // end of main

	private static void combination(int depth, int start) {
		if(depth == N >> 1) {
			
			boolean[] checkList = new boolean[N]; // true는 startTeam, false는 linkTeam
			
			for (int i = 0; i < N/2; i++) {
				checkList[startTeam[i]] = true;
			}
			
			int idx = 0;
			int[] linkTeam = new int[N/2];
			for (int i = 0; i < N; i++) {
				if(checkList[i]) continue;
				linkTeam[idx++] = i; // checkList 값이 false일 때 해당 인덱스 값을 linkTeam에 할당
			}
			
			int startAbility = 0;
			int linkAbility = 0;
			
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					startAbility += board[startTeam[i]][startTeam[j]];
					linkAbility += board[linkTeam[i]][linkTeam[j]];
				}
			}
			
			min = Math.min(min, Math.abs(startAbility - linkAbility));
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			startTeam[depth] = input[i];
			combination(depth+1, i+1);
		}
	}

} // end of class
