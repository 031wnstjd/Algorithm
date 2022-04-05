package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_1600_말이되고픈원숭이_골4_추준성_568ms {
	/*
	 * 1. (K가 0이 아니고) 말 탐색으로 이동이 가능하면 말 탐색으로 이동 
	 * 2. (K가 0이거나) 말 탐색으로 이동이 불가능하면 4방탐색으로 이동 
	 * 3. 1,2를 반복하며 현재위치가 (W-1,H-1)이 됐을 때 동작수 출력하고 while문 탈출 4. while문을 다 돌고나서도 시스템이 종료가 안됐으면 -1을 출력
	 */
	static int[] dr = { 0, 1, 0, -1 }; // 우하좌상
	static int[] dc = { 1, 0, -1, 0 };

	static int[] hdr = { 2, 1,-2,-1, 2, 1,-2,-1}; // 우하, 우상, 좌하, 좌상
	static int[] hdc = { 1, 2, 1, 2,-1,-2,-1,-2};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] map = new int[H][W];
		boolean[][][] visited = new boolean[H][W][K+1]; // visited[r][c][k] : "(r,c) 좌표에 말 탐색 횟수 k를 남기고 도달할 수 있는 최소 횟수" (bfs의 인자값들은 '상태' 정보를 나타냄)
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// bfs 탐색
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0, K, 0 }); // 좌표, K값, 동작수 정보
		visited[0][0][K] = true;
		
		while (!queue.isEmpty()) {
				
			int[] cur = queue.poll();
			
			if (cur[0] == H - 1 && cur[1] == W - 1) {
				System.out.print(cur[3]);
				System.exit(0);
			}
			
			int curK = cur[2];
			int curMove = cur[3];
			
			// 4방 탐색
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1 || visited[nr][nc][curK]) continue;
				
				queue.add(new int[] { nr, nc, curK, curMove + 1 });
				visited[nr][nc][curK] = true;
			}
			if (cur[2] == 0) continue;
			
			// K가 0이 아니면 말탐색 이동도 시도
			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + hdr[i];
				int nc = cur[1] + hdc[i];
				
				if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1 || visited[nr][nc][curK-1]) continue;
				
				queue.add(new int[] { nr, nc, curK - 1, curMove + 1 });
				visited[nr][nc][curK-1] = true;
			}
			
		
		}

		System.out.println(-1);
	} // end of main

} // end of class
