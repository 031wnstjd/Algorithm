import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_백준_15664_N과M_10_실2_추준성_84ms {
	private static int N;
	private static int M;
	private static int[] input;
	private static int[] output;
	private static boolean[] visited;
	private static StringBuilder sb;
	private static HashSet<String> set;

	/*
	 * 중복 결과가 출력되지 않게끔 해싱기법 활용 (HashSet 활용)
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		visited = new boolean[N];
		output = new int[M];
		set = new HashSet<String>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input); // 사전 순으로 증가하는 순서로 수열을 출력하기 위해 정렬
		
		perm(0);
		
		System.out.print(sb.toString());
	} // end of main
	
	static void perm(int depth) {
		if(depth == M) {
			
			for (int i = 0; i < M-1; i++) {
				if(output[i] > output[i+1]) return;
			}
			
			// 해싱 기법 (중복 결과가 출력 안 되게끔)
			StringBuilder strb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				strb.append(output[i]);
			}
			String s = strb.toString();
			
			if(set.contains(s)) return; // 기존에 추가된 수열이면 출력 안 하고 리턴
			set.add(s); // 아니면 추가
			
			// 결과 출력
			for (int i = 0; i < M; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			output[depth] = input[i];
			visited[i] = true;
			perm(depth+1);
			visited[i] = false;
		}
	} // end of method perm
	

} // end of class
