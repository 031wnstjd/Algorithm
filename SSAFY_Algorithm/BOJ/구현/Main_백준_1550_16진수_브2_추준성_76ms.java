import java.io.InputStreamReader;

import java.io.BufferedReader;

public class Main_백준_1550_16진수_브2_추준성_76ms {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String hexInput = br.readLine();
		System.out.println(Integer.parseInt(hexInput, 16));
		
	} // end of main

} // end of class
