import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1991_트리순회_실1_추준성_76ms {
	/*
	 * 1. 트리 만들기 (Node 클래스 정의)
	 * 2. 프리오더, 인오더, 포스트오더 구현
	 */
	static class Node {
		char root; // 자기 자신의 값 (출력용)
		int left; // 왼쪽 자식 번호
		int right; // 오른쪽 자식 번호
		public Node(char root, int left, int right) {
			super();
			this.root = root;
			this.left = left;
			this.right = right;
		}
	}
	
	private static int N;
	private static Node[] tree;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		tree = new Node[N+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char root = st.nextToken().charAt(0);
			int left = st.nextToken().charAt(0) - 'A' + 1; // 인덱스 값과 알파벳을 동기화
			int right = st.nextToken().charAt(0) - 'A' + 1;
			tree[root - 'A' + 1] = new Node(root, left, right);
		}
		
		preOrder(1); // 루트부터 탐색 시작
		sb.append("\n");
		inOrder(1); // 루트부터 탐색 시작
		sb.append("\n");
		postOrder(1); // 루트부터 탐색 시작
		
		System.out.print(sb.toString());
	} // end of main

	private static void preOrder(int n) {
		char root = tree[n].root;
		int left = tree[n].left;
		int right = tree[n].right;
		
		sb.append(root);
		if(left != -18) preOrder(left); // '.'이 아닐 때 (== 기저조건)
		if(right != -18) preOrder(right);
	}
	private static void inOrder(int n) {
		char root = tree[n].root;
		int left = tree[n].left;
		int right = tree[n].right;
		
		if(left != -18) inOrder(left);
		sb.append(root);
		if(right != -18) inOrder(right);
	}
	private static void postOrder(int n) {
		char root = tree[n].root;
		int left = tree[n].left;
		int right = tree[n].right;
		
		if(left != -18) postOrder(left); 
		if(right != -18) postOrder(right);
		sb.append(root);
	}


} // end of class
