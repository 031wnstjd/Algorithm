import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_20057_마법사상어와토네이도_골3_추준성_396ms {
	
	// 토네이도 이동 방향
	static int[] dr = { 0, 1, 0,-1}; // 좌 하 우 상
	static int[] dc = {-1, 0, 1, 0};
	
	// 모래 이동 방향
	static int[][][] sandDir = { 
					{{-1, 1, 1}, {1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}, {0, -1}},  // 좌
					{{-1, 1, 1}, {-1, -1, 1}, {0, 1, 7}, {0, -1, 7}, {0, 2, 2}, {0, -2, 2}, {1, 1, 10}, {1, -1, 10}, {2, 0, 5}, {1, 0}}, // 하
					{{-1,-1, 1}, {1,-1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, 1, 10}, {1, 1, 10}, {0, 2, 5}, {0, 1}}, // 우
					{{1, 1, 1}, {1, -1, 1}, {0, 1, 7}, {0, -1, 7}, {0, 2, 2}, {0, -2, 2}, {-1, 1, 10}, {-1, -1, 10}, {-2, 0, 5}, {-1, 0}} // 상
				  };
	private static int[][] map;
	private static int N;
	private static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ans = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(N >> 1, N >> 1);
		
		System.out.print(ans);
	} // end of main

	public static void move(int r, int c) {
		int step = 1; 
		int td = 0;
		while(true) {
			for (int i = 0; i < 2; i++) {
				int dir = td % 4;
				for (int j = 0; j < step; j++) {
					r += dr[dir];
					c += dc[dir];
					disperse(r, c, dir);
					if(r == 0 && c == 0) return;
				}
				td++;
			}
			step++;
		}
	}
		
	public static void disperse(int r, int c, int td) {
		int sandTotal = 0;
		for (int sd = 0; sd < 9; sd++) {
			int nr = r + sandDir[td][sd][0];
			int nc = c + sandDir[td][sd][1];
			int perc = sandDir[td][sd][2];
			int sandSpread = (map[r][c] * perc) / 100;
			sandTotal += sandSpread;
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
				ans += sandSpread;
				continue;
			}
			
			map[nr][nc] += sandSpread;
		}
		
		// alpha
		int alphaR = r + sandDir[td][9][0];
		int alphaC = c + sandDir[td][9][1];
		
		int alpha = map[r][c] - sandTotal;
		
		if(alphaR < 0 || alphaR >= N || alphaC < 0 || alphaC >= N) {
			ans += alpha;
		} else {
			map[alphaR][alphaC] += alpha;
		}
		
		map[r][c] = 0; // 모래 비우기
	}
	
} // end of class
