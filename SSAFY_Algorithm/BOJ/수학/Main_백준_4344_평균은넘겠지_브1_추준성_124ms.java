import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_4344_평균은넘겠지_브1_추준성_124ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int testCase = 1; testCase <= tc; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] scores = new int[N];
			int avg = 0;
			for (int i = 0; i < N; i++) {
				int score = Integer.parseInt(st.nextToken());
				avg += score;
				scores[i] = score;
			}
			avg /= N;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if(scores[i] > avg) cnt++;
			}
			
			double ratio = (double) cnt / N;
			
			System.out.printf("%.3f%%\n", ratio * 100);
			
		} // end of testCase
		
	} // end of main

} // end of class
