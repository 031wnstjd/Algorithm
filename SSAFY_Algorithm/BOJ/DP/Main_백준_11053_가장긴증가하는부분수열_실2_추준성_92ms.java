import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_11053_가장긴증가하는부분수열_실2_추준성_92ms {
	/*
	 * LIS(Longest Increasing Subsequence) 알고리즘
	 * - 자기 자신(i번째 원소)를 끝으로 하는 증가 수열의 길이 중 가장 큰 길이를 LIS 배열에 할당
	 * - LIS 배열의 값들 중 최댓값<=> 수열 A의 가장 긴 증가하는 부분 수열의 길이 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] LIS = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(LIS, 1);
		
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && LIS[i] < LIS[j] + 1) LIS[i] = LIS[j] + 1;
			}
			if(max < LIS[i]) max = LIS[i];
		}
		
		
		System.out.print(max);
		
	} // end of main

} // end of class
