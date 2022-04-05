import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_1158_요세푸스문제_실5_추준성 {
	
	/* 원리
	 * 	// 1. queue가 빌 때까지 반복 실행
	 * while(!queue.isEmpty()){
	 * 	for(int i = 0; i < K-2; i++){
	 *  // 2. K-1번째까지 queue의 뒤로 보냄
	 * 		queue.offer(queue.poll())
	 * 	}
	 * 	// 3. K번째 원소 poll
	 *  queue.poll()
	 * }
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");	
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		LinkedList<Integer> queue = new LinkedList<>();
		ArrayList<Integer> save = new ArrayList<>();
		// queue에 담기
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}

		// 1. queue가 빌 때까지 반복 실행
		while(!queue.isEmpty()) { 
			// 2. K-1번째까지 queue의 뒤로 보냄 (뽑아서 뒤로 보내는 걸 K-1번 반복)
			for(int i = 0; i < K-1; i++){
				queue.offer(queue.poll());
			}
			// 3. K번째 원소 poll해서 save에 저장
			save.add(queue.poll());		
		}
		
		sb.append("<");
		for (int i = 0; i < N; i++) {
			if(i == N-1) {
				sb.append(save.get(i));
			} else {
				sb.append(save.get(i)).append(", ");
			}
		}
		sb.append(">");
		System.out.print(sb.toString());
	} // end of main
} // end of class
