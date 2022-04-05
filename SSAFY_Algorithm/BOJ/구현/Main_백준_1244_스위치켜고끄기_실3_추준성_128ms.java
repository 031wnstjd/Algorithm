import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1244_스위치켜고끄기_실3_추준성_128ms {
	
	/*
	 * 남학생 : 자신이 받은 수의 배수들의 스위치 상태를 바꿈
	 * 여학생 : 자신이 받은 수를 기준으로 좌우 대칭을 이루는 스위치들의 상태를 바꿈 (최대한 많이 대칭을 이뤄야함, 만약 대칭이 없다면 자기 자신의 상태를 바꿈) 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 스위치의 개수 (1<=N<=100)
		
		int[] switchState = new int[N+1]; // 스위치 상태를 담을 배열
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			switchState[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine()); // 학생수 (1<=M<=100)
		
		// 테스트 케이스
		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			int gender = Integer.parseInt(st2.nextToken());
			int switchNum = Integer.parseInt(st2.nextToken());
			
			if(gender == 1) { // 남학생이라면
				int coefficient = 1;
				while(switchNum * coefficient <= N) {
					int temp = switchState[switchNum * coefficient];
					if(temp == 0) {
						switchState[switchNum * coefficient] = 1;
					} else {
						switchState[switchNum * coefficient] = 0;
					}
					coefficient++;
				}
			}
			
			if(gender == 2) { // 여학생이라면
				int j = 0;
				while(switchNum-j >= 1 && switchNum+j <= N) {
					if(switchState[switchNum - j] == switchState[switchNum + j]) { // 대칭이면
						int temp = switchState[switchNum-j];
						if(temp == 0) {
							switchState[switchNum-j] = 1;
							switchState[switchNum+j] = 1;
						} else {
							switchState[switchNum-j] = 0;
							switchState[switchNum+j] = 0;
						}
					} else {
						break;
					}
					j++;
				}
			}
		} // end of for testcase
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(switchState[i]).append(" ");
			if(i % 20 == 0) {
				sb.append("\n");
			}
		}
		
		System.out.print(sb.toString());
		
	} // end of main
} // end of class
