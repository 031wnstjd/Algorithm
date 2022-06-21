import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_20055_컨베이어벨트위의로봇_골5_추준성_288ms {
	private static int[][] belt;
	private static int N;
	private static int K;

	/*
	 * <조건>
	 * 1. 로봇은 올리는 위치에만 올릴 수 있다. (1행 1열)
	 * 2. 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다. (1행 N열)
	 * 3. 로봇은 컨베이어 벨트 위에서 스스로 이동할 수 있다. 
	 * 4. 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소
	 * 
	 * <설계>
	 * 1. 회전 - 2차원 배열 / copy(temp) 배열 활용 (메모리 512MB까지 가능하므로)
	 * 2. 로봇 위치 관리 - 1차원 배열 활용
	 * => 인덱스 활용을 쉽게하기 위해 2차원 배열 하나로 합침
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[3][N];
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++) {
			belt[1][j] = Integer.parseInt(st.nextToken());
		}
		for (int j = N-1; j >= 0; j--) {
			belt[2][j] = Integer.parseInt(st.nextToken());
		}
		
		int step = 0;
		while(true) {
			step++; // 단계 수 증가
			rotate();
			moveRobot();
			putRobot();
			if(!checkValidation()) continue;
			System.out.print(step);
			return;
		}
		
	} // end of main

	private static void rotate() {
		// 1. 로봇 이동
		for (int i = N-1; i > 0; i--) {
			belt[0][i] = belt[0][i-1];
		}
		belt[0][0] = 0; // 0이면 로봇이 없는 것
		belt[0][N-1] = 0; // 마지막 로봇은 즉시 내림
		
		// 2. 컨베이어벨트 이동
		int tmp = belt[1][N-1];
		
		for (int i = N-1; i > 0; i--) {
			belt[1][i] = belt[1][i-1]; // 좌 -> 우
		}
		
		belt[1][0] = belt[2][0]; // 하 -> 상
		
		for (int i = 0; i < N-1; i++) {
			belt[2][i] = belt[2][i+1]; // 우 -> 좌
		}
		
		belt[2][N-1] = tmp; // 상 -> 하
	}

	private static void moveRobot() { 
		for (int i = N-2; i >= 0; i--) { // 가장 먼저 벨트에 올라간 로봇부터
			if(belt[0][i] == 0 || belt[0][i+1] == 1 || belt[1][i+1] < 1) continue; // 현재 칸에 로봇이 없거나, 다음 칸에 로봇이 있거나, 내구도가 0이면  continue
			belt[0][i+1] = 1; // 다음 칸으로 로봇 이동
			belt[1][i+1]--; // 다음 칸 내구도 감소
			belt[0][i] = 0; // 현재칸 로봇 없앰
		}
	}

	private static void putRobot() {
		if(belt[1][0] == 0) return;
		belt[0][0] = 1; // 로봇 올림
		belt[1][0]--; // 내구도 감소
	}

	private static boolean checkValidation() {
		int cnt = 0; // 내구도가 0인 곳의 개수
		for (int i = 1; i <= 2; i++) {
			for (int j = 0; j < N; j++) {
				if(belt[i][j] == 0) cnt++;
			}
		}
		if(cnt >= K) return true;
		return false;
	}

} // end of class
