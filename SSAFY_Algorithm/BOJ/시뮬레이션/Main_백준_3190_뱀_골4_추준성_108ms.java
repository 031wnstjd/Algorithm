import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_3190_뱀_골4_추준성_108ms {
	/*
	 * 1. 자료구조 : LinkedList
	 * - 뱀의 머리, 몸통 좌표들을 저장
	 * 
	 * 2. 방향전환 함수 : changeDircetion(int dir, char inst)
	 * - 현재 방향(dir)에 대한 좌, 우 90도 지시(inst)의 결과를 반환
	 * => switch ~ case 문 사용
	 */
	
	static class DirInfo {
		int time;
		char direction;
		
		public DirInfo(int time, char direction) {
			super();
			this.time = time;
			this.direction = direction;
		}
	}
	
	private static int N;
	private static int K;
	private static int[][] board;
	private static DirInfo[] dirInfos;
	
	static int[] dr = {0, 1, 0,-1}; // 우하좌상
	static int[] dc = {1, 0,-1, 0}; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		// 사과 놓기
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int ar = Integer.parseInt(st.nextToken()) - 1;
			int ac = Integer.parseInt(st.nextToken()) - 1;
			board[ar][ac] = 1; // 사과
		}
		
		int L = Integer.parseInt(br.readLine());
		dirInfos = new DirInfo[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char direction = st.nextToken().charAt(0) ;
			dirInfos[i] = new DirInfo(time, direction);
		}
		
		System.out.println(game());
		
	} // end of main


	private static int game() {
		
		LinkedList<int[]> snake = new LinkedList<>();
		snake.add(new int[] {0, 0}); // 뱀 초기 위치
		int curDir = 0; // 우
		
		int time = 0;
		while(true) {
			
			time++;
			
			int[] head = snake.peek();
			
			int nextHeadRow = head[0] + dr[curDir];
			int nextHeadCol = head[1] + dc[curDir];
			
			// 벽을 만나면
			if(nextHeadRow < 0 || nextHeadRow >= N || nextHeadCol < 0 || nextHeadCol >= N) 
				break;
			
			// 몸통을 만나면
			for (int[] loc : snake) 
				if(nextHeadRow == loc[0] && nextHeadCol == loc[1]) return time;
			
			// 사과가 있다면
			if(board[nextHeadRow][nextHeadCol] == 1) {
				snake.addFirst(new int[] {nextHeadRow, nextHeadCol});
				board[nextHeadRow][nextHeadCol] = 0; // 사과 먹고나면 없어짐
			} 
			// 사과가 없다면
			else {
				snake.addFirst(new int[] {nextHeadRow, nextHeadCol});
				snake.removeLast(); // 꼬리 없애기
			}
			
			for (DirInfo dirInfo  : dirInfos) {
				if(dirInfo.time != time) continue;
				curDir = changeDirection(curDir, dirInfo.direction);
			}
			
		}
		
		return time;
	}


	private static int changeDirection(int curDir, char direction) {
		
		switch(curDir) {
			case 0: // 우
				if(direction == 'L')
					curDir = 3;
				else
					curDir = 1;
				break;
			case 1: // 하
				if(direction == 'L')
					curDir = 0;
				else
					curDir = 2;
				break;
			case 2: // 좌
				if(direction == 'L')
					curDir = 1;
				else
					curDir = 3;
				break;
			case 3: // 상
				if(direction == 'L')
					curDir = 2;
				else
					curDir = 0;
				break;
		}
		
		return curDir;
	}

	
} // end of class
