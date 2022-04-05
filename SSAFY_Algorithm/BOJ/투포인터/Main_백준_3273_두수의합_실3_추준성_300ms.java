import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_3273_두수의합_실3_추준성_300ms{
	/*
	 * 조합으로 풀면 시간 터짐 => 투 포인터
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(numbers); // 투포인터 적용을 위해 오름차순 정렬
		
		int cnt = 0; // 문제의 조건을 만족하는 쌍의 개수
		int leftPointer = 0;
		int rightPointer = N-1;
		
		while(leftPointer < rightPointer) {
			int sum = numbers[leftPointer] + numbers[rightPointer];
			// 두 포인터에 담긴 수의 합이 x보다 작으면  leftPointer++
			if(sum < x) leftPointer++;
			// 두 포인터에 담긴 수의 합이 x보다 크거나 같으면 rightPointer--
			else {
				if(sum == x) cnt++; // sum == x이면 카운트 증가
				rightPointer--;
			}
		}
		
		System.out.print(cnt);
	} // end of main
} // end of class
