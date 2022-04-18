import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_2217_로프_실4_추준성_268ms {

	/*
	 * 1. 모든 로프를 사용해야 할 필요는 없으며, 임의로 몇 개의 로프를 골라서 사용해도 된다.
	 * 2. 구하고자 하는 것 : 로프들을 이용하여 들어올릴 수 있는 물체의 최대 중량 (== 선택한 로프들 중 최저 무게 * 로프의 개수)
	 * 
	 * => 무게 정렬 후, 최저 무게를 하나씩 지워가면서 들어올릴 수 있는 최대 중량 갱신 => 최대 중량 출력
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] weight = new int[N];
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(weight); // 오름차순 정렬
		
		int max = 0; // 들어올릴 수 있는 최대 중량
		for (int i = 0; i < N; i++) {
			max = Math.max(max, weight[i] * (N-i));
		}
		
		System.out.print(max);
		
	} // end of main

} // end of class
