import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14888_연산자끼워넣기_실1_추준성_84ms {
	/*
	 * 1. 완전탐색을 통해 최댓값, 최솟값 찾기
	 */
	private static int N;
	private static int[] numbers;
	private static int[] operators;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		numbers = new int[N];
		operators = new int[4]; // 덧셈개수, 뺄셈개수, 곱셈개수, 나눗셈개수 순
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(1, numbers[0]);
		
		System.out.println(max);
		System.out.println(min);
	} // end of main
	
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;
	public static void perm(int depth, int result) {
		if(depth == N) { // 연산자 개수(==깊이==열 길이)만큼
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for (int j = 0; j < 4; j++) { // 연산자 종류(==너비==행 길이)만큼
			if(operators[j] != 0) {
				operators[j]--; // 방문처리
				if(j == 0) perm(depth+1, result+numbers[depth]);
				if(j == 1) perm(depth+1, result-numbers[depth]);
				if(j == 2) perm(depth+1, result*numbers[depth]);
				if(j == 3) perm(depth+1, result/numbers[depth]);
				operators[j]++; // 방문처리 복구
			}
		}
		
	} // end of method perm
} // end of class
