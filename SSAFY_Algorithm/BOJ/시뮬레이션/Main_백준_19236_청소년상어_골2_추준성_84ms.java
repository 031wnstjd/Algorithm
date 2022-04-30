import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_백준_19236_청소년상어_골2_추준성_84ms {
	/*
	 * <주요 조건>
	 * 1. 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.
	 * 
	 * - 상어의 이동  => 여러 군데 가능 => 완탐 (각각의 수정된 map 정보를 들고, 상어가 더이상 이동 못할 때까지 dfs)
	 * 
	 */
	static class Fish implements Comparable<Fish>, Cloneable{
		int no;
		int r;
		int c;
		int d;
		public Fish(int no, int r, int c, int d) {
			super();
			this.no = no;
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		public int compareTo(Fish o) {
			return this.no - o.no; // 번호 오름차순 정렬
		}
		@Override
		protected Fish clone() throws CloneNotSupportedException {
			return (Fish)super.clone();
		}
	}
	
	static class Shark {
		int r;
		int c;
		int d;
		int score;
		public Shark(int r, int c, int d, int score) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.score = score;
		}
	}
	
	static int[] dr = {-1,-1, 0, 1, 1, 1, 0,-1};
	static int[] dc = {0,-1,-1,-1, 0, 1, 1, 1};
	private static int N = 4;
	private static int max;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Fish[][] board = new Fish[N][N];
		ArrayList<Fish> fishList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int no = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				Fish fish = new Fish(no, i, j, d);
				fishList.add(fish); // fishList에 추가
				board[i][j] = fish; // board에 추가
			}
		}
		
		Shark shark = new Shark(0, 0, board[0][0].d, board[0][0].no);
		Collections.sort(fishList);
		fishList.remove(board[0][0]);
		board[0][0] = null; // (0, 0) 빈칸 처리
		max = 0;
		moveShark(shark, board, fishList);
		
		System.out.print(max);
	} // end of main

	private static void moveShark(Shark shark, Fish[][] board, ArrayList<Fish> fishList) throws CloneNotSupportedException {
		// 0. 최댓값 갱신
		max = Math.max(max, shark.score);
		
		// 1. 물고기 이동
		moveFish(shark, board, fishList);
		
		// 2. 상어 이동
		int nr = shark.r;
		int nc = shark.c;
		int sd = shark.d;
		while(true) {
			nr += dr[sd];
			nc += dc[sd];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) return; // 경계 밖이면 리턴
			if(board[nr][nc] == null) continue; // 빈 칸이면 다음 칸으로
			
			// 물고기가 존재하면
			Fish feed = board[nr][nc];
			Fish[][] nextBoard = new Fish[N][N];
			ArrayList<Fish> nextFishList = new ArrayList<>();
			
			// fishList 복사
			for (Fish fish : fishList) {
				if(fish.no == feed.no) continue;
				nextFishList.add(fish.clone());
			}
			
			// fishList 정보 nextBoard에 뿌리기
			for (Fish fish : nextFishList) {
				nextBoard[fish.r][fish.c] = fish;
			}
			
			moveShark(new Shark(nr, nc, feed.d, shark.score+feed.no), nextBoard, nextFishList);
		}
	}

	private static void moveFish(Shark shark, Fish[][] board, ArrayList<Fish> fishList) {
		int size = fishList.size();
		for (int i = 0; i < size; i++) {
			Fish cur = fishList.get(i);
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;
			
			while(true) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || (nr == shark.r && nc == shark.c)) { // 경계이거나 상어 위치이면 회전
					d = (d+1)%8; // 반시계 방향 회전
					if(d == cur.d) break; // 제자리로 돌아오면 탈출
				} else { // 빈 칸이거나 상어 위치가 아니면 이동
					if(board[nr][nc] == null) { // 빈칸이면 그냥 이동
						cur.r += dr[d]; // 위치 업데이트
						cur.c += dc[d];
						cur.d = d; // 방향 업데이트
						board[nr][nc] = cur; // board 이동 위치 업데이트
						board[r][c] = null; // board 원래 위치 업데이트
						break;
					} else { // 물고기가 존재하면 위치 swap
						Fish tmp = board[nr][nc];
						swap(cur, tmp); // 객체 위치 정보 swap
						board[nr][nc] = cur; // board 정보도 swap
						board[r][c] = tmp;
						cur.d = d; // 방향 업데이트
						break;
					}
				}
			} // end of while
			
		} // end of for fishList
		
	} // end of method moveFish
	
	static void swap(Fish cur, Fish tmp) {
		int tr = tmp.r;
		int tc = tmp.c;
		tmp.r = cur.r;
		tmp.c = cur.c;
		cur.r = tr;
		cur.c = tc;
	}
	
} // end of class
