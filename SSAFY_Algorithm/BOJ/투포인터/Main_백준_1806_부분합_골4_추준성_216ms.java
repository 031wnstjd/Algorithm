import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1806_부분합_골4_추준성_216ms {
	/*
	 * 1. 부분합 배열 만들기
	 * 2. 투포인터로 '부분합 차가 S이상이 되는 경우'를 찾고, 최소 길이 갱신 
	 * => '한 쌍', '두 수' 등의 키워드는 투포인터를 떠올리기
	 * 3. 자기자신의 부분합도 꼭 고려!! => 첫 번째 인덱스의 값을 0으로 비워두기
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] pSum = new int[N+1]; // 1 ~ N번 인덱스 활용
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			pSum[i] = pSum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		// 자기 자신의 값도 부분합으로 고려해야 하므로 맨 처음 인덱스를 1이 아닌 0으로 설정
		/* 1로 잡을 경우 반례 )
		   10 11
		   10 1 1 1 1 1 1 1 1 1
		*/
		int i = 0; // left
		int j = 0; // right
		
		int minLen = Integer.MAX_VALUE;
		while(i <= N && j <= N) {
			int diff = pSum[j] - pSum[i];
			if(diff < S) {
				j++;
				continue;
			}
			minLen = Math.min(minLen, j-i); // 최소 길이 갱신
			i++;
		}
		System.out.print(minLen == Integer.MAX_VALUE ? 0 : minLen);
	} // end of main

} // end of class
