import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_백준_1966_프린터큐_실3_추준성_164ms {
	
	/*
	 * queue의 최댓값을 찾아서 지속적으로 최댓값부터 poll
	 * queue에 담을 때 {value, idx} 꼴로 담음 (M번째 원소의 값과 같은 여러 원소들 가운데 M번째 원소를 구분짓기 위함)
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int TC = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= TC; testCase++) {
			LinkedList<int[]> queue = new LinkedList<>(); // queue로 활용할 LinkedList (인덱스로 조회해야하기 때문에 Queue가 아닌 LinkedList로 생성)
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken(" "));
			int M = Integer.parseInt(st.nextToken(" "));
			int cnt = 0;
			int ref = 0;
			int refIdx = 0;
			int val = 0;
			int temp;
			int[] tempArr;
			int max;
			int maxIdx;
			
			// 데이터 입력 받기
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				val = Integer.parseInt(st2.nextToken());
				if(i==M) { // M번째 원소의 값과 인덱스 저장
					ref = val;
					refIdx = i;
				}
				queue.offer(new int[] {val, i});
			}
			
			
			// queue가 빌 때까지 최댓값 poll하면서 반복
			while(!queue.isEmpty()) {
				// 최댓값과 최댓값의 인덱스 찾기
				max = 0;
				maxIdx = 0;
				for (int i = 0; i < queue.size(); i++) {
					temp = queue.get(i)[0];
					if(temp > max) { // 최댓값이 여러 개면 가장 앞의 인덱스에 해당하는 값이 최댓값으로 할당됨 (temp>=max로 하면 안 됨)
						max = temp;
						maxIdx = i;
					}
				}
				
				// 최댓값 인덱스 직전까지 poll & offer 반복
				for (int i = 0; i < maxIdx; i++) {
					tempArr = queue.poll();
					queue.offer(tempArr);
				}
				
				// 최댓값 poll
				tempArr = queue.poll();
				cnt++;
				
				// poll한 값의 인덱스가 우리가 찾는 원소라면 while문 탈출
				if(tempArr[1] == refIdx) {
					break;
				}
			}
			sb.append(cnt).append("\n");
		} // end of for testCase
		System.out.println(sb);
	} // end of main
} // end of class
