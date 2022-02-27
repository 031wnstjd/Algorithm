import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_1225_암호생성기_D3_추준성 {
	public static void main(String[] args) throws Exception{
		
		Queue<Integer> queue = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = "";
		try {
		while((str = br.readLine()) != null) {
//			if(str.equals("")) {
//				break;
//			}
			int TC = Integer.parseInt(str);
			StringTokenizer st = new StringTokenizer(br.readLine());
			sb.append("#").append(TC).append(" ");
			// queue에 원소 담음
			for (int i = 0; i < 8; i++) {
				queue.offer(Integer.parseInt(st.nextToken(" ")));
			}
			// 암호 생성시까지 반복
			int num = 1;
			while(num > 0) {
				for (int i = 1; i <= 5; i++) {
					// 암호가 생성되기 전이면 이를 반복 실행
					num = queue.poll() - i;
					
					if(num <= 0) {
						queue.offer(0);
						while(!queue.isEmpty()) {
							sb.append(queue.poll()).append(" ");
						}
						break;
					} else {
						queue.offer(num);							
					}
				}
			}
			sb.append("\n");
		} // end of while testCase
		} catch (Exception e) {			
		
		}
		System.out.print(sb);
	} // end of main
} // end of class
