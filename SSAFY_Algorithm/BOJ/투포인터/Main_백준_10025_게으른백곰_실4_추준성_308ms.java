import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10025_게으른백곰_실4_추준성_308ms {
	/*
	 * <설계>
	 * 1. 구하고자 하는 것 : K만큼 떨어진 거리 내에 있는 얼음들의 합(최댓값)
	 * 2. 1차원 배열 탐색하면서 중복 연산 제거 => 슬라이딩 윈도우(투포인터)
	 * => 윈도우 크기 2K+1로 고정
	 *
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 얼음 양동이의 수
		int K = Integer.parseInt(st.nextToken()); // 엘버트로부터 떨어진 최대 거리
		int size = 1000000;
		int[] arr = new int[size+1]; // 양동이 위치 좌표 배열
		
		// 입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken()); // 양동이의 얼음의 양
			int x = Integer.parseInt(st.nextToken()); // 양동이의 좌표
			arr[x] = g;
		}
		
		// 초기값(초기 슬라이딩 윈도우 값) 세팅
		int ans = 0;
		int max = 0;
		for (int i = 0; i < 2*K+1; i++) {
			if(i > size) break;
			ans += arr[i];
			max = ans;
		}

		// 슬라이딩 윈도우 적용
		for (int i = 0; i <= size - (2*K+1); i++) {
			ans -= arr[i];
			ans += arr[2*K+1 + i];
			max = Math.max(max, ans);
		}
		
		System.out.print(max);
	} // end of main

} // end of class
