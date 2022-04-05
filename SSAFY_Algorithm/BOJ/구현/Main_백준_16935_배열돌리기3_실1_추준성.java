import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_16935_배열돌리기3_실1_추준성 {
	
	private static int[][] mapSave; // 연산을 지속적으로 적용해서 저장해야하므로 전역변수로 선언
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		mapSave = map;
		// R개만큼 입력되는 연산을 순서대로 적용
		StringTokenizer st3 = new StringTokenizer(br.readLine(), " ");			
		for (int testCase = 0; testCase < R; testCase++) {
			int testNum = Integer.parseInt(st3.nextToken());
			
			switch(testNum) {
			
			case 1: // 상하 반전
				for (int i = 0; i < mapSave.length/2; i++) {					
					for (int j = 0; j < mapSave[0].length; j++) {
						int temp = mapSave[i][j];
						mapSave[i][j] = mapSave[(mapSave.length-1)-i][j];
						mapSave[(mapSave.length-1)-i][j] = temp;
					}
				}
				
				break;
			case 2: // 좌우 반전
				for (int i = 0; i < mapSave.length; i++) {					
					for (int j = 0; j < mapSave[0].length/2; j++) {
						int temp = mapSave[i][j];
						mapSave[i][j] = mapSave[i][(mapSave[0].length-1)-j];
						mapSave[i][(mapSave[0].length-1)-j] = temp;
					}
				}
				
				break;
			case 3: // 오른쪽으로 90도 회전 (회전은 배열의 크기가 NxM에서 MxN으로 반전되므로 새로운 배열 객체 생성)
				int[][] mapRotationRight = new int[mapSave[0].length][mapSave.length];
				for (int i = 0; i < mapSave.length; i++) {
					for (int j = 0; j < mapSave[0].length; j++) {
						mapRotationRight[j][(mapSave.length-1)-i] = mapSave[i][j];
					}
				}
				mapSave = mapRotationRight;
				break;
			case 4: // 왼쪽으로 90도 회전
				int[][] mapRotationLeft = new int[mapSave[0].length][mapSave.length];
				for (int i = 0; i < mapSave.length; i++) {
					for (int j = 0; j < mapSave[0].length; j++) {
						mapRotationLeft[(mapSave[0].length-1)-j][i] = mapSave[i][j];
					}
				}
				mapSave = mapRotationLeft;
				break;
			case 5:
				int nr = 0;
				int nc = 0;
				int idx = 0;
				int startR = 0; 
				int startC = 0; 
				int[] dr = {mapSave.length/2,0, -mapSave.length/2, 0};// 하우상좌
				int[] dc = {0, mapSave[0].length/2, 0, -mapSave[0].length/2};
				int[][] save = new int[mapSave.length/2][mapSave[0].length/2];
				// 맨 처음 영역 저장
				for (int i = 0; i < save.length; i++) {
					for (int j = 0; j < save[0].length; j++) {
						save[i][j] = mapSave[i][j];
					}
				}
				while(idx < 3) {
					// 사각형 영역에 있는 값들을 붙여넣기 
					for (int r = startR; r < startR + mapSave.length/2; r++) {
						for (int c = startC; c < startC + mapSave[0].length/2; c++) {
							nr = r + dr[idx];
							nc = c + dc[idx];							
								mapSave[r][c] = mapSave[nr][nc]; // 현재 값에 다음 영역에 있는 걸 저장
						}
					}
					startR += dr[idx];
					startC += dc[idx];
					idx++;
				}
				// 처음에 저장했던 값을 마지막 영역에 채워넣기
				for (int i = 0; i < save.length; i++) {
					for (int j = 0; j < save[0].length; j++) {
						mapSave[startR+i][startC+j] = save[i][j];
					}
				}
				
				
				break;
			case 6:
				int nr2 = 0;
				int nc2 = 0;
				int idx2 = 0;
				int startR2 = 0; 
				int startC2 = 0; 
				int[] dr2 = {0, mapSave.length/2, 0, -mapSave.length/2};// 우하좌상
				int[] dc2 = {mapSave[0].length/2, 0, -mapSave[0].length/2, 0};
				int[][] save2 = new int[mapSave.length/2][mapSave[0].length/2];
				// 맨 처음 영역 저장
				for (int i = 0; i < save2.length; i++) {
					for (int j = 0; j < save2[0].length; j++) {
						save2[i][j] = mapSave[i][j];
					}
				}
				while(idx2 < 3) {
					// 사각형 영역에 있는 값들을 붙여넣기 
					for (int r = startR2; r < startR2 + mapSave.length/2; r++) {
						for (int c = startC2; c < startC2 + mapSave[0].length/2; c++) {
							nr2 = r + dr2[idx2];
							nc2 = c + dc2[idx2];							
								mapSave[r][c] = mapSave[nr2][nc2]; // 현재 값에 다음 영역에 있는 걸 저장
						}
					}
					startR2 += dr2[idx2];
					startC2 += dc2[idx2];
					idx2++;
				}
				// 처음에 저장했던 값을 마지막 영역에 채워넣기
				for (int i = 0; i < save2.length; i++) {
					for (int j = 0; j < save2[0].length; j++) {
						mapSave[startR2+i][startC2+j] = save2[i][j];
					}
				}
			}
		} 
		
		// 최종 결과 출력
		for (int i = 0; i < mapSave.length; i++) {
			for (int j = 0; j < mapSave[0].length; j++) {
				sb.append(mapSave[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		
	} // end of main
} // end of class
