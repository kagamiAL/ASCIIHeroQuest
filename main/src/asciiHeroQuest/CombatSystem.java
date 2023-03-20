package asciiHeroQuest;

import asciiHeroQuest.SkillClass.*;
import asciiHeroQuest.Entities.Entity;
import asciiHeroQuest.Entities.Mobs;
import asciiHeroQuest.Items.Items;

import java.util.Scanner;

public class CombatSystem {
	
	static boolean	USER_BLOCKING		= false;
		
	static final int CRITICAL_SCALING	= 2;
	
	static final int CRIT_CHANCE		= 25;

	static final String[] USER_OPTIONS	= {"Attack", "Defend", "Item", "Skill"};
	
	static String USER_PROMPT;
	
	//Returns true if player wins against enemy, false if player loses
	public static boolean main(Player mainPlayer, Mobs.Mob enemyEntity) {
		//Variables
		Scanner userInput	= TransferredData.USER_INPUT;
		
		//Workspace
		
		Utilities.print(String.format("You have encountered a %s!", enemyEntity.getName()), true);
		enemyEntity.printAsciiLogo();
		
		while (mainPlayer.isAlive() && enemyEntity.isAlive()) {
			checkBleeding(mainPlayer);
			mainPlayer.displayHealth();
			if (!isEntityShocked(mainPlayer)) {
				printOptions();
				while ((!sortUserInput(userInput.nextLine(), mainPlayer, enemyEntity))){ //If user inputs something that doesn't belong in options
					Utilities.print("ENTER A VALID ACTION", true);
					printOptions();
				};
				decrementPoisonedStatus(mainPlayer);
			}
			promptUserToContinue(userInput);
			if (!enemyEntity.isAlive()) {break;};
			handleEnemyTurn(mainPlayer, enemyEntity);
			promptUserToContinue(userInput);
		}
		if (mainPlayer.isAlive()) {
			return true;
		}
		Main.onPlayerDeath();
		return false;
	}
	
	//Prints user combat options
	static void printOptions() {
		Utilities.skipLines(1);
		Utilities.print(USER_PROMPT, true);
	}
	
	//Press enter to continue
	static void promptUserToContinue(Scanner inputObject) {
		Utilities.skipLines(1);
		Utilities.print("Press [ENTER] to continue", true);
		inputObject.nextLine();
	}
	
	//Basic enemy AI
	static void handleEnemyTurn(Player mainPlayer, Mobs.Mob enemyEntity) {
		checkBleeding(enemyEntity);
		Utilities.print(enemyEntity.getName() + "'s turn", true);
		if (!isEntityShocked(enemyEntity)) {
			Utilities.print(enemyEntity.getName() + " attacks...", true);
			if (USER_BLOCKING) {
				USER_BLOCKING = false;
				Utilities.print("YOU BLOCKED THE ENEMY'S ATTACK! NO DAMAGE TAKEN", true);
				return;
			}
			Utilities.skipLines(1);
			mainPlayer.displayAsciiVisual();
			Utilities.print(String.format("%s DEALT %.1f DAMAGE!", enemyEntity.getName(), enemyEntity.getBaseAttack()), true);
			enemyEntity.attackEntity(mainPlayer, (isEntityPoisoned(enemyEntity) ? 0.5 : 1));
			decrementPoisonedStatus(enemyEntity);
		}
	}
	
	//Sorts the user's option and calls functions based on it
	static boolean sortUserInput(String userInput, Player mainPlayer, Mobs.Mob enemyEntity) {
		switch(userInput) {
			case "Attack": {
				handleAttacking(mainPlayer, enemyEntity);
				return true;
			}
			case "Defend": {
				Utilities.print("You decide to block the enemy's next attack", true);
				USER_BLOCKING = true;
				return true;
			}
			case "Item": {
				handleItems(mainPlayer);
				return true;
			}
			case "Skill":{
				
				return true;
			}
			default: {
				return false;
			}
		}
	}
	
	//Handles player attack
	static void handleAttacking(Player mainPlayer, Mobs.Mob enemyEntity) {
		double damageScaling	= 1;
		Utilities.print("You attack...", true);
		if ((int)(Math.random()*100) <= CRIT_CHANCE) { //See if the player hits a critical
			damageScaling	= 2;
			Utilities.print("CRITICAL!!!", true); 
		}
		if (isEntityPoisoned(mainPlayer)) {
			damageScaling	/= 2;
		}
		mainPlayer.attackEntity(enemyEntity, damageScaling);
		enemyEntity.printAsciiDesign();
		enemyEntity.displayHealth();
		Utilities.skipLines(1);
		Utilities.print(String.format("YOU DEALT %.1f DAMAGE TO %s!", mainPlayer.getTotalDamage()*damageScaling, enemyEntity.getName()), true);
	}
	
	//Handles player skill
	static void handleSkillUse(Player mainPlayer, Mobs.Mob enemyEntity) {
		displayPlayerSkills(mainPlayer);
	}
	
	//Prints player skills
	static void displayPlayerSkills(Player mainPlayer) {
		Skill[] skillArray	= mainPlayer.getSkillsArray();
		for (int x = 0; x < skillArray.length; x++) {
			Utilities.print(String.format("\t%d) %s", x + 1, skillArray[x].getSkillName()), true);
		}
	}
	
	//Check bleeding status effect, if bleeding then damage entity
	static void checkBleeding(Entity mainEntity) {
		String statusEffect	= mainEntity.getStatusEffect();
		if (!statusEffect.isEmpty() && statusEffect.equals("Bleeding")) {
			mainEntity.decrementStatusCounter();
			mainEntity.damageEntity(StatusEffects.BLEED_DAMAGE);
			Utilities.print(mainEntity.getName() + " took bleed damage!", true);
		}
	}
	
	//If enemy is shocked skip turn
	static boolean isEntityShocked(Entity mainEntity) {
		if (mainEntity.getStatusEffect().equals("Shocked")) {
			Utilities.print(mainEntity.getName() + " is shocked! Turn skipped!", true);
			mainEntity.decrementStatusCounter();
			return true;
		}
		return false;
	}
	
	//If poisoned deal reduced damage
	static boolean isEntityPoisoned(Entity mainEntity) {
		if (mainEntity.getStatusEffect().equals("Poisoned")) {
			Utilities.print(mainEntity.getName() + " is poisoned! Damage is now reduced!", true);
			return true;
		}
		return false;
	}
	
	//Decrement poison status if entity is poisoned
	static void decrementPoisonedStatus(Entity mainEntity) {
		if (mainEntity.getStatusEffect().equals("Poisoned")) {
			mainEntity.decrementStatusCounter();
		}
	}
	
	//Handles player items option
	static void handleItems(Player mainPlayer) {
		int userAnswer;
		mainPlayer.displayInventory();
		Utilities.skipLines(1);
		Utilities.print("Enter item index or enter 0 to continue", true);
		userAnswer 	= TransferredData.USER_INPUT.nextInt();
		Utilities.skipLines(1);
		
		if (userAnswer > 0) {
			Items.SupportItem supportItem	= mainPlayer.searchForItemWithIndex(userAnswer);
			if (supportItem != null) {
				supportItem.useItem();
			}
		}
		
		TransferredData.USER_INPUT.nextLine();
	}
	
	static {
		String baseString	= "What will you do?";
		
		for (int x = 0; x < USER_OPTIONS.length; x++) {
			baseString += "\n\t• " + USER_OPTIONS[x];
		}
		
		USER_PROMPT	= baseString;
	}
	
}
