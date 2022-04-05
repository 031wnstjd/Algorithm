import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2477_참외밭_실4_추준성_132ms {
	/*
	 * 1.인접한 변들의 곱들의 합 = 3*전체넓이 - 빈 부분
	 * => 빈 부분 = 3*전체넓이 - 인접한 변들의 곱들의 합
	 * 2. 밭의 넓이 = 전체 넓이 - 빈 부분 = 인접한 변들의 곱들의 합 - 2*전체넓이
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int sumArea = 0; // 인접한 변들의 곱들의 합
		int beforeLength = 1;
		int maxHorizontal = 0;
		int maxVertical = 0;
		int firstLength = 0;
		
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken()); // 방향
			int length = Integer.parseInt(st.nextToken()); // 변의 길이
			
			if(dir == 1 || dir == 2) { // 가로 방향의 최댓값 구하기
				if(length > maxHorizontal) {
					maxHorizontal = length;
				}
			} else { // 세로 방향의 최댓값 구하기
				if(length > maxVertical) {
					maxVertical = length;
				}
			}
			
			if(i > 0) {
				sumArea += beforeLength * length; // 인접한 변들의 곱을 누적합
			} else {
				firstLength = length; // 가장 첫 번째 길이 따로 저장
			}

			beforeLength = length; // 현재 변의 길이를 이전 변의 길이로 둠
		}
		
		sumArea += firstLength * beforeLength;
		
		System.out.print((sumArea-2*maxHorizontal*maxVertical)*K); // 주어진 밭에서 자라는 참외의 수
	} // end of main
	
} // end of class
