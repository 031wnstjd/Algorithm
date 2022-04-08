package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_14502_연구소_골5_추준성_344ms {
	private static int N;
	private static int M;
	private static int[][] map;

	/*
	 * 0 : 빈칸
	 * 1 : 벽
	 * 2 : 바이러스
	 * 
	 * <설계>
	 * 1. 목적 : 벽 3개를 세워서 최대한 바이러스가 퍼지지 않게끔 해야함
	 * 2. N*M은 최대 24이므로 24C3 = 2024 --> 시간 충분
	 * 3. 24개의 좌표들 중에서 값이 0인 좌표 3개를 뽑아서 1로 채움
	 * 
	 */
	
	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1,-1};
	private static int[][] output;
	private static ArrayList<int[]> virusList;
	private static int maxCnt = -1;
	private static LinkedList<int[]> queue;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		output = new int[3][2];
		virusList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virusList.add(new int[] {i, j}); // virusList에 바이러스 좌표 담기
			}
		}
		
		setWall(0, 0);
		
		System.out.print(maxCnt);
		
	} // end of main
	
	static void setWall(int depth, int start) {
		if(depth == 3) {
			int[][] copyMap = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
			
			for (int i = 0; i < 3; i++) {
				int r = output[i][0];
				int c = output[i][1];
				copyMap[r][c] = 1; 
			}
			
			queue = new LinkedList<>();
			
			// 바이러스 초기 위치 큐에 담기
			for (int i = 0; i < virusList.size(); i++) {
				int[] virus = virusList.get(i);
				queue.add(virus);
			}
			
			
			// 바이러스 퍼짐
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				int r = cur[0];
				int c = cur[1];
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || copyMap[nr][nc] != 0) continue;
					
					copyMap[nr][nc] = 2;
					queue.add(new int[] {nr, nc});
				}
			}
			
			// 0(안전영역)의 개수 카운트
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(copyMap[i][j] == 0) cnt++;
				}
			}
			
			maxCnt = Math.max(cnt, maxCnt); // 최댓값 갱신
			return;
		}
		
		// 2차원 배열 좌표 조합 코드 (NxM 배열에서 좌표 3개를 뽑음)
		for (int i = start; i < N * M; i++) {
			int r = i / M;
			int c = i % M;
			
			if(map[r][c] == 0) {
				output[depth] = new int[] {r, c};
				setWall(depth+1, i+1);
			}
		}
		
	}
	
} // end of class
