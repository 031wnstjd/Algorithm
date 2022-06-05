import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_9372_상근이의여행_실3_추준성_184ms {
	/*
	 * N개의 국가를 모두 여행하기 위해 필요한 최소 간선 개수 == N-1
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int i = 1; i <= TC; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				br.readLine();
			}
			sb.append(N-1).append("\n");
		}
		System.out.print(sb.toString());
	} // end of main

} // end of class
