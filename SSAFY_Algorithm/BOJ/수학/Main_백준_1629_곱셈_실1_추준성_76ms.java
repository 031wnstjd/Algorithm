import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1629_곱셈_실1_추준성_76ms {
	/*
	 * 모듈러 연산 : (a*b)%c == ((a%c)*(b%c))%c
	 */
	private static int c;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		System.out.println(pow(a, b));
	} // end of main

	private static long pow(long a, long b) {
		if(b == 1) return a % c;
		
		long tmp = pow(a, b>>1);
		
		if(b%2==0) return tmp * tmp % c;
		else return (tmp * tmp % c) * a % c;
	}
	
} // end of class
