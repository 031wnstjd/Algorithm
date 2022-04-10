import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_15649_N과M_3_실3_추준성_392ms {
	private static int N;
	private static int M;
	private static int[] output;
	private static StringBuilder sb;
	
	/*
	 * 중복순열
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		output = new int[M];
		
		perm(0);
		
		System.out.print(sb.toString());
	} // end of main
	
	static void perm(int depth) {
		if(depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			output[depth] = i;
			perm(depth+1);
		}
	} // end of method perm
	

} // end of class
