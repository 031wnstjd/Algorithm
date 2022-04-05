import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_2292_벌집_브2_추준성_128ms {
	
	/*
	 * 1.N이 몇 번째 띠에 존재하는지만 알면 됨
	 * 2.띠의 둘레 1 -> 6*1 -> 6*2 -> 6*3 -> ... 순으로 커짐 (6의 배수)
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 1; // 첫 번째도 카운트에 포함
		
		if(N == 1) {
			System.out.println(cnt);
		} else {
			while(N > 1) {
				N -= 6*cnt;
				cnt++;
			}
			
			System.out.println(cnt);
		}
		
		
	} // end of main
	
} // end of class
