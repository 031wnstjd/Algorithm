import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_백준_11286_절댓값힙_실1_추준성_276ms {
	/*
	 * N <= 100,000 - O(N^2)이면 터짐 => 우선순위큐 활용 => O(logN)
	 */
	static class Number implements Comparable<Number> {
		int num;

		public Number(int num) {
			super();
			this.num = num;
		}

		@Override
		public int compareTo(Number o) {
			int abs1 = Math.abs(this.num);
			int abs2 = Math.abs(o.num);
			return abs1 - abs2 == 0 ? this.num - o.num : abs1 - abs2; // 오름차순 정렬 
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Number> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) pq.add(new Number(x));
			else {
				Number number = pq.poll();
				if(number != null) sb.append(number.num).append("\n");
				else sb.append(0).append("\n");
			}
		}
		System.out.print(sb.toString());
	} // end of main

} // end of class
