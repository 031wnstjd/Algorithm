import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_3307_최장증가부분수열_D3_추준성_197ms {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] LIS = new int[N]; // 각 수를 맨 마지막 순서로 가지는 부분 증가 수열의 최대 길이를 저장
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;

			Arrays.fill(LIS, 1); // LIS 최소 길이 == 1 (자기 자신만 고려)
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < i; j++) { // 자기 자신 이전의 원소들만 고려
					if(arr[i] > arr[j] && LIS[i] < LIS[j] + 1) { // 자기 자신보다 작은 원소들에 대해(증가 수열 조건 만족) LIS값 갱신 
						LIS[i] = LIS[j] + 1;
					}
				}
				if(max < LIS[i]) max = LIS[i];
			}
			
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		} // end of for testCase
		System.out.println(sb.toString());
	} // end of main

} // end of class
