import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_1919_애너그램만들기_브2_추준성_80ms {
	/*
	 * 두 문자열의 알파벳 카운트 배열을 각각 두고, 동일한 알파벳에 대하여 두 카운트 배열 값 중 더 작은 값을 common에 더함
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int[] cnt1 = new int[26];
		int[] cnt2 = new int[26];
		
		for (int i = 0; i < str1.length(); i++) {
			cnt1[str1.charAt(i) - 'a']++;
		}
		for (int i = 0; i < str2.length(); i++) {
			cnt2[str2.charAt(i) - 'a']++;
		}
		
		int common = 0;
		for (int i = 0; i < 26; i++) {
			common += Math.min(cnt1[i], cnt2[i]);
		}
		
		System.out.print(str1.length() + str2.length() - 2 * common);
		
	} // end of main
 
} // end of class
