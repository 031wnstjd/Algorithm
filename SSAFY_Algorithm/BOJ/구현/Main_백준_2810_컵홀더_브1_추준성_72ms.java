import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_2810_컵홀더_브1_추준성_72ms {
	/*
	 * <아이디어>
	 * 1. 항상 *S 또는 *LL으로 시작하므로 컵홀더의 개수 cnt는 1로 초기화
	 * 2. 이후, S를 만나도, L을 만나도 사람 오른쪽에 컵홀더가 항상 존재하므로 cnt++ (하지만, L을 만났을 땐, 인덱스를 2씩 건너 뛰어야함)
	 * 3. 만약, 컵홀더의 개수(cnt)가 사람 수(N)보다 많다면 사람 수 출력
	 * 4. 사람 수가 컵홀더의 개수보다 많다면 컵홀더의 개수 출력 
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 1;
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 'S') cnt++;
			else {
				cnt++;
				i++; // 'L'이면 두 칸씩 건너 뜀
			}
		}
		
		// 상황을 두 가지 케이스로 나누어 생각하여 각 상황에 맞게끔 결과를 출력 (컵홀더의 개수와 사람 수 둘 중에 어떤 것이 큰지에 따라 컵홀더에 컵을 놓을 수 있는 최대 사람의 수를 정의하는 게 달라짐)
		if(cnt > N) System.out.print(N); // 컵홀더의 개수보다 사람 수가 적으면 사람 수 출력
		else System.out.print(cnt); // 컵홀더의 개수가 사람 수보다 적으면 컵홀더 개수 출력 
	}

}
