import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_정올_1828_냉장고_추준성_140ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][2];
		
		// 입력된 원소들 배열에 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			map[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		// 최고온도 기준 오름차순 정렬
		Arrays.sort(map, (o1, o2) ->{ // 람다 표현식
			return o1[1]-o2[1]; 
		});
		
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		// 최소 냉장고 수 구하기
		int cnt = 1; // 적어도 냉장고 하나는 필요
		int ul = map[0][1]; // 첫 번째 원소의 상한 온도
		for (int i = 1; i < map.length; i++) {
			// 현재 원소의 하한 온도가 이전에 저장된 원소의 상한 온도보다 크면 카운트 & 현재 원소의 상한 온도를 ul에 할당 
			if(ul < map[i][0]) {
				ul = map[i][1];
				cnt++;
			}
		}
		
		System.out.print(cnt);
		
	} // end of main

} // end of class
