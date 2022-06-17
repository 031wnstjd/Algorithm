import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_3052_나머지_브2_추준성_76ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[10];
		int[] cntArr = new int[43];
		for (int i = 0; i < 10; i++) {
			int tmp = Integer.parseInt(br.readLine()) % 42;
			cntArr[tmp]++;
		}
		
		int cnt = 0;
		for (int i = 0; i < cntArr.length; i++) {
			if(cntArr[i] != 0) cnt++;
		}
		
		System.out.print(cnt);
		
	} // end of main

} // end of class
