package asciiHeroQuest;
import java.util.HashMap;
import java.util.Scanner;
import asciiHeroQuest.Items.*;
import asciiHeroQuest.Items.Items.*;

public class PlayerInventory {
	
	private static final String STRING_PROMPT	= "User Inventory: \n\tEnter '0' to equip armour\n\tEnter '1' to equip weapon";

	private static final String STRING_VISUAL	= "   .eec.              .e$$$c                                \r\n"
			+ "  z$*\"*$$eec..       zP  .3$c                   \r\n"
			+ " .$\"  d$\"  \"\"\"****bee*=*\"\" *$                               \r\n"
			+ " $%  d$$                   ^$%                              \r\n"
			+ ".$  z$%$bc.                 $%                              \r\n"
			+ "4F 4$\" $\"^$*ec..  .ee.    ./\" b                             \r\n"
			+ "dF $P  P  F   \"\"\"\"\"3F\"\"\"\"\"\"   4                             \r\n"
			+ "3b4$   \"           $F         4                             \r\n"
			+ "4$$$  -            $F         4                             \r\n"
			+ " $$$               $F         4                             \r\n"
			+ " *$$               $F         @                             \r\n"
			+ " 4$$               $F         F                             \r\n"
			+ " ^$F   .......     $F        .F                             \r\n"
			+ "  $\"  z\"     ^\"\"\"\"\"$F\"\"\"\"\"\"\"\"\"%.                            \r\n"
			+ " 4$  4F     e      $L          \".                           \r\n"
			+ " 4F  ^L    4$     z%\"c    *.    b                           \r\n"
			+ " d    ^$*=e$$eer=$\"  \"be..JL..ee*                           \r\n"
			+ " $     $   $F    $   zP   4P   F                            \r\n"
			+ " F     F   $F    4. .P    d%  J                             \r\n"
			+ "4%     F   $F     F $\"   zP   P                             \r\n"
			+ "J      F   '%     Fd\"   4P   4\"                             \r\n"
			+ "$      F          $F         P                              \r\n"
			+ "$      L         .$         4%                              \r\n"
			+ "*      '.       .$$.       .$                               \r\n"
			+ "'       ^\"****\"\"\"  \"*******$\"                               \r\n"
			+ " %                        .P                                \r\n"
			+ "  *c                     .@                                 \r\n"
			+ "   ^\"%4c...        ...zed*  Gilo94'                         \r\n"
			+ "         ^\"\"\"\"\"\"\"\"";
	
	
	public static void main(Player mainPlayer) {
		//Variables
		Scanner userInput		= TransferredData.USER_INPUT;
		int userAnswer			= -1;
		
		//Workspace
		printOpeningPrompt();
		userAnswer 			= userInput.nextInt();
		while (userAnswer < 0 || userAnswer > 1) {
			Utilities.print("Not a valid input!", true);
			userAnswer		= userInput.nextInt();
		}
		
		if (userAnswer == 0) {
			handleArmourSelected(mainPlayer);
		}else {
			handleWeaponSelected(mainPlayer);
		}
		TransferredData.promptUserToContinue();
	}
	
	//Prints a visual whenever inventory is opened
	private static void printOpeningPrompt() {
		Utilities.print(STRING_VISUAL, true);
		Utilities.skipLines(1);
		Utilities.print(STRING_PROMPT, true);
	}
	
	//Handles all processes related to when user chooses weapon option
	private static void handleWeaponSelected(Player mainPlayer) {
		//Variables
		HashMap<String, Weapon> weaponHashMap	= mainPlayer.getWeapons();
		
		//Workspace
		if (!weaponHashMap.isEmpty()) {
			mainPlayer.setWeaponEquipped(weaponHashMap.get(retrieveSelectedCombatItem(printWeaponHashMap(weaponHashMap))));
			Utilities.print("Sucessfully Equipped!", true);
		}
	}
	
	//Prints weapon names and returns an array of their names
	private static String[] printWeaponHashMap(HashMap<String, Weapon> hashMap) {
		String[] weaponNames	= new String[hashMap.size()];
		Utilities.skipLines(1);
		Utilities.print("Weapons: ", true);
		int numericalIndex	= 0;
		for (String key: hashMap.keySet()) {
			weaponNames[numericalIndex] = key;
			Utilities.print(String.format("\t%d) %s", ++numericalIndex, key), true);
			
		}
		return weaponNames;
	}
	
	//Handles all processes related to when user chooses armour option
	private static void handleArmourSelected(Player mainPlayer) {
		//Variables
		HashMap<String, Armour> armourHashMap	= mainPlayer.getArmours();
		
		//Workspace
		if (!armourHashMap.isEmpty()) {
			mainPlayer.setArmourEquipped(armourHashMap.get(retrieveSelectedCombatItem(printArmourHashMap(armourHashMap))));
			Utilities.print("Sucessfully Equipped!", true);
		}
	}
	
	//Prints armour names and returns an array of their names
	private static String[] printArmourHashMap(HashMap<String, Armour> hashMap) {
		String[] weaponNames	= new String[hashMap.size()];
		Utilities.skipLines(1);
		Utilities.print("Armours: ", true);
		int numericalIndex	= 0;
		for (String key: hashMap.keySet()) {
			weaponNames[numericalIndex] = key;
			Utilities.print(String.format("\t%d) %s", ++numericalIndex, key), true);
			
		}
		return weaponNames;
	}

	
	//Prompts user to select the combat item they want to equip and returns their selection
	private static String retrieveSelectedCombatItem(String[] combatItemNames) {
		String combatItemName	= "";
		int userAnswer			= -1;
		
		do {
			Utilities.print("Enter in the index of the combat item you want to equip", true);
			userAnswer			= TransferredData.USER_INPUT.nextInt();
		} while(userAnswer < 1 || userAnswer > combatItemNames.length);
		
		combatItemName	= combatItemNames[userAnswer - 1];
		return combatItemName;
	}
	
}
