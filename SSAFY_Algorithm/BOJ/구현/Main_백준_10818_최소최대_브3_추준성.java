import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10818_최소최대_브3_추준성 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = -1000000;
		int min = 1000000;
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			max = Math.max(max, tmp);
			min = Math.min(min, tmp);
		}
		
		sb.append(min).append(" ").append(max);
		System.out.println(sb.toString());
		
	} // end of main

} // end of class
