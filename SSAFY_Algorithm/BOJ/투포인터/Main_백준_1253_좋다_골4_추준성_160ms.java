import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1253_좋다_골4_추준성_160ms {
	/*
	 * <설계>
	 * 1. 어떤 수가 다른 두 수 두 개의 합으로 나타내어진다. 
	 * => (1) 정렬된 상태에서 해당 수보다 작은 범위만 고려 - 2000 * 2001 / 2 
	 * 	      => 해당 수보다 작은 범위만 고려할 경우 크기가 같은 수가 연속될 때 문제 발생 => 모든 범위를 다 고려 => left = 0, right = N-1
	 * => (2) 그 중 두 개를 뽑아서 더하여 체크 - O(N)이 돼야 하므로 투포인터 활용
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums); // 오름차순 정렬
		
		int cnt = 0; // 좋은 수 개수
		for (int i = 0; i < N; i++) { // 투포인터 알고리즘 시작
			int target = nums[i]; // 좋은 수인지 체크할 타겟 넘버
			int left = 0;
			int right = N-1;
			while(true) {
				// 1. 포인터가 타겟 인덱스와 같아질 경우 포인터 인덱싱 처리 [중요]
				if(left == i) left++;
				else if(right == i) right--;
				
				if(left >= right) break; // 투포인터 종료조건
					
				int sum = nums[left] + nums[right];
				
				// 2. 좋은 수인지 체크
				if(sum == target) {
					cnt++;
					break;
				}
				else if(sum < target) {
					left++;
				}
				else if(sum > target) {
					right--;
				}
			}
		}
		
		System.out.println(cnt);
		
		
	} // end of main

} // end of class
