import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_1475_방번호_실5_추준성_76ms {
	/*
	 * 6과 9는 동일하게 취급
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] cnt = new int[10];
		
		for (int i = 0; i < str.length(); i++) {
			int tmp = str.charAt(i) - '0';
			if(tmp == 6 || tmp == 9) {
				// 6이거나 9이면, 카운트 값이 더 작은 인덱스의 값을 증가시킴
				if(cnt[6] <= cnt[9]) cnt[6]++;
				else cnt[9]++;
			} else {
				cnt[tmp]++;
			}
		}
		
		int max = 0;
		for (int i = 0; i < cnt.length; i++) {
			max = Math.max(max, cnt[i]);
		}
		
		System.out.print(max);
		
	} // end of main

} // end of class
