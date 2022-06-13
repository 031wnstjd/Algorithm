import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_2562_최댓값_브2_추준성_76ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int maxValue = 0;
		int maxValueIdx = 0;
		for (int i = 1; i <= 9; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if(maxValue >= tmp) continue;
			maxValue = tmp;
			maxValueIdx = i;
		}
		
		System.out.println(maxValue);
		System.out.println(maxValueIdx);
		
	} // end of main

} // end of class
