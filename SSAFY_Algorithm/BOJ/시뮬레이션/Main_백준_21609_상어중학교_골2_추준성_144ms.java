import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_21609_상어중학교_골2_추준성_144ms {

	/*
	 * 검은색블록 : -1
	 * 무지개블록 : 0
	 * 일반블록 : 1~M
	 * 빈칸 : -2
	 * 
	 * <주요 조건>
	 * 1. 격자에 중력이 작용하면 "검은색 블록을 제외"한 모든 블록이 행의 번호가 큰 칸으로 이동한다
	 * 2. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.
	 * 3. 블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록, 그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.
	 * => 기준 블록은, 그룹의 가장 왼쪽 상단에 있는 블록
	 * 
	 */
	private static int N;
	private static int score;
	private static int[][] map;
	private static ArrayList<int[]> removeList;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = { 0, 1, 0,-1};
	private static boolean[][] visited;
	private static ArrayList<Block> refList;
	private static Queue<Block> queue;
	private static int totalCnt;
	private static int rainbowCnt;
	private static int ref;
	private static int maxTotalCnt;
	
	static class Block implements Comparable<Block>{
		int r;
		int c;
		int totalCnt;
		int rainbowCnt;
		
		public Block(int r, int c, int totalCnt, int rainbowCnt) {
			super();
			this.r = r;
			this.c = c;
			this.totalCnt = totalCnt;
			this.rainbowCnt = rainbowCnt;
		}
		public Block(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Block o) {
			return o.totalCnt - this.totalCnt != 0 ? o.totalCnt - this.totalCnt : 
				   o.rainbowCnt - this.rainbowCnt != 0 ? o.rainbowCnt - this.rainbowCnt : 
				   o.r - this.r != 0 ? o.r - this.r : o.c - this.c; // 내림차순 정렬
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		score = 0;
		while(true) {
			findBiggestGroup();
			if(refList.size() == 0) break; // refList에 아무것도 안 담겼으면 break
			removeBlocks();
			gravity();
			rotate();
			gravity();
		}
		
		System.out.print(score);
	} // end of main

	private static void findBiggestGroup() {
		// 1. 크기가 가장 큰 블록 그룹의 기준 블록들 찾기
		findRefBlocks();
		
		// 1-2.refList에 아무것도 안 담겼으면  return
		if(refList.size() == 0) return; 
		
		// 2. 기준 블록을 행, 열 내림차순으로 정렬
		Collections.sort(refList);
		
		// 3. 첫 번째 기준블록으로 bfs 탐색(makeRemoveList)해서 removeList에 담기
		Block refBlock = refList.get(0);
		maxTotalCnt = refBlock.totalCnt;
		makeRemoveList(refBlock.r, refBlock.c);
	}
	
	private static void findRefBlocks() {
		queue = new LinkedList<>();
		visited = new boolean[N][N]; // bfs용
		refList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] < 1 || visited[i][j]) continue; // 일반블록(기준)이 아니면 continue
				ref = map[i][j];
				resetRainbowVisit(); // 무지개 블록은 visited 초기화
				bfs(i, j);
				if(totalCnt < 2) continue; // 그룹이 아니면 continue
				refList.add(new Block(i, j, totalCnt, rainbowCnt));
			}
		}
	}
	
	private static void bfs(int i, int j) {
		totalCnt = 0;
		rainbowCnt = 0;
		
		queue.add(new Block(i, j));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			Block cur = queue.poll();
			
			int r = cur.r;
			int c = cur.c;
			
			if(map[r][c] == 0) rainbowCnt++;
			totalCnt++;
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				if(map[nr][nc] == 0 || map[nr][nc] == ref) {
					queue.add(new Block(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}
	private static void makeRemoveList(int i, int j) {
		boolean[][] check = new boolean[N][N];
		removeList = new ArrayList<>();
		queue.add(new Block(i, j));
		check[i][j] = true;
		int refValue = map[i][j];
		
		while(!queue.isEmpty()) {
			Block cur = queue.poll();
			
			int r = cur.r;
			int c = cur.c;
			removeList.add(new int[] {r ,c});
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || check[nr][nc]) continue;
				
				if(map[nr][nc] == 0 || map[nr][nc] == refValue) {
					queue.add(new Block(nr, nc));
					check[nr][nc] = true;
				}
			}
		}
	}
	
	private static void resetRainbowVisit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0 && visited[i][j]) visited[i][j] = false;
			}
		}
	}

	private static void removeBlocks() {
		for (int[] block : removeList) {
			map[block[0]][block[1]] = -2; // 빈칸 -2 처리
		}
		score += maxTotalCnt * maxTotalCnt;
	}
	private static void gravity() {
		for (int c = 0; c < N; c++) { // 열
			while(true) {
				boolean isComplete = true;
				for (int r = N-1; r >= 0; r--) {
					if(map[r][c] != -2) continue; // 빈칸이 아니면 continue
					if(r-1 >= 0 && map[r-1][c] >= 0) { // 윗칸이 경계 안이고, 무지개 블록이나 일반 블록이면 swap
						isComplete = false;
						map[r][c] = map[r-1][c]; // 블록 내리기
						map[r-1][c] = -2; // 기존 블록 위치는 빈 칸 처리
					}
				}
				if(isComplete) break;
			}
		}
	}
	private static void rotate() {
		int[][] rotMap = new int[N][N];
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				rotMap[N-1-c][r] = map[r][c];
			}
		}
		map = rotMap;
	}

} // end of class
