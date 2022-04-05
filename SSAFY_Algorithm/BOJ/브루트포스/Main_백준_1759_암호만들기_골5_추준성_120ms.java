import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_백준_1759_암호만들기_골5_추준성_120ms {
	/*
	 * 입력된 문자들에서 조합을 통해 L개의 문자를 뽑고, 이들 중 최소  한 개의 모음과 두 개의 자음을 포함하는 모든 경우의 수를 오름차순으로 출력
	 * 주요 변경 사항 : 조합 메서드에 정렬된 배열을 입력으로 넣으면, 재귀적 특성상 결과 또한 정렬된 상태(오름차순으로 입력했으면 오름차순, 내림차순으로 입력했으면 내림차순)로 도출됨
	 */
	private static int L;
	private static int C;
	private static char[] outputs;
	private static char[] inputs;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 암호를 구성하는 문자의 개수
		C = Integer.parseInt(st.nextToken()); // 사용했을 법한 암호 문자 종류의 개수
		inputs = new char[C];
		outputs = new char[L];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		// 입력 문자들을 inputs 배열에 저장
		for (int i = 0; i < C; i++) {
			inputs[i] = st2.nextToken().charAt(0);
		}

		Arrays.sort(inputs); // 입력할 배열을 정렬하고 combination 함수를 돌리면, 정렬된 상태로 조합 결과가 나옴
		combination(0,0);
		System.out.print(sb.toString());
	} // end of main
	
	public static void combination(int cnt, int start) {
		if(cnt == L) {
			// L개의 원소를 가진 모든 경우들 중에 (outputs 배열)
			// 1. 모음 최소 1개, 자음 최소 2개를 만족시키는 배열만을 
			// 2. 오름차순 정렬해서 출력
			int cntVowel = 0;
			int cntConsonant = 0;
			
			// 모음 최소 1개, 자음 최소 2개 검사
			char c;
			for (int i = 0; i < outputs.length; i++) {
				c = outputs[i];
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
					cntVowel++; // 모음을 가지면 모음 개수 카운트
				} else {
					cntConsonant++; // 자음을 가지면 자음 개수 카운트
				}
			}
			
			// 모음 최소 1개, 자음 최소 2개 조건을 만족하면 오름차순 정렬해서 출력
			if(cntVowel >= 1 && cntConsonant >= 2) {
				for (int i = 0; i < outputs.length; i++) {
					sb.append(outputs[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		for (int i = start; i < inputs.length; i++) {
			outputs[cnt] = inputs[i];
			combination(cnt+1, i+1);
		}
	} // end of method
	
} // end of class
