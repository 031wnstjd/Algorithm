import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_2230_수고르기_골5_추준성_296ms {
	/*
	 * 1 <= N <= 100,000 => O(N^2)이면 터짐 => O(N)이하여야 함 => 투포인터
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int leftPointer = 0; // 차이가 M보다 더 크면 오른쪽으로 이동
		int rightPointer = 0; // 차이가 M보다 더 작으면 오른쪽으로 이동
		
		int minDiff = Integer.MAX_VALUE;
		while(leftPointer <= N-1 && rightPointer <= N-1) {
			int diff = arr[rightPointer] - arr[leftPointer];
			if(diff < M) rightPointer++;
			else if(diff > M) {
				leftPointer++;
				minDiff = Math.min(minDiff, diff);
			} 
			else { // diff == M이면 이를 저장하고 break 
				minDiff = diff;
				break;
			}
		}
		
		System.out.print(minDiff);
		
	} // end of main

} // end of class
