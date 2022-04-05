import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_2309_일곱난쟁이_브2_추준성_128ms {
	
	private static int N = 7;
	private static int[] input;
	private static int[] output;
	private static boolean flag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = new int[9];
		output = new int[N];
		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(input); // 조합의 경우, input을 오름차순 정렬하면 output도 오름차순 정렬로 나옴
		
		comb(0,0);
		
	} // end of main
	public static void comb(int cnt, int start) {
		if(flag) { // 중복 출력 방지
			return;
		}
		// 기저 조건
		if(cnt == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += output[i];
			}
			if(sum == 100) {
				flag = true;
				for (int i = 0; i < N; i++) {
					System.out.println(output[i]);
				}
			}
			return;
		}
		
		for (int i = start; i < input.length; i++) {
			output[cnt] = input[i];
			comb(cnt+1, i+1);
		}
	} // end of method comb
} // end of class
