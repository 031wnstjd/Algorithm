import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_SWEA_9229_한빈이와SpotMart_D3_추준성 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int max = -1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken(" "));
			int M = Integer.parseInt(st.nextToken(" "));
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int arr[] = new int[N]; // 과자 무게를 담을 배열 생성
			for (int i = 0; i < N; i++) {				
				arr[i] = Integer.parseInt(st2.nextToken()); // 과자의 무게들을 담는 배열
			}
			Arrays.sort(arr); // 오름차순 정렬
			
			for (int i = N-1; i >= 1; i--) { // 큰 것부터 조회 (그리디 알고리즘)
				if(arr[i] >= M) {
					continue;
				} else {
					for (int j = i-1; j >= 0 ; j--) {
						if(arr[i] + arr[j] <= M && arr[i] + arr[j] > max) {
							max = arr[i] + arr[j];
						}
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		} // end of for testCase
		System.out.println(sb);
	} // end of main

}// end of class
