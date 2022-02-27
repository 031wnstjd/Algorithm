import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_4012_요리사_추준성_228ms {
	
	private static int[][] map;
	private static int min;

	/*
	 * 1. 조합 활용 - 식재료 N개중 N/2개를 뽑음
	 * 2. 뽑은 식재료의 인덱스를 활용하여, 이중 for문을 돌려 맛의 정보를 구함
	 * 3. 전체 식재료에서 뽑은 식재료들을 제외한 나머지 식재료들의 배열을 따로 선언하여 이 또한 이중 for문을 돌려 맛의 정보를 구함
	 * 4. 두 맛의 차를 구하고, 최솟값 보다 작으면 이를 최솟값으로 할당
	 * 5. 최솟값 출력
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1]; // 시너지 담는 2차원 배열
			for (int i = 1; i < N+1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j < N+1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] inputs = new int[N];
			int[] outputs = new int[N/2];
			
			for (int i = 0; i < N; i++) {
				inputs[i] = i+1; // 식재료 번호 input에 담음
			}
			
			min = Integer.MAX_VALUE; // min 값 초기화
			
			comb(N, 0, 0, inputs, outputs);
			
			sb.append("#").append(testCase).append(" ").append(min).append("\n");
			
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main
	
	
	public static void comb(int N, int cnt, int start, int[] inputs, int[] outputs) { // 전체 식재료 개수, 뽑는 회차, 
		if(cnt == N/2) { // 절반 개수 만큼 뽑으면 (N은 짝수이므로 가능)
			
			// 뽑히지 않은 식재료들의 배열 생성
			int[] nonSelected = new int[N/2];
			boolean[] isSelected = new boolean[N];  // inputs 중에 어떤 원소가 담겼는지 확인하는 용도
			
			// inputs에서 outputs들의 원소를 제외한 걸 nonSelected 배열에 담음
			for (int i = 0; i < inputs.length; i++) {
				for (int j = 0; j < outputs.length; j++) {
					if(inputs[i] == outputs[j]) { // inputs의 원소와 outputs의 원소가 같으면 선택된 원소
						isSelected[i] = true;
					}
				}
			}
			
			int idx = 0;
			for (int i = 0; i < isSelected.length; i++) {
				if(!isSelected[i]) { // 선택되지 않았으면
					nonSelected[idx++] = inputs[i]; // 선택되지 않은 원소를 담음
				}
			}
			
			int tasteSelected = 0;
			int tasteNonSelected = 0;
			
			for (int i = 0; i < outputs.length; i++) {
				for (int j = 0; j < outputs.length; j++) {
					if(i == j) continue;
					tasteSelected += map[outputs[i]][outputs[j]];
					tasteNonSelected += map[nonSelected[i]][nonSelected[j]];
				}
			}
			
			int diff = Math.abs(tasteSelected - tasteNonSelected); // 두 맛의 차이
			
			if(diff < min) {
				min = diff;
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			outputs[cnt] = inputs[i];
			comb(N, cnt+1, i+1, inputs, outputs);
		}
	} // end of method comb

} // end of class
