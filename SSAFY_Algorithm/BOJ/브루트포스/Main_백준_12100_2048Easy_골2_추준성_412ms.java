import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_12100_2048Easy_골2_추준성_412ms {
	private static int[][] map;
	private static int N;

	/*
	 * 0. '같은 값'을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다
	 * 1. 합쳐지면, 합쳐지는 것 처리 이후에 그 다음 블록 이동
	 * 2. 4^5 == 2^10 == 1024개의 경우의 수 == 중복순열
	 */
	
	static int max = 0; // 블록 최댓값
	private static int[] output;
	private static int[] directions;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		directions = new int[4];
		for (int i = 0; i < 4; i++) {
			directions[i] = i; // 0, 1, 2, 3으로 초기화 (방향 인덱스)
		}
		output = new int[5]; // 최대 다섯 번
		
		rPerm(0);
		
		System.out.println(max);
	} // end of main

	private static void rPerm(int depth) {
		if(depth == 5) {
			int[][] copyMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, N); // 깊은 복사
			}
			
			for (int i = 0; i < 5; i++) { // 5번 이동
				copyMap = moveBlocks(copyMap, output[i]); 
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, copyMap[i][j]); // 최댓값 갱신
				}
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			output[depth] = directions[i];
			rPerm(depth+1);
		}
	} // end of rPerm

	private static int[][] moveBlocks(int[][] map, int dir) {
		
		LinkedList<Integer> queue = new LinkedList<>();
		
		switch(dir) {
			case 0: // 상
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(map[j][i] != 0) queue.add(map[j][i]); // 한 열에 대해서 위에서부터 차례대로 queue에 담기
						map[j][i] = 0; // 0으로 초기화
					}

					int idx = 0; // 위에서부터
					while(!queue.isEmpty()) {
						Integer curVal = queue.poll();
						
						if(map[idx][i] == 0) {
							map[idx][i] = curVal;
						} else if(map[idx][i] == curVal) {
							map[idx][i] = curVal * 2;
							idx++;
						} else {
							idx++; // 현재 인덱스에서의 값이 0도 아니고, queue에서 빼낸 값과 같지도 않다면 그 다음 위치에 값을 배정
							map[idx][i] = curVal;
						}
					}
				}
				break;
				
			case 1: // 우
				for (int i = 0; i < N; i++) {
					for (int j = N-1; j >= 0; j--) {
						if(map[i][j] != 0) queue.add(map[i][j]);
						map[i][j] = 0;
					}
					
					int idx = N-1;
					while(!queue.isEmpty()) {
						Integer curVal = queue.poll();
						
						if(map[i][idx] == 0) {
							map[i][idx] = curVal;
						} else if(map[i][idx] == curVal) {
							map[i][idx] = curVal * 2;
							idx--;
						} else {
							idx--;
							map[i][idx] = curVal;
						}
					}
				}
				break;
				
			case 2: // 하
				for (int i = 0; i < N; i++) {
					for (int j = N-1; j >= 0; j--) {
						if(map[j][i] != 0) queue.add(map[j][i]);
						map[j][i] = 0;
					}
					
					int idx = N-1;
					while(!queue.isEmpty()) {
						Integer curVal = queue.poll();
						
						if(map[idx][i] == 0) {
							map[idx][i] = curVal;
						} else if(map[idx][i] == curVal) {
							map[idx][i] = curVal * 2;
							idx--;
						} else {
							idx--;
							map[idx][i] = curVal;
						}
					}
				}
				break;
				
			case 3: // 좌
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(map[i][j] != 0) queue.add(map[i][j]);
						map[i][j] = 0;
					}

					int idx = 0;
					while(!queue.isEmpty()) {
						Integer curVal = queue.poll();
						
						if(map[i][idx] == 0) {
							map[i][idx] = curVal;
						} else if(map[i][idx] == curVal) {
							map[i][idx] = curVal * 2;
							idx++;
						} else {
							idx++;
							map[i][idx] = curVal;
						}
						
					}
				}
			break;
		}
		
		return map; // 이동 후 결과 map
		
	} // end of moveBlocks

} // end of class
