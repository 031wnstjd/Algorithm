import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Student {
	int no;
	int[] likeList;
	public Student(int no, int[] likeList) {
		super();
		this.no = no;
		this.likeList = likeList;
	}
}

class Place implements Comparable<Place>{
	int r;
	int c;
	int numOfLike;
	int numOfVoid;
	public Place(int r, int c, int numOfLike, int numOfVoid) {
		super();
		this.r = r;
		this.c = c;
		this.numOfLike = numOfLike;
		this.numOfVoid = numOfVoid;
	}
	@Override
	public int compareTo(Place o) {
		return o.numOfLike - this.numOfLike != 0 ? o.numOfLike - this.numOfLike :
			   o.numOfVoid - this.numOfVoid != 0 ? o.numOfVoid - this.numOfVoid :
			   this.r - o.r != 0 ? this.r - o.r : this.c - o.c;
	}
}


public class Main_백준_21608_상어초등학교_골5_추준성_216ms {
	private static Student[][] map;
	private static Student[] studentList;
	private static int N;
	private static int M;

	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	private static ArrayList<Place> placeList;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M =  N * N; // 학생 수
		map = new Student[N][N];
		studentList = new Student[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int s3 = Integer.parseInt(st.nextToken());
			int s4 = Integer.parseInt(st.nextToken());
			studentList[i] = new Student(no, new int[] {s1, s2, s3, s4});
		}
		
		// 1. 자리 배치
		for (int i = 0; i < M; i++) {
			setPlaceList(studentList[i]);
			Collections.sort(placeList);
			Place place = placeList.get(0);
			map[place.r][place.c] = studentList[i];
		}
		
		
		// 2. 만족도 총합 구하기
		System.out.print(getScore());
		
	} // end of main
	
	private static void setPlaceList(Student stud) {
		
		placeList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] != null) continue; // 빈 칸이 아니면 continue
				
				int numOfLike = 0;
				int numOfVoid = 0;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					// 빈 칸이면 빈 칸 수 세기
					if(map[nr][nc] == null) { 
						numOfVoid++;
						continue;
					}
					
					// 빈 칸이 아니면 좋아하는 학생 수 세기
					int adjNo = map[nr][nc].no;
					for (int likeNo : stud.likeList) {
						if(likeNo == adjNo) numOfLike++;
					}
				} // end of for 4방 탐색
				
				placeList.add(new Place(r, c, numOfLike, numOfVoid)); // placeList에 자리 정보 추가
			} // end of for c
		} // end of for r
	}
	
	private static int getScore() {
		int score = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				Student cur = map[r][c];
				
				int numOfLike = 0;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					int adjNo = map[nr][nc].no;
					for (int likeNo : cur.likeList) {
						if(likeNo == adjNo) numOfLike++;
					}
				}
				
				if(numOfLike == 0) continue; // 좋아하는 학생 없으면 다음 학생으로...
				
				switch(numOfLike) {
					case 1:
						score += 1;
						break;
					case 2:
						score += 10;
						break;
					case 3:
						score += 100;
						break;
					case 4:
						score += 1000;
						break;
				}
			}
		}
		
		return score;
	}
	
} // end of class
