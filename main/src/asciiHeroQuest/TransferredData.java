package asciiHeroQuest;

import java.util.Scanner;

public class TransferredData {
	
	public static final Scanner	USER_INPUT	= new Scanner(System.in);
	
	public static final void promptUserToContinue() {
		Utilities.print("Press 'Enter' to continue", true);
		USER_INPUT.nextLine();
	}
	
}
