import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_백준_2961_도영이가만든맛있는음식_실1_추준성 {
	
	private static int N;
	private static int[][] ingredient;
	private static boolean[] isSelected;
	private static int min;
	/*
	 * 1.재료의 신맛은 곱으로 처리, 쓴맛은 합으로 처리
	 * 2.(신맛 - 쓴맛)이 최소가 되도록 만들기
	 * 3.부분집합으로 모든 재료를 선택하는 경우의 수를 구하고, 해당 경우의 수에 대응되는 (신맛-쓴맛)을 구한 뒤, 최솟값을 지속적으로 갱신
	 * 4.재료를 적어도 하나 사용해야하므로, 공집합은 제외
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ingredient = new int[N][2];
		isSelected = new boolean[N];
		// 재료의 신맛, 쓴맛 정보를 배열에 담기
		for (int i = 0; i < ingredient.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			ingredient[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		min = Integer.MAX_VALUE;
		subset(0);
		System.out.print(min);
		
	} // end of main
	public static void subset(int cnt) {
		if(cnt == N) {
			int sour = 1; // 신맛 점수
			int bitter = 0; // 쓴맛 점수
			int result = 0;
			int count = 0;
			
			for (int i = 0; i < ingredient.length; i++) {
				if(isSelected[i] == true) { // 선택된 재료들만 뽑음
					sour *= ingredient[i][0]; // 신맛은 곱
					bitter += ingredient[i][1]; // 쓴맛은 더함
				}
			}
			
			for (int i = 0; i < ingredient.length; i++) {
				if(isSelected[i] == false) {
					count++; // 뽑히지 않은 개수 카운트
				}
			}
			
			if(count != N) { // 재료를 적어도 하나 사용해야하므로 공집합은 제외 
				result = Math.abs(sour-bitter);
				if(result < min) {
					min = result;
				}
			}
			return;
		}
		
		// 선택
		isSelected[cnt] = true;
		subset(cnt+1);
		// 비선택
		isSelected[cnt] = false;
		subset(cnt+1);
		
		
	} // end of method

} // end of class
