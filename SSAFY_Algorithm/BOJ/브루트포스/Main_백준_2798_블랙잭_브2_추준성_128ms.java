import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_백준_2798_블랙잭_브2_추준성_128ms {

	private static int N;
	private static int M;
	private static int max;

	/*
	 * 1. 조합을 통해 세 개의 카드 조합들을 구하고, 세 개의 카드 넘버의 합이 21이하의 범위에서 최댓값이 되는 경우를 고름
	 * 2. 카드 조합들을 구해나갈 때, 카드 넘버의 합이 M이 넘으면 가지치기
	 * 3. 재귀 시간초과 나면, 카드 3개밖에 안되므로 3중 for문 이용
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] inputs = new int[N];
		
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st2.nextToken());
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				for (int k = j+1; k < N; k++) {
					sum += inputs[i] + inputs[j] + inputs[k]; // 세 카드 조합의 합
					if(sum < M && sum > max) { // 합이 M을 넘지 않고, 이전 max값보다 크면 해당 합을 max값으로 할당
						max = sum;
					}
					if(sum == M) { // 합이 M이면 출력하고 main 메서드 종료
						System.out.print(sum);
						return;
					}
					sum = 0; // sum 초기화
				}
			}
		}
		System.out.print(max);
	} // end of main
	
} // end of class


// 	재귀함수 -> 시간 초과
//	public static void comb(int depth, int start, int sum, int[] inputs) {
//		if(depth == 3) { // 기저 조건
//			if(sum < M && sum > max) {
//				max = sum;
//			}
//			if(sum == M) { // 정확히 M이면 이를 출력하고 시스템 종료
//				System.out.println(sum);
//				System.exit(0);
//			}
//		}
//		
//		for (int i = start; i < N; i++) {
//			if(inputs[i] < M && sum < M) { 
//				comb(depth+1, i+1, sum+inputs[i], inputs);
//			}
//		}
//	} // end of method comb
