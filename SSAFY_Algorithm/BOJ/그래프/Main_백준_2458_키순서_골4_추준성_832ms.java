package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_2458_키순서_골4_추준성_832ms {
	/*
	 * <조건 분석> - 자기 자신을 기준으로 크고 작음이 명확히 이분법적으로 분리되어야 자신의 키가 몇번째인지 알 수 있음 - 즉, '자기
	 * 자신보다 작은 사람 수 + 자기 자신보다 큰 사람 수 = 총 학생의 수'가 성립할 때만 자신의 키를 알 수 있음
	 * - 행 정보 : 자기 자신보다 큰 사람 
	 * - 열 정보 : 자기 자신보다 작은 사람
	 */

	static class HeightInfo {
		int curR;
		int curC;
		int parR;
		int parC;

		public HeightInfo(int curR, int curC, int parR, int parC) {
			super();
			this.curR = curR;
			this.curC = curC;
			this.parR = parR;
			this.parC = parC;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] adjMatrix = new int[N + 1][N + 1];
		boolean[][] visited = new boolean[N + 1][N + 1]; // 방문체크용
		Queue<HeightInfo> queue = new LinkedList<>();

		// 입력 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int lower = Integer.parseInt(st.nextToken());
			int higher = Integer.parseInt(st.nextToken());
			adjMatrix[lower][higher] = 1;
			queue.add(new HeightInfo(lower, higher, lower, higher));
		}

		// 자기 자신보다 큰 걸 기준으로 업데이트
		while (!queue.isEmpty()) {
			HeightInfo info = queue.poll();
			int curR = info.curR;
			int curC = info.curC;
			int parR = info.parR;
			int parC = info.parC;

			for (int i = 1; i <= N; i++) {
				// 자기 자신보다 큰 걸 기준으로
				if (!visited[parR][i] && adjMatrix[curC][i] == 1) {
					queue.add(new HeightInfo(curC, i, parR, parC));
					adjMatrix[parR][i] = 1;
					visited[parR][i] = true;
				}
			}
		}

		// 결과 출력
		int result = 0;
		for (int i = 1; i <= N; i++) {
			int sumR = 0;
			int sumC = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				sumR += adjMatrix[i][j];
				sumC += adjMatrix[j][i];
			}
			if (N - 1 == sumR + sumC)
				result++;
		}

		System.out.println(result);
		
	} // end of main

} // end of class
