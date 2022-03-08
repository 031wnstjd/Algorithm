import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_SWEA_2001_파리퇴치_D2_추준성 {
	public static void main(String[] args) throws Exception{
		
//		이중 for문 - 필터의 탐색범위는 MxM으로 고정이지만 반복 시작점 r, c가 지속적으로 변함
//		필터의 가로, 세로 방향 이동은 N-M번
//		최댓값을 지속적으로 갱신한 후 마지막에 리턴
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		// 테스트 케이스 돌기
		for (int testCase = 1; testCase <= TC; testCase++) {
			// N, M 입력 받기
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); // 문자열을 정수로 변환
			int M = Integer.parseInt(s[1]);
			
			// map 입력받기
			String[][] map = new String[N][N];
			for (int i = 0; i < map.length; i++) {			
				map[i] = br.readLine().split(" ");
			}
						
			int r = 0; // r : 0~N-M
			int c = 0; // c : 0~N-M
			int max = 0;
			int sum = 0;
			// 필터 이동 범위 제한 (왼쪽 위 꼭짓점)
			
			while(true) {
				if(r > N-M) { // 행의 outOfBoundsException 관리			
					break;
				}
				while(0 <= c && c <= N-M) { // 열의 outOfBoundsException 관리
					sum = 0;
					for (int i = r; i < r+M; i++) {
						for (int j = c; j < c+M; j++) {
							sum += Integer.parseInt(map[i][j]);
						}			
					}
					// 최댓값 갱신
					if(sum > max) {
						max = sum;
					}
					c++;
				}
				r++;
				c=0;
			}
			
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main
} // end of class
