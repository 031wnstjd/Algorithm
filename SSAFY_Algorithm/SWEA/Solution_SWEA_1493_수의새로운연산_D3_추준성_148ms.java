package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1493_수의새로운연산_D3_추준성_148ms {
	/*
	 * 1. p와 q의 x좌표, y좌표를 각각 더한 뒤 해당 지점의 할당 값을 구하는 연산
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine()); 
		
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			int nr = findPoint(p)[0] + findPoint(q)[0];
			int nc = findPoint(p)[1] + findPoint(q)[1];
			
			sb.append("#").append(testCase).append(" ").append(findValue(nr, nc)).append("\n");
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main
	
	public static int findValue(int pr, int pc) {
		
		// 좌표에 해당하는 숫자 찾기
		int num = 1;
		int temp = 0;
		for (int r = 1; ; r++) {
			temp = r; // r값을 직접적으로 줄이면 안되므로 temp에 담아서 다음 c for문을 돌 때 temp--를 하며 r좌표로 할당
			for (int c = 1; c <= r; c++) {
				if(temp == pr && c == pc) { // 입력한 좌표와 같으면 해당 지점의 숫자를 리턴
					return num;
				}
				temp--;
				num++;
			}
		}
		
	} // end of method findValue
	
	public static int[] findPoint(int value) {
		
		int[] point = new int[2]; // 점의 좌표
		
		// 좌표 찾기
		int num = 1;
		int temp = 0;
		for (int r = 1; ; r++) {
			temp = r; // r값을 직접적으로 줄이면 안되므로 temp에 담아서 다음 c for문을 돌 때 temp--를 하며 r좌표로 할당
			for (int c = 1; c <= r; c++) {
				if(num == value) {
					point[0] = temp;
					point[1] = c;
					return point;
				}
				temp--;
				num++;
			}
		}
	} // end of method findPoint
	
} // end of class


