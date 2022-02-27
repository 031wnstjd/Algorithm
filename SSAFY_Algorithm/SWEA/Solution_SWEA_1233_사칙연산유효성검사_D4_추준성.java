import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1233_사칙연산유효성검사_D4_추준성 {
	/* 특징 (문제를 관통하는 원리를 찾는 것이 중요)
	 * 1. 단말노드가 아니면 연산자가 들어가야함
	 * 2. 단말노드이면 숫자가 들어가야함
	 * 
	 * 입력 데이터 : 현재 노드 번호, 현재 노드에 담겨진 연산자나 숫자, 왼쪽 자식 노드의 번호, 오른쪽 자식 노드의 번호
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int result = 1; // 유효성 검사 판단 결과 -> 1이면 가능, 0이면 불가능
			
			// 유효성 검사
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				st.nextToken(); // 필요 없는 노드 번호 항목 지움
				char element = st.nextToken().charAt(0);
				// 다음 토큰이 없으면 단말 노드, 있으면 그냥 노드
				if(!st.hasMoreTokens()) {					
					if('0' > element || element > '9') { // 단말노드이고, 숫자를 포함 안하면 오류
						result = 0;
					}
				} else {
					if('0' <= element && element <= '9') { // 단말노드가 아니고, 숫자를 포함하면 오류
						result = 0;
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main
} // end of class
