import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_2839_설탕배달_브1_추준성_124ms {

	/*
	 * 그리디 - 큰 킬로그램 봉지부터 담기
	 * 1. N이 5로 나누어 떨어지면 몫 만큼 봉지 개수 출력 
	 * 2. 5로 나누어 떨어지지 않으면 3을 빼고 다시 N이 5로 나누어 떨어지는지 확인
	 * 3. 1, 2를 반복했는데도 구해지지 않으면 -1을 출력
	 * => 하나의 연산을 고정시켜놓고(= N%5) N에서 3을 빼주면서 검사하는 느낌  
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0; // 봉지의 개수
		while(N >= 0) {
			if(N % 5 == 0) {
				cnt += N/5;
				System.out.print(cnt);
				return;
			} else {
				N -= 3;
				cnt++;
			}
		}
		System.out.print(-1);
	}

}
