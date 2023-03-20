package asciiHeroQuest;

public class Main {
	
	private static final String[] USER_ACTIONS	= {"Quest", "Shop", "Inventory", "Save"};
	
	private static boolean GAME_IN_PROGRESS	= true;
	
	public static void main(String[] args) {
		Player mainPlayer	= DataStore.getPlayerData();
		if (mainPlayer.getPlayerLevel() == 0) {
			QuestLine.gameOpening(mainPlayer);
		}
		if (mainPlayer.getPlayerLevel() == 5) {
			promptUserRestart();
			return;
		}
//		testCase(mainPlayer);
		handleUserAction(mainPlayer);
	}
	
	//This function tests certain aspects of the game
//	private static void testCase(Player mainPlayer) {
//		mainPlayer.modifyAmountGold(28100);
//		mainPlayer.setPlayerLevel(4);
//	}
	
	//Prompts user if they want to restart the game if they've already beaten it
	private static void promptUserRestart() {
		Utilities.print("You've already beaten the game, do you wish to restart?", true);
		Utilities.print("Enter (y) to restart or (n) to quit", true);
		if (TransferredData.USER_INPUT.nextLine().toLowerCase().equals("y")){
			Main.onPlayerDeath();
			return;
		}
	}
	
	//This controls the direction of the game from user input, if the user wants to go to shop or battle etc..
	private static void handleUserAction(Player mainPlayer) {
		int userAction;
		while (GAME_IN_PROGRESS) {
			promptUserActions();
			userAction	= TransferredData.USER_INPUT.nextInt();
			if (isInputValid(userAction)) {
				sortUserAction(USER_ACTIONS[userAction-1], mainPlayer);
			}
		}
	}
	
	//This prints the user's possible actions
	private static void promptUserActions() {
		Utilities.skipLines(1);
		Utilities.print("Actions: ", true);
		for (int x = 0; x < USER_ACTIONS.length; x++) {
			Utilities.print(String.format("\t%d) %s", x + 1, USER_ACTIONS[x]), true);
		}
		Utilities.skipLines(1);
		Utilities.print("Enter in the index of the action you wish to do: ", true);
	}
	
	//This checks if the user's answer is within bounds
	private static boolean isInputValid(int input) {
		if (input > 0 && input <= USER_ACTIONS.length) {
			return true;
		}
		return false;
	}
	
	//This sorts the user's answer and does whatever the user wishes to do
	private static void sortUserAction(String userAction, Player mainPlayer) {
		switch(userAction) {
			case "Shop":	{
				ShopSystem.main(mainPlayer);
				break;
			}
			case "Inventory":{
				PlayerInventory.main(mainPlayer);
				break;
			}
			case "Quest":{
				TransferredData.USER_INPUT.nextLine();
				Utilities.skipLines(1);
				QuestLine.sortPlayerQuest(mainPlayer);
				break;
			}
			case "Save":{
				DataStore.main(mainPlayer);
				break;
			}
		}
	}
	
	//This is called whenever user loses in battle
	public static void onPlayerDeath() {
		GAME_IN_PROGRESS	= false;
		DataStore.erasePlayerData();
		DialogueSystem.main(DialogueSystem.GAME_OVER_TEXT, "Game");
	}
	
	//This is called whenever the user finishes the game, handles afterstory etc..
	public static void onGameComplete() {
		Main.GAME_IN_PROGRESS	= false;
		DialogueSystem.main(DialogueSystem.GAME_COMPLETE, "Game");
	}
	
}
