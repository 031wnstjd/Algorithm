import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1592_영식이와친구들_브2_추준성_124ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] receiveCnt = new int[N+1]; // 1~N번 사람이 공을 받은 횟수 카운트
		receiveCnt[1]++; // 가장 먼저 1번 자리에 앉은 사람이 공을 받음
		int totalThrowCnt = 0; // 공을 던진 총 횟수
		
		int x = 0;
		int getter = 1; // 맨 처음 공을 받은 사람
		while(true) {
			x = receiveCnt[getter]; // 현재 공을 받은 사람의 공을 받은 횟수
			if(x < M) {
				totalThrowCnt++;
				if(x % 2 != 0) { // 공을 받은 횟수가 홀수면
					int temp = (getter + L) % N;
					if(temp == 0) {
						getter = N;		// 시계 방향으로 L번째 있는 사람을 getter로 설정
					} else {
						getter = temp;
					}
					receiveCnt[getter]++;
				} else { // 공을 받은 횟수가 짝수면
					if(getter - L <= 0) {
						getter = N + (getter - L); // 반시계 방향으로 돌린 게 음수가 됐을 때를 따로 구분지어서 연산
					} else {
						getter = getter - L;						
					}
					receiveCnt[getter]++;
				}
			} else { // 공을 M번 이상 받은 사람이 존재하게 되면 while문 탈출
				break;
			}
		} // end of while
		
		System.out.print(totalThrowCnt);
	} // end of main
	
} // end of class
