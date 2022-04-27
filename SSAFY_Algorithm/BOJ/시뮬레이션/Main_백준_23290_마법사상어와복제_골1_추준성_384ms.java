import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_23290_마법사상어와복제_골1_추준성_384ms {
	/*
	 * - 이전 시간 초과 이유 : ArrayList, LinkedList의 remove 메소드를 사용하여 fishList에서 해당하는 물고기를 제거하려고 했음 (탐색 시간이 너무 오래 걸림, 오버헤드 큼)
	 * - 크기가 '정해진' 반복은 for문으로 해당 크기만큼 반복하기
	 * 
	 * <정리>
	 * - 빠뜨리기 쉬운 조건 1. 리스트에 포함된 '객체까지 복사'하려면 얕은 복사가 아닌 '깊은 복사'를 활용해야 함 - 'Cloneable 인터페이스'의 clone 메소드 오버라이드
	 * - 빠뜨리기 쉬운 조건 2. 상어의 이동 경로 중 '물고기가 존재하는 곳'만 냄새 처리
	 * - 빠뜨리기 쉬운 조건 3. 상어가 방문한 곳은 먹이를 이미 먹었으므로, 다시 방문할 때 카운트 X
	 * 
	 * - 구현 팁 1. 해당 문구(구현부)를 어떡해야 가장 '시간효율적'으로 짤 수 있는지 '자료구조/방법' 고민하기
	 * - 구현 팁 2. 상대 시간이 아닌 '절대 시간'을 냄새 정보로 할당하여 비교 처리
	 */
	
	static int[] shkdr = {-1, 0, 1, 0}; // 상 좌 하 우
	static int[] shkdc = {0,-1, 0, 1};
	
	static int[] fshdr = {0,-1,-1,-1, 0, 1, 1, 1}; 
	static int[] fshdc = {-1,-1, 0, 1, 1, 1, 0,-1};
	private static ArrayList<Fish>[][] fishMap;
	private static ArrayList<Fish> copyList;
	private static ArrayList<Fish> fishList;
	private static int maxFeed;
	private static int[] maxFeedDir;
	private static Shark shark;
	private static int[][] smellMap;
	
	static class Shark {
		int r;
		int c;
		public Shark(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static class Fish implements Cloneable{
		int r;
		int c;
		int d;
		public Fish(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		protected Fish clone() throws CloneNotSupportedException {
			return (Fish) super.clone();
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		fishList = new ArrayList<>();
		copyList = new ArrayList<>();
		smellMap = new int[4][4]; // 냄새 표시 (값 == 생성 회차)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken()) - 1;
			int fc = Integer.parseInt(st.nextToken()) - 1;
			int fd = Integer.parseInt(st.nextToken()) - 1; // 물고기 방향
			fishList.add(new Fish(fr, fc, fd));
		}
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		shark = new Shark(sr, sc); // shark는 한 마리이므로 한 객체만으로 위치 업데이트
		
		fishMap = new ArrayList[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				fishMap[i][j] = new ArrayList<Fish>();
			}
		}
		
		// S번 시행
		for (int step = 1; step <= S; step++) {
			copyFish(); // 초기 물고기 복사
			moveFish(); // 물고기 이동
			moveShark(step); // 상어 이동
			removeSmell(step); // 냄새 제거
			copyMapToList(); // 업데이트된 Map 정보를 fishList로 업데이트
			copyMagic(); // 복제 마법 적용 (copyList 정보를 fishList로 복제)
		} // end of for S
			
		System.out.print(fishList.size());
	} // end of main
	
	static void copyFish() throws CloneNotSupportedException {
		for (Fish fish : fishList) {
			copyList.add(fish.clone()); // (빠뜨리기 쉬운 조건 1 - 리스트에 포함된 객체까지 복사하려면 얕은 복사가 아닌 깊은 복사를 활용해야 함 - Cloneable 인터페이스 메소드 오버라이드)
		}
	}
	
	static void moveFish() {
		for (Fish fish : fishList) {
			int r = fish.r;
			int c = fish.c;
			int d = fish.d;
					
			boolean isMove = false;
			for (int i = 0; i < 8; i++) {
				if(d == -1) d = 7;
				int nr = r + fshdr[d];
				int nc = c + fshdc[d];
				if(0 <= nr && nr < 4 && 0 <= nc && nc < 4 && smellMap[nr][nc] == 0 && (nr != shark.r || nc != shark.c)) {
					fish.r = nr;
					fish.c = nc;
					fish.d = d;
					fishMap[nr][nc].add(fish); // map 업데이트
					isMove = true;
					break;
				}
				d--;
			}
			if(!isMove) fishMap[r][c].add(fish);
		}
	}
	
	static void moveShark(int step) {
		int[] selected = new int[3];
		maxFeed = -1;
		maxFeedDir = new int[3]; // 최대 먹이를 먹을 수 있는 위치 정보 저장
		setMaxFeedDir(0, selected);
		int nr = shark.r;
		int nc = shark.c;
		for (int i = 0; i < 3; i++) { // 물고기 제거 & 냄새 남기기
			nr += shkdr[maxFeedDir[i]];
			nc += shkdc[maxFeedDir[i]];
			if(fishMap[nr][nc].size() != 0) { // 물고기가 존재하는 곳에서만 실행!! (빠뜨리기 쉬운 조건 2 - '물고기가 존재하는 곳'만 냄새 처리)
				fishMap[nr][nc].clear(); // 해당위치 물고기 모두 삭제 (구현 팁 1 - 해당 문구(구현부)를 어떡해야 가장 시간효율적으로 짤 수 있는지 자료구조/방법 고민하기)
				smellMap[nr][nc] = step; // 냄새 남기기
			}
		}
		shark.r = nr; // 상어 이동 (원본을 변경)
		shark.c = nc;
	}
	
	static void setMaxFeedDir(int depth, int[] selected) {
		if(depth == 3) {
			int fishCnt = 0; // 먹은 물고기의 수 초기화
			int nr = shark.r; // 현재 상어 위치 초기화
			int nc = shark.c;
			int[] tmpDir = new int[3]; 
			boolean[][] visited = new boolean[4][4];
			for (int i = 0; i < 3; i++) {
				int setDir = selected[i];
				nr += shkdr[setDir];
				nc += shkdc[setDir];
				
				if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) return; // 범위 밖으로 나가면 리턴 (이동 불가)
				
				if(!visited[nr][nc]) fishCnt += fishMap[nr][nc].size(); // 해당 위치에 있는 물고기 모두 먹기 (빠뜨리기 쉬운 조건 3 - 방문한 곳은 먹이를 이미 먹었으므로 카운트 X)
				
				visited[nr][nc] = true;
				tmpDir[i] = setDir;
			}
			if(maxFeed < fishCnt) { // 물고기를 기존 최대 보다 많이 먹을 때 갱신
				maxFeed = fishCnt;
				for (int i = 0; i < 3; i++) {
					maxFeedDir[i] = tmpDir[i]; // 최대 먹이를 먹을 수 있는 방향 정보를 갱신/저장
				}
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			selected[depth] = i;
			setMaxFeedDir(depth+1, selected);
		}
	}
	
	static void removeSmell(int step) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(smellMap[i][j] == 0) continue;
				if(step == smellMap[i][j] + 2) smellMap[i][j] = 0; // 현재 step과 2만큼 차이날 때 냄새가 사라짐 (구현 팁 2 - 상대 시간이 아닌 절대 시간을 할당하여 비교 처리)
			}
		}
	}
	
	static void copyMagic() {
		for (Fish fish : copyList) {
			fishList.add(fish);
		}
		copyList.clear();
	}
	
	static void copyMapToList() {
		fishList.clear();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (Fish fish : fishMap[i][j]) {
					fishList.add(fish);
				}
				fishMap[i][j].clear();
			}
		}
	}
	
} // end of class
