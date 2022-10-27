import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_14502_연구소_골4_추준성 {

	static class Virus {
		int r, c;

		public Virus(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static class Empty {
		int r, c;

		public Empty(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<Virus> viruses;
	private static ArrayList<Empty> input;
	private static Empty[] output;
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	private static int max;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		viruses = new ArrayList<>();
		input = new ArrayList<>();
		output = new Empty[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) viruses.add(new Virus(i, j)); // 바이러스 좌표
				else if(map[i][j] == 0) input.add(new Empty(i, j)); // 빈 공간 좌표
			}
		}
		
		max = 0;
		comb(0, 0);
		
		System.out.print(max);
	} // end of main

	private static void comb(int depth, int start) {
		if(depth == 3) { // 기저조건
			int[][] copyMap = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, M);
			}
			
			for (Empty empty : output) {
				copyMap[empty.r][empty.c] = 1; // 벽으로 채우기
			}
			
			max = Math.max(max, diffuse(copyMap));
			
			return; 
		}
		
		for (int i = start; i < input.size(); i++) {
			output[depth] = input.get(i);
			comb(depth+1, i+1);
		}
	}

	private static int diffuse(int[][] copyMap) {
		
		LinkedList<Virus> queue = new LinkedList<>();
		for (Virus virus : viruses) {
			queue.add(new Virus(virus.r, virus.c));
		}
		
		while(!queue.isEmpty()) {
			
			Virus cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || copyMap[nr][nc] != 0) continue;
				
				queue.add(new Virus(nr, nc));
				copyMap[nr][nc] = 2; // 바이러스 퍼짐 (방문체크)
			}
		}

		int safety = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) ++safety;
			}
		}
		
		return safety;
	}

} // end of class
