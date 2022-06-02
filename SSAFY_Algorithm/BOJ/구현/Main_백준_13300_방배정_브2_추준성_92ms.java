import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_13300_방배정_브2_추준성_92ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] students = new int[6][2]; // 학년, 성별
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()); // 성별
			int Y = Integer.parseInt(st.nextToken())-1; // 학년
			students[Y][S]++;
		}
		
		int roomCnt = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 2; j++) {
				if(students[i][j] % K == 0) roomCnt += (students[i][j] / K); // K로 나누어 떨어질 경우
				else roomCnt += (students[i][j] / K) + 1; // K로 나누어 떨어지지 않는 경우
			}
		}
		System.out.print(roomCnt);
		
	} // end of main

} // end of class
