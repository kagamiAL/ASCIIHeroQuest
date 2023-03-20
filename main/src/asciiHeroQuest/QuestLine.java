package asciiHeroQuest;
import asciiHeroQuest.Entities.Mobs.*;

public class QuestLine {
	
	//Prints opening dialogue and gets user name
	public static void gameOpening(Player mainPlayer) {
		String playerName;
		DialogueSystem.main(DialogueSystem.OPENING_DIALOGUE, "Game");
		DialogueSystem.main(DialogueSystem.ARYA_DIALOGUE, "Arya");
		Utilities.skipLines(1);
		Utilities.print("Enter your name: ", false);
		playerName	= Utilities.safeNextLine();
		mainPlayer.setName(playerName);
		Utilities.skipLines(1);
		DialogueSystem.setNameInDialogue(DialogueSystem.ARYA_DIALOGUE2, playerName);
		DialogueSystem.main(DialogueSystem.ARYA_DIALOGUE2, "Arya");
		Utilities.print("You have recieved 1100 Gold!", true);
		mainPlayer.modifyAmountGold(1100);
		mainPlayer.setPlayerLevel(1);
	}
	
	//Sorts the quest that the player can embark on
	public static void sortPlayerQuest(Player mainPlayer) {
		switch(mainPlayer.getPlayerLevel()) {
			case 1:	{
				questLine1(mainPlayer);
				break;
			}
			case 2: {
				questLine2(mainPlayer);
				break;
			}
			case 3: {
				questLine3(mainPlayer);
				break;
			}
			case 4: {
				questLine4(mainPlayer);
				break;
			}
		}
	}
	
	//Quest 1
	private static void questLine1(Player mainPlayer) {
		DialogueSystem.main(DialogueSystem.ARTEMIS_DIALOGUE, "Artemis, Demeter's disciple");
		if (CombatSystem.main(mainPlayer, new Skeleton())) {;
			questRewards(mainPlayer, 2000);
			mainPlayer.setPlayerLevel(2);
		}
	}
	
	//Quest 2
	private static void questLine2(Player mainPlayer) {
		DialogueSystem.main(DialogueSystem.BRUTUS_DIALOGUE, "Brutus, the lord's paladin");
		if (CombatSystem.main(mainPlayer, new Ghoul())) {;
			questRewards(mainPlayer, 5000);
			mainPlayer.setPlayerLevel(3);
		}
	}
	
	//Quest 3
	private static void questLine3(Player mainPlayer) {
		DialogueSystem.main(DialogueSystem.ASTORIA_DIALOGUE, "Astoria, the high priest");
		if (CombatSystem.main(mainPlayer, new Specter())) {;
			questRewards(mainPlayer, 20000);
			mainPlayer.setPlayerLevel(4);
		}
	}
	
	//Quest 4 final quest
	private static void questLine4(Player mainPlayer) {
		if (CombatSystem.main(mainPlayer, new Dragon())) {;
			questRewards(mainPlayer, 10000);
			mainPlayer.setPlayerLevel(5);
			Main.onGameComplete();
		}
	}
	
	//Gives player quest rewards
	private static void questRewards(Player mainPlayer, int amountOfGold) {
		Utilities.skipLines(1);
		Utilities.print(String.format("Quest completed! You recieved %d Gold!", amountOfGold), true);
		mainPlayer.modifyAmountGold(amountOfGold);
	}
	
}
