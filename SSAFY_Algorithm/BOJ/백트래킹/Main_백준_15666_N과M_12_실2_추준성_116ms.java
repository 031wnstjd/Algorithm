import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_백준_15666_N과M_12_실2_추준성_116ms {
	private static int N;
	private static int M;
	private static int[] input;
	private static int[] output;
	private static HashSet<String> set;
	private static StringBuilder ans;

	/*
	 * 1. 중복조합
	 * 2. 중복 결과가 출력되지 않게끔 해싱기법 활용 (HashSet 활용)
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		output = new int[M];
		set = new HashSet<String>();
		ans = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input); // 사전 순으로 증가하는 순서로 수열을 출력하기 위해 정렬
		
		comb(0, 0);
		
		System.out.println(ans.toString());
	} // end of main
	
	static void comb(int depth, int start) {
		if(depth == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb.append(output[i]).append(" ");
			}
			String s = sb.toString();
			
			if(!set.contains(s)) {
				set.add(s);
				ans.append(s).append("\n");
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			output[depth] = input[i];
			comb(depth+1, i);
		}
	} // end of method perm
	

} // end of class
