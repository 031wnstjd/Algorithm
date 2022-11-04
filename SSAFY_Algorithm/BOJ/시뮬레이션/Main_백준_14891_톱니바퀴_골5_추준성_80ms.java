import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14891_톱니바퀴_골5_추준성_80ms {
	
	static class Gear {
		int idx;
		char[] poles;
		
		public Gear(int idx, char[] poles) {
			super();
			this.idx = idx;
			this.poles = poles;
		}
	}

	private static Gear[] gears;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gears = new Gear[4];
		for (int i = 0; i < 4; i++) {
			char[] poles = br.readLine().toCharArray(); // 0 : N극, 1 : S극
			gears[i] = new Gear(i, poles);
		}
		
		
		
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());
			
			gearOperation(idx, direction);
		}
		
		int score = 0;
		for (int i = 0; i < 4; i++) {
			int pole = gears[i].poles[0] - '0';
			if(pole == 0) continue; // N극이면
			switch(i) {
				case 0:
					score += 1;
					break;
				case 1:
					score += 2;
					break;
				case 2:
					score += 4;
					break;
				case 3:
					score += 8;
					break;
			}
		}
		
		System.out.print(score);
	} // end of main

	
	private static void gearOperation(int idx, int direction) {
		leftSide(idx-1, -direction);
		rightSide(idx+1, -direction);
		rotation(idx, direction); // 초기 상태에서 회전 여부가 다 결정되고 난 뒤 상태가 바뀜
	}


	private static void rightSide(int idx, int direction) {
		if(idx > 3) return;
		if(gears[idx-1].poles[2] == gears[idx].poles[6]) return;
		rightSide(idx+1, -direction);
		rotation(idx, direction);
	}


	private static void leftSide(int idx, int direction) {
		if(idx < 0) return;
		if(gears[idx+1].poles[6] == gears[idx].poles[2]) return;
		leftSide(idx-1, -direction);
		rotation(idx, direction);
	}


	private static void rotation(int idx, int direction) {
		Gear gear = gears[idx];
		
		if(direction == 1) { // 시계방향
			char tmp = gear.poles[7];
			for (int i = 7; i > 0; i--) {
				gear.poles[i] = gear.poles[i-1];
			}
			gear.poles[0] = tmp;
		} else { // 반시계방향
			char tmp = gear.poles[0];
			for (int i = 0; i < 7; i++) {
				gear.poles[i] = gear.poles[i+1];
			}
			gear.poles[7] = tmp;
		}
	}

} // end of class
