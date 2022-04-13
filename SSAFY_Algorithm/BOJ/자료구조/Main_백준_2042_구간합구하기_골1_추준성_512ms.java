import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2042_구간합구하기_골1_추준성_512ms {
	/**
	 * 세그먼트 트리
	 */
	private static long[] nums;
	private static long[] tree;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간의 합을  구하는 횟수
		nums = new long[N];
		tree = new long[N * 4];
		
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		buildRec(1, 0, N-1); // 세그먼트 트리 생성
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) updateRec(b-1, c, 1, 0, N-1); // 세그먼트 트리 업데이트 (인덱스이므로 b-1임에 주의)
			else sb.append(queryRec(b-1, (int)c - 1, 1, 0, N-1)).append("\n"); // 구간합 출력 (인덱스이므로 b-1, c-1임에 주의)
		}
		System.out.print(sb.toString());
	} // end of main
	
	// 세그먼트 트리 생성
	private static long buildRec(int node, int nodeLeft, int nodeRight) {
		if(nodeLeft == nodeRight) return tree[node] = nums[nodeLeft]; // 리프노드이면
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2; // stackOverFlow 방지용 코드 ( 'nodeLeft + nodeRight / 2' 로 짜면 스택오버플로우 발생)
		long leftVal = buildRec(node * 2, nodeLeft, mid);
		long rightVal = buildRec(node * 2 + 1, mid + 1, nodeRight);
		
		return tree[node] = sum(leftVal, rightVal);		
	}

	// 세그먼트 트리 업데이트
	private static long updateRec(int index, long newValue, int node, int nodeLeft, int nodeRight) {
		if(index < nodeLeft || nodeRight < index) return tree[node]; // index 노드이지않거나
		if(nodeLeft == nodeRight) return tree[node] = newValue; // index 노드이거나
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftVal = updateRec(index, newValue, node * 2, nodeLeft, mid);
		long rightVal = updateRec(index, newValue, node * 2 + 1, mid + 1, nodeRight);
		
		return tree[node] = sum(leftVal, rightVal);
	}

	// 세그먼트 트리 구간합 구하기
	private static long queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {
		if(right < nodeLeft || nodeRight < left) return 0; // [left, right] 구간에 포함 되지 않거나
		if(left <= nodeLeft && nodeRight <= right) return tree[node]; // [left, right] 구간에 포함 되거나
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftVal = queryRec(left, right, node * 2, nodeLeft, mid);
		long rightVal = queryRec(left, right, node * 2 + 1, mid + 1, nodeRight);
		
		return sum(leftVal, rightVal);
	}
	
	private static long sum(long x, long y) {
		return x + y;
	}

} // end of class
