import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_1043_거짓말_골4_추준성_84ms {
	/*
	 * <조건 분석>
	 * - 진실을 아는 사람이 포함된 파티에서는 무조건 진실을 이야기해야 함
	 * - 따라서 해당 파티의 구성원들이 포함된 파티 또한 무조건 진실을 이야기해야함
	 * 
	 * <설계>
	 * - 진실을 아는 사람 체크 배열 : boolean[] isKnow
	 * - 파티 조회용 리스트 : ArrayList<>[] partyList
	 * - 각 파티 구성원 조회용 리스트 : ArrayList<Integer> memberList
	 * 
	 * 1. 입력 받을 때 진실을 아는 사람이 파티에 있으면, 모두 isKnow true처리 => 대략 최대 50번 반복
	 * 2. partyList를 탐색하면서 isKnow가 true인 사람이 있으면 카운트(cnt), 아니면 break
	 * 3. 전체 파티의 개수 M에서 cnt를 뺀 값을 출력
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 수
		int M = Integer.parseInt(st.nextToken()); // 파티의 수
		
		boolean[] isKnow = new boolean[N+1]; // 진실을 알고 있는지 여부
		ArrayList<Integer>[] partyList = new ArrayList[M];
		
		st = new StringTokenizer(br.readLine());
		int initNumOfEnemies = Integer.parseInt(st.nextToken());
		for (int i = 0; i < initNumOfEnemies; i++) {
			int person = Integer.parseInt(st.nextToken());
			isKnow[person] = true; // 진실을 앎
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int numOfPerson = Integer.parseInt(st.nextToken());
			boolean isHere = false; 
			ArrayList<Integer> memberList = new ArrayList<>();
			for (int j = 0; j < numOfPerson; j++) {
				int person = Integer.parseInt(st.nextToken());
				memberList.add(person);
			}
			partyList[i] = memberList;
		}
		
		for (int i = 0; i < 50; i++) { // 최대 50번
			for (ArrayList<Integer> memberList : partyList) {
				boolean isHere = false; 
				for (Integer person : memberList) {
					if(isKnow[person]) {
						isHere = true;
						break;
					}
				}
				
				if(!isHere) continue; // 진실을 아는 사람 없으면 continue
				
				for (Integer person : memberList) {
					isKnow[person] = true; // 진실을 아는 사람을 포함한 파티의 구성원들 true처리
				}
			}
		}
		
		int cnt = 0;
		for (ArrayList<Integer> memberList : partyList) {
			for (Integer person : memberList) {
				if(isKnow[person]) {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(M-cnt);
		
	} // end of main

} // end of class
