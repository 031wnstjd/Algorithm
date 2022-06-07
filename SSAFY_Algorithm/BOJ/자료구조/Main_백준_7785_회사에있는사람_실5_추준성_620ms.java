import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_백준_7785_회사에있는사람_실5_추준성_620ms {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Map<String, String> map = new HashMap<>();
		
		// 1. 로그를 통해 회사에 남아 있는 직원들 선별
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String state = st.nextToken();
			if(state.equals("enter")) { // "enter"이면 name을 key로 하여 map에 추가
				map.put(name, state);
			} else {
				map.remove(name); // "leave"이면 name을 key로 하여 map에서 삭제
			}
		}
		
		// 2. 역사전순으로 정렬 (정렬을 위해 형변환 : Set -> List)
		ArrayList<String> list = new ArrayList<>(map.keySet()); // Set자료형을 ArrayList로 초기화
		Collections.sort(list, Collections.reverseOrder()); // Collections.sort()를 통해 list 정렬
		
		for (String name : list) {
			sb.append(name).append("\n");
		}
		
		System.out.print(sb.toString());
		
	} // end of main

} // end of class 
