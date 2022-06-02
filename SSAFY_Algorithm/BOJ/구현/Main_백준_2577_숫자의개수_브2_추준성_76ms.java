import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_2577_숫자의개수_브2_추준성_76ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		
		char[] ret = String.valueOf(A * B * C).toCharArray(); // 1 <= ret <= 10^9
		int[] cnt = new int[10];
		
		for (int i = 0; i < ret.length; i++) {
			cnt[ret[i]-'0']++;
		}
		
		for (int i = 0; i < cnt.length; i++) {
			System.out.println(cnt[i]);
		}
		
		
	} // end of main

} // end of class
