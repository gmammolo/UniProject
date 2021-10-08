package LFT.A2016.cap1;
 public class Es1Comma5_Code {
	public static boolean scan(String s)
	{
		int state = 0;
		int i = 0;
		while(state >= 0 && i < s.length()) {
		final char ch = s.charAt(i++);
		switch(state) {
			 case 0: 
				 if(  ch == 'A' ||  ch == 'B' ||  ch == 'C' ||  ch == 'D' ||  ch == 'E' ||  ch == 'F' ||  ch == 'G' ||  ch == 'H' ||  ch == 'I' ||  ch == 'J' ||  ch == 'K' ) 
					 state = 1;
				 else if(  ch == 'L' ||  ch == 'M' ||  ch == 'N' ||  ch == 'O' ||  ch == 'P' ||  ch == 'Q' ||  ch == 'R' ||  ch == 'S' ||  ch == 'T' ||  ch == 'U' ||  ch == 'V' ||  ch == 'W' ||  ch == 'X' ||  ch == 'Y' ||  ch == 'Z' ) 
					 state = 2;
				 else 
					 state = -1; 
				 break; 
			 case 1: 
				 if(  ch == ' ' ) 
					 state = 3;
				 else if(  ch == '0' ||  ch == '2' ||  ch == '4' ||  ch == '6' ||  ch == '8' ) 
					 state = 5;
				 else if(  ch == 'a' ||  ch == 'b' ||  ch == 'c' ||  ch == 'd' ||  ch == 'e' ||  ch == 'f' ||  ch == 'g' ||  ch == 'h' ||  ch == 'i' ||  ch == 'j' ||  ch == 'k' ||  ch == 'l' ||  ch == 'm' ||  ch == 'n' ||  ch == 'o' ||  ch == 'p' ||  ch == 'q' ||  ch == 'r' ||  ch == 's' ||  ch == 't' ||  ch == 'u' ||  ch == 'v' ||  ch == 'w' ||  ch == 'x' ||  ch == 'y' ||  ch == 'z' ) 
					 state = 1;
				 else if(  ch == '1' ||  ch == '3' ||  ch == '5' ||  ch == '7' ||  ch == '9' ) 
					 state = 6;
				 else 
					 state = -1; 
				 break; 
			 case 2: 
				 if(  ch == 'a' ||  ch == 'b' ||  ch == 'c' ||  ch == 'd' ||  ch == 'e' ||  ch == 'f' ||  ch == 'g' ||  ch == 'h' ||  ch == 'i' ||  ch == 'j' ||  ch == 'k' ||  ch == 'l' ||  ch == 'm' ||  ch == 'n' ||  ch == 'o' ||  ch == 'p' ||  ch == 'q' ||  ch == 'r' ||  ch == 's' ||  ch == 't' ||  ch == 'u' ||  ch == 'v' ||  ch == 'w' ||  ch == 'x' ||  ch == 'y' ||  ch == 'z' ) 
					 state = 2;
				 else if(  ch == ' ' ) 
					 state = 4;
				 else if(  ch == '0' ||  ch == '2' ||  ch == '4' ||  ch == '6' ||  ch == '8' ) 
					 state = 6;
				 else if(  ch == '1' ||  ch == '3' ||  ch == '5' ||  ch == '7' ||  ch == '9' ) 
					 state = 7;
				 else 
					 state = -1; 
				 break; 
			 case 3: 
				 if(  ch == ' ' ) 
					 state = 3;
				 else if(  ch == 'A' ||  ch == 'B' ||  ch == 'C' ||  ch == 'D' ||  ch == 'E' ||  ch == 'F' ||  ch == 'G' ||  ch == 'H' ||  ch == 'I' ||  ch == 'J' ||  ch == 'K' ||  ch == 'L' ||  ch == 'M' ||  ch == 'N' ||  ch == 'O' ||  ch == 'P' ||  ch == 'Q' ||  ch == 'R' ||  ch == 'S' ||  ch == 'T' ||  ch == 'U' ||  ch == 'V' ||  ch == 'W' ||  ch == 'X' ||  ch == 'Y' ||  ch == 'Z' ) 
					 state = 1;
				 else 
					 state = -1; 
				 break; 
			 case 4: 
				 if(  ch == ' ' ) 
					 state = 4;
				 else if(  ch == 'A' ||  ch == 'B' ||  ch == 'C' ||  ch == 'D' ||  ch == 'E' ||  ch == 'F' ||  ch == 'G' ||  ch == 'H' ||  ch == 'I' ||  ch == 'J' ||  ch == 'K' ||  ch == 'L' ||  ch == 'M' ||  ch == 'N' ||  ch == 'O' ||  ch == 'P' ||  ch == 'Q' ||  ch == 'R' ||  ch == 'S' ||  ch == 'T' ||  ch == 'U' ||  ch == 'V' ||  ch == 'W' ||  ch == 'X' ||  ch == 'Y' ||  ch == 'Z' ) 
					 state = 2;
				 else 
					 state = -1; 
				 break; 
			 case 5: 
				 if(  ch == '1' ||  ch == '3' ||  ch == '5' ||  ch == '7' ||  ch == '9' ) 
					 state = 8;
				 else 
					 state = -1; 
				 break; 
			 case 7: 
				 if(  ch == '0' ||  ch == '2' ||  ch == '4' ||  ch == '6' ||  ch == '8' ) 
					 state = 9;
				 else 
					 state = -1; 
				 break; 
			 case 8: 
				 if(  ch == '0' ||  ch == '2' ||  ch == '4' ||  ch == '6' ||  ch == '8' ) 
					 state = 5;
				 else 
					 state = -1; 
				 break; 
			 case 9: 
				 if(  ch == '1' ||  ch == '3' ||  ch == '5' ||  ch == '7' ||  ch == '9' ) 
					 state = 7;
				 else 
					 state = -1; 
				 break; 
			}
		}
		return state == 5;
		}
	public static void main(String[] args)
	{
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}

