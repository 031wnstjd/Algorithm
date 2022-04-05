import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1074_Z_실1_추준성_128ms {

	/*
	<영역탐색>
	초기 영역
	1영역 : 0 <= r < M/2, 0 <= c < M/2 
	2영역 : 0 <= r < M/2, M/2 <= c < M 
	3영역 : M/2 <= r < M, 0 <= c < M/2 
	4영역 : M/2 <= r < M, M/2 <= c < M 
	=> M이 1이 될 때까지 M = M/2을 반복적으로 수행
	각 영역에 (M/2 * M/2)개의 원소 존재
 	주어진 (r,c)가 어느 구간에 존재하는지 판단하기
	*/
	
	private static int cnt; // 몇 번째 방문인지 카운트할 변수

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int M = (int) Math.pow(2, N); // 가장 행, 열의 길이
		
		int startR = 0;
		int startC = 0;
		
		// 영역 탐색
		while(true) {
			if(M == 1) {
				System.out.println(cnt);
				break;
			}
			// M==1이 될 때까지 재귀적으로 영역 탐색
//			// 1영역 (처리할 게 없음)
//			if(startR <= r && r < startR + M/2 && startC <= c && c < startC + M/2) { 
//
//			}
			// 2영역
			if(startR <= r && r < startR + M/2 && startC + M/2 <= c && c < startC + M ) { 
				startC += M/2;
				cnt += M/2*M/2; 
			}
			// 3영역
			if(startR + M/2 <= r && r < startR + M && startC <= c && c < startC + M/2) { 
				startR += M/2;
				cnt += 2*(M/2*M/2);
			}
			// 4영역
			if(startR + M/2 <= r && r < startR + M && startC + M/2 <= c && c < startC + M) { 			
				startR += M/2;
				startC += M/2;
				cnt += 3*(M/2*M/2);
			}
			
			M = M/2;

		}
		
	} // end of main

} // end of class
