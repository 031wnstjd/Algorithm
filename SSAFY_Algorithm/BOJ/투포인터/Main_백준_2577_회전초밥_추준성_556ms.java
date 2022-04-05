import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2577_회전초밥_추준성_556ms {
	/*
	 * 
	 * <아이디어>
	 * - 슬라이딩 윈도우 기법
	 * - 회전을 %(모듈러 연산)으로 구현
	 * 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] arr = new int[N]; // 벨트에 놓인 초밥 번호 저장
		int[] isSelected = new int[d+1]; // 각 번호에 해당하는 초밥을 몇 개 골랐는지 여부
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 슬라이딩 윈도우 기법
		// 0~k-1번째 초밥을 고르는 경우를 우선적으로 체크
		int num = 1; // 각 슬라이드 마다의 초밥 가짓수  (서비스 초밥은 미리 땡겨씀)
		for (int i = 0; i < k; i++) {
			if(isSelected[arr[i]] == 0) num++; // 선택된 적 없으면 가짓수 증가
			isSelected[arr[i]]++;
		}
		
		int max = num;
		
		// 한 칸씩 밀면서 num, max값 갱신
		for (int i = 1; i < N; i++) {
			if(max <= num) {
				if(isSelected[c] == 0) max = num + 1;
				else max = num;
			}
			
			// 왼쪽 (사라짐)
			isSelected[arr[i-1]]--; // arr[i-1]에 해당하는 숫자를 하나 줄임(슬라이드에서 벗어났으므로)
			if(isSelected[arr[i-1]] == 0) num--;// 이후 arr[i-1]이 0이면 초밥 가짓수를 1 줄임
			
			// 오른쪽 (추가됨)
			if(isSelected[arr[(i+k-1)%N]] == 0) num++; // 인덱스가 N-1을 넘어가면 모듈러 연산으로 회전 처리 
			isSelected[arr[(i+k-1)%N]]++;
			
		}
		
		System.out.print(max);
		
	} // end of main

} // end of class
