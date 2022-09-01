import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_6603_로또_실2_추준성_108ms {

	private static Integer k;
	private static int[] nums;
	private static int[] outputs;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str.equals("0")) {
				System.out.print(sb.toString());
				System.exit(0);// 0이 나오면 끝
			}
			StringTokenizer st = new StringTokenizer(str);
			k = Integer.valueOf(st.nextToken());
			outputs = new int[6];
			nums = new int[k];
			for (int i = 0; i < k; i++) {
				nums[i] = Integer.valueOf(st.nextToken());
			}
			comb(0, 0);
			sb.append("\n");
		}
	} // end of main

	private static void comb(int cnt, int start) {
		if(cnt == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(outputs[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < k; i++) {
			outputs[cnt] = nums[i];
			comb(cnt+1, i+1);
		}
	}

} // end of class
