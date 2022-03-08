import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
/*
 * 입력의 최적화 Scanner => BufferedReader
 * 쪼개는 방법 String.split(정규화표현식	) => StringTokenizer (메모리도, 시간도 절약 가능)
 *
 */
public class Solution_SEWA_5215_햄버거다이어트_D3_추준성 {
	
	private static int N;
	private static int L;
	private static int[][] v;
	private static int maxT;
	/** i:단계, sumT : 맛의 현재 단계까지의 합, sumK : 칼로리의 현재까지 단계까지의 합
	 * 	현재까지 계산한 결과를 매번 가지고 다니자 -> selected[]가 아닌 sumT와 sumK를 매개변수로 추가
	 * */
	public static void dfs(int i, int sumT, int sumK) {
		if (i == N) {
			// 선택한 재료들의 맛의 총합, 칼로리의 총합을 구해서, 칼로리 제한범위 이내에서 최대 맛을 찾기
			if(sumK <= L && maxT < sumT) {
				maxT = sumT;
			}
		} else if (sumK <=L) {
			// 선택함
			dfs(i+1, sumT+v[i][0], sumK+v[i][1]);
			// 선택 안 함
			dfs(i+1, sumT, sumK);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 재료 수 20 - 완탐(부분집합 2^20)으로도 가능하다고 예상할 수 있어야함
			L = Integer.parseInt(st.nextToken()); // 제한칼로리
			v = new int[N][2];
			maxT = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				v[i][0] = Integer.parseInt(st.nextToken());// 맛
				v[i][1] = Integer.parseInt(st.nextToken());// 칼로리
			}
			dfs(0, 0, 0);
			sb.append("#").append(testCase).append(" ").append(maxT).append("\n");
		} // end of for testCase
		System.out.println(sb);
	} // end of main
} // end of class

