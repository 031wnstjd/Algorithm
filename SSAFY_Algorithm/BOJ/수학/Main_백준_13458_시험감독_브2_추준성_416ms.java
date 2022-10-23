import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_13458_시험감독_브2_추준성_416ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] testers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			testers[i] = Integer.parseInt(st.nextToken());
		}
		
		int B, C;
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		long cnt = 0;
		int quotient = 0;
		int remainder = 0;
		for (int i = 0; i < N; i++) {
			testers[i] -= B;
			cnt++; // 감독관 수 카운트
			if(testers[i] <= 0) continue; // 총감독관 하나로 충분하면 continue
			
			quotient = testers[i] / C; // 부감독관 감시 가능 인원 수로 나눈 몫
			remainder = testers[i] % C; // 부감독관 감시 가능 인원 수로 나눈 나머지
			
			if(remainder > 0) cnt += (quotient + 1);
			else cnt += quotient;
		}
		
		System.out.print(cnt);
		
	} // end of main

} // end of class
