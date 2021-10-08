package LFT.A2016.cap1;
 public class Es1Comma3_Code {
	public static boolean scan(String s)
	{
		int state = 0;
		int i = 0;
		while(state >= 0 && i < s.length()) {
		final char ch = s.charAt(i++);
		switch(state) {
			 case 0: 
				 if(  ch == 'a' ||  ch == 'b' ||  ch == 'c' ||  ch == 'd' ||  ch == 'e' ||  ch == 'f' ||  ch == 'g' ||  ch == 'h' ||  ch == 'i' ||  ch == 'j' ||  ch == 'k' ||  ch == 'l' ||  ch == 'm' ||  ch == 'n' ||  ch == 'o' ||  ch == 'p' ||  ch == 'q' ||  ch == 'r' ||  ch == 's' ||  ch == 't' ||  ch == 'u' ||  ch == 'v' ||  ch == 'w' ||  ch == 'x' ||  ch == 'y' ||  ch == 'z' ||  ch == 'A' ||  ch == 'B' ||  ch == 'C' ||  ch == 'D' ||  ch == 'E' ||  ch == 'F' ||  ch == 'G' ||  ch == 'H' ||  ch == 'I' ||  ch == 'J' ||  ch == 'K' ||  ch == 'L' ||  ch == 'M' ||  ch == 'N' ||  ch == 'O' ||  ch == 'P' ||  ch == 'Q' ||  ch == 'R' ||  ch == 'S' ||  ch == 'T' ||  ch == 'U' ||  ch == 'V' ||  ch == 'W' ||  ch == 'X' ||  ch == 'Y' ||  ch == 'Z' ||  ch == '0' ||  ch == '2' ||  ch == '4' ||  ch == '6' ||  ch == '8' ||  ch == '1' ||  ch == '3' ||  ch == '5' ||  ch == '7' ||  ch == '9' ) 
					 state = 0;
				 else if(  ch == 'L' ||  ch == 'M' ||  ch == 'N' ||  ch == 'O' ||  ch == 'P' ||  ch == 'Q' ||  ch == 'R' ||  ch == 'S' ||  ch == 'T' ||  ch == 'U' ||  ch == 'V' ||  ch == 'W' ||  ch == 'X' ||  ch == 'Y' ||  ch == 'Z' ||  ch == 'A' ||  ch == 'B' ||  ch == 'C' ||  ch == 'D' ||  ch == 'E' ||  ch == 'F' ||  ch == 'G' ||  ch == 'H' ||  ch == 'I' ||  ch == 'J' ||  ch == 'K' ) 
					 state = 4;
				 else 
					 state = -1; 
				 break; 
			 case 4: 
				 if(  ch == 'a' ||  ch == 'b' ||  ch == 'c' ||  ch == 'd' ||  ch == 'e' ||  ch == 'f' ||  ch == 'g' ||  ch == 'h' ||  ch == 'i' ||  ch == 'j' ||  ch == 'k' ||  ch == 'l' ||  ch == 'm' ||  ch == 'n' ||  ch == 'o' ||  ch == 'p' ||  ch == 'q' ||  ch == 'r' ||  ch == 's' ||  ch == 't' ||  ch == 'u' ||  ch == 'v' ||  ch == 'w' ||  ch == 'x' ||  ch == 'y' ||  ch == 'z' ||  ch == 'A' ||  ch == 'B' ||  ch == 'C' ||  ch == 'D' ||  ch == 'E' ||  ch == 'F' ||  ch == 'G' ||  ch == 'H' ||  ch == 'I' ||  ch == 'J' ||  ch == 'K' ||  ch == 'L' ||  ch == 'M' ||  ch == 'N' ||  ch == 'O' ||  ch == 'P' ||  ch == 'Q' ||  ch == 'R' ||  ch == 'S' ||  ch == 'T' ||  ch == 'U' ||  ch == 'V' ||  ch == 'W' ||  ch == 'X' ||  ch == 'Y' ||  ch == 'Z' ) 
					 state = 4;
				 else 
					 state = -1; 
				 break; 
			}
		}
		return state == 4;
		}
	public static void main(String[] args)
	{
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}

