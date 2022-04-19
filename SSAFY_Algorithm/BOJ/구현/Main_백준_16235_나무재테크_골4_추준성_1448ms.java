import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_16235_나무재테크_골4_추준성_1448ms {
	/*
	 * 1. 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
	 * 2. 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다. 
	 * 3. 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다. (나이순 정렬 - pq 활용)
	 * 4. 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
	 * 5. 여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.(소수점 아래는 버린다.)
	 * 6. 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
	 * 7. 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
	 * 
	 * 구하고자 하는 것 : K년이 지난 후 상도의 땅에 살아있는 나무의 개수
	 * 
	 * <설계>
	 * 0. 나무 클래스 : r, c, age
	 * 1. 나무를 관리할 자료구조 : pQueue (양분을 줄 때, 나이가 어린 나무부터 줌)
	 * 	  - 핵심 : pQueue에 담으면 원래 다음 순서인 원소가 나오지 않을 수 있으므로 tmpList를 생성하여 따로 담아놓고, 로직이 끝난 이후에 pQueue를 업데이트
	 * 2. 봄에 나무가 죽으면, 해당 나무가 있던 위치에 (해당 나무의 나이 / 2) 만큼 양분을 더함
	 * 3. 가을엔 pQueue에서 하나씩 꺼내면서 나무의 나이가 5의 배수일 때, 8방탐색으로 번식
	 * 
	 */
	
	static int[] dr = {0, 0, 1,-1, 1, 1,-1,-1};
	static int[] dc = {1,-1, 0, 0, 1,-1, 1,-1};
	private static PriorityQueue<Wood> pq;
	private static LinkedList<Wood> deathList;
	private static int[][] add;
	private static int[][] map;
	private static int n;
	
	static class Wood implements Comparable<Wood>{
		int r;
		int c;
		int age;
		
		public Wood(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Wood o) {
			return this.age - o.age; // 오름차순
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 1 <= n <= 10
		int m = Integer.parseInt(st.nextToken()); // 1 <= m <= 100
		int k = Integer.parseInt(st.nextToken()); // 1 <= k <= 1000
		
		add = new int[n][n]; // 겨울에 주는 양분의 양 ( 1<= energy[i][j] <= 100 )
		map = new int[n][n]; // 양분의 양
		pq = new PriorityQueue<>();
		deathList = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			pq.add(new Wood(r, c, age));
		}
		
		for (int i = 0; i < n; i++) {
			Arrays.fill(map[i], 5); // map 값을 5로 초기화
		}
		
		while(k-- > 0) {
			// 봄
			spring();
			// 여름
			summer();
			// 가을
			fall();
			// 겨울
			winter();
		}
		
		System.out.print(pq.size());
	} // end of main
	
	static void spring() {
		LinkedList<Wood> tmpList = new LinkedList<>();
		while(!pq.isEmpty()) {
			int pqSize = pq.size();
			while(pqSize-- > 0) {
				Wood cur = pq.poll();
				int r = cur.r;
				int c = cur.c;
				int age = cur.age;
				
				if(map[r][c] < age) {
					deathList.add(new Wood(r, c, age)); // 죽은 나무들을 deathList에 담아 놓음 (여름에 양분처리)
					continue; // 양분보다 나이가 많으면 죽음
				}
				
				map[r][c] -= age;
				age++;
				tmpList.add(new Wood(r, c, age)); // pq에 담으면 우선순위를 방해할 수 있으므로 tmpList에 담아놨다가 나중에 pq에 업데이트
			}
			break;
		}
		
		while(!tmpList.isEmpty()) pq.add(tmpList.poll());
	}
	
	static void summer() {
		while(!deathList.isEmpty()) {
			Wood cur = deathList.poll();
			map[cur.r][cur.c] += (cur.age/2);
		}
	}
	
	static void fall() {
		LinkedList<Wood> tmpList = new LinkedList<>();
		while(!pq.isEmpty()) {
			int pqSize = pq.size();
			while(pqSize-- > 0) {
				Wood cur = pq.poll();
				int r = cur.r;
				int c = cur.c;
				int age = cur.age;
				
				tmpList.add(new Wood(r, c, age));
				
				if(age % 5 != 0) continue;
				
				for (int i = 0; i < 8; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					
					tmpList.add(new Wood(nr, nc, 1));
				}
			}
			break;
		}
		
		while(!tmpList.isEmpty()) pq.add(tmpList.poll());
	}
	
	static void winter() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] += add[i][j];
			}
		}
	}

} // end of class
