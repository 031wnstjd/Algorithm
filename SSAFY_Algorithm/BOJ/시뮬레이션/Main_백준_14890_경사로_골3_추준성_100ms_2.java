package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14890_경사로_골3_추준성_100ms_2 {
	private static int cnt;
	private static int N;
	private static int L;
	private static int[][] map;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		
		count(map);
		count(rotateMap(map));
		System.out.print(cnt);
		
	} // end of main

	private static int[][] rotateMap(int[][] map) {
		int[][] newMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[j][N-1-i] = map[i][j];
			}
		}
		
		return newMap;
	}

	static void count(int[][] map) {
		
		boolean[][] visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) { // next가 범위밖을 넘어가지 않게 인덱스를 N-2까지만 고려
			boolean isCnt = true;
			for (int j = 0; j < N-1; j++) {
				int cur = map[i][j];
				int next = map[i][j+1];
				
				if(cur == next) continue; // 높이가 같으면 continue
				if(Math.abs(cur - next) >= 2) {
					isCnt = false;
					break; // 높이차가 2이상 나면 내부 for문 탈출
				}
				
				// 높이차가 1이 나는 경우
				boolean isPossible = true;
				
				if(cur - next == 1) { // 위에서 아래로
					for (int k = j+1; k <= j+L; k++) {
						// 경사로가 범위 밖으로 벗어나거나 경사로가 바닥과 하나라도 접하지 않으면 경사로를 놓을 수 없음
						if(k >= N || map[i][k] != next || visited[i][k]) {
							isCnt = false;
							isPossible = false;
							break; 
						}
						visited[i][k] = true;
					}
					
					if(!isPossible) break;
					
					j += L-1; // 경사로 마지막 지점으로
				} 
				else { // 아래에서 위로
					for (int k = j-L+1; k <= j; k++) {
						// 경사로가 범위 밖으로 벗어나거나 경사로가 바닥과 하나라도 접하지 않으면 경사로를 놓을 수 없음
						if(k < 0 || map[i][k] != cur || visited[i][k]) {
							isCnt = false;
							isPossible = false;
							break; 
						}
						visited[i][k] = true;
					}
					
					if(!isPossible) break;
				}
			}
			
			if(isCnt) cnt++;
		
		}
	}
} // end of class
