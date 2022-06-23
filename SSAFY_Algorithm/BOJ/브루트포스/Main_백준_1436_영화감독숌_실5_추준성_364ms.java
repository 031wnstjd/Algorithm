import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_1436_영화감독숌_실5_추준성_364ms {
	/*
	 * - 종말의 숫자 : 666이 들어가는 수
	 * - N번째로 작은 종말의 숫자?
	 * => "666"이 들어가는 수들을 카운트하며, N번째가 될 때 해당 수 출력
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 1;
		int number = 666;
		while(cnt < N) {
			number++;
			
			if(String.valueOf(number).contains("666")) {
				cnt++;
			}
		}
		
		System.out.println(number);
		
	} // end of main

} // end of class
