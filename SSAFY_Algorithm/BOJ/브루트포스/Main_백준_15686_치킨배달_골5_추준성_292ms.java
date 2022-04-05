import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_15686_치킨배달_골5_추준성_292ms {
	private static int M;
	private static int N;
	private static LinkedList<int[]> chickenPos;
	private static LinkedList<int[]> housePos;
	private static int[][] selectedPos;
	private static int min = Integer.MAX_VALUE;
	/*
	 * 1. 입력 받을 때 치킨집의 좌표들을 배열에 담고, 집의 좌표들도 배열에 담음
	 * 2. 조합을 통해 치킨집의 좌표들 중 M개를 뽑아 각 치킨집 좌표에 대하여 이전에 저장해 둔 집의 좌표들과의 치킨 거리를 계산하고, 이를 합하여 도시의 치킨 거리를 구함
	 * 3. 계산한 치킨 거리의 합을 최솟값과 비교해가며 최솟값을 갱신
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chickenPos = new LinkedList<>(); // 치킨집 좌표
		housePos = new LinkedList<>(); // 집 좌표
		selectedPos = new int[M][];
		
		// 집의 좌표, 치킨 집의 좌표 입력 받기
		int temp;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				 temp = Integer.parseInt(st.nextToken());
				 if(temp == 1) {
					 housePos.add(new int[] {i, j}); // 집의 좌표를 담음
				 }
				 if(temp == 2) { 
					 chickenPos.add(new int[] {i, j}); // 치킨집의 좌표를 담음
				 }
			}
		}
		
		comb(0, 0);
		
		System.out.println(min);
		
		
	} // end of main
	
	public static void comb(int depth, int start) {
		if(depth == M) { // 치킨집 M개를 뽑으면, 각 집의 좌표로부터의 치킨 거리 계산하고 합함
			int totalDist = 0; // 치킨 거리 합
			// 각 집의 좌표로부터의 치킨 거리 계산 & 합하기
			int temp = 0;
			for (int i = 0, endH = housePos.size(); i < endH; i++) {
				int chickenDist = Integer.MAX_VALUE; // 각 집의 좌표로부터의 치킨 거리
				for (int j = 0; j < M; j++) {
					temp = Math.abs(housePos.get(i)[0] - selectedPos[j][0]) + Math.abs(housePos.get(i)[1] - selectedPos[j][1]);
					chickenDist = Math.min(chickenDist, temp);
				}
				totalDist += chickenDist; // 도시의 치킨 거리
			}
			
			// 치킨 거리 최솟값 갱신
			min = Math.min(min, totalDist);
			return;
		}
		
		for (int i = start; i < chickenPos.size() ; i++) {
			selectedPos[depth] = chickenPos.get(i);
			comb(depth+1 , i+1);
		}
	} // end of method comb
	
} // end of class
