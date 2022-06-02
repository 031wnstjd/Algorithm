import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_10808_알파벳개수_브2_추준성_80ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] str = br.readLine().toCharArray();
		int[] cnt = new int[26];
		for (int i = 0; i < str.length; i++) {
			cnt[str[i]-'a']++;
		}
		
		for (int i = 0; i < cnt.length; i++) {
			sb.append(cnt[i]).append(" ");
		}
		
		System.out.print(sb.toString());
		
	} // end of main	

} // end of class
