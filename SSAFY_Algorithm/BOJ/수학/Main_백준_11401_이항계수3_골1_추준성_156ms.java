import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_11401_이항계수3_골1_추준성_156ms {
	private static int P;
	private static long[] fac;
	/*
	 * 1. 1,000,000,007 : 소수
	 * 2. 소수로 나눈 나머지 => 페르마의 소정리 => a^(p-2) == (1/a) (mod p)
	 * 3. nCk = n! / (n-k)! * k!
	 * 4. 분할 정복을 이용한 거듭제곱
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		P = 1000000007;
		fac = new long[N+1];
		
		long a = factorial(N); // n!
		long b = factorial(N-K) * factorial(K) % P; // ((n-k)!k!)^(p-2)
				
		System.out.print(a * pow(b, P-2) % P);
		
	} // end of main

	 static long pow(long x, long exp) {
		if(exp == 1) return x % P;
	
		long tmp = pow(x, exp >> 1);
		
		if(exp % 2 == 1) return (tmp * tmp % P) * x % P;
		return tmp * tmp % P;
	 }
	 
	 static long factorial(int n) {
		fac[0] = 1;
		fac[1] = 1;
		
		if(fac[n] == 0) {
			for (int i = 2; i <= n; i++) {
				fac[i] = fac[i-1] * i % P;
			}
		}
		
		return fac[n];
	 }
} // end of class
