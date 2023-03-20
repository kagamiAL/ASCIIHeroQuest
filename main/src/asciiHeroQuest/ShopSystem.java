package asciiHeroQuest;
import asciiHeroQuest.Items.*;
import asciiHeroQuest.Items.Items.*;
import java.util.Scanner;

public class ShopSystem {
	
	private final static String	SUPPORT_ITEM	= "SupportItem";
	private final static String WEAPON			= "Weapon";
	private final static String ARMOUR			= "Armour";
	
	private final static String ASCII_VISUAL	= "                                   .-.,-.\r\n"
			+ "                                  _|_||_|_\r\n"
			+ "                                ,'|--'  __|\r\n"
			+ "                                |,'.---'-.'\r\n"
			+ "  ___                            |:|] .--|\r\n"
			+ " (__ ```----........_________...-|-|__'--|-........_________.....\r\n"
			+ "  \\._,```----........__________..::|--' _|--........_________....\r\n"
			+ "  :._,._,._,._,._,._,._,._,._,._,\\\\|___'-|._,._,._,._,._,._,._,._\r\n"
			+ "  |._,._,._,._,._,._,._,._,._,._,.`'-----'._,._,._,._,._,._,._,._\r\n"
			+ "  |._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._\r\n"
			+ "  |._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._\r\n"
			+ "  ;._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._\r\n"
			+ " /,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._,._\r\n"
			+ " )________)________)________)________)________)________)_______)_\r\n"
			+ "  |::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::.-\r\n"
			+ "  |_|_  .----._     ___.-_-__-_-.--.-'          __.-.-_-__-_-. '-\r\n"
			+ "  |---' '--.-'-'.  '-.-||_|__|_||--'           '--'-||_|__|_||_\r\n"
			+ "  |        '----'    '-|| |  | ||             ____.-|| |  | ||-'\r\n"
			+ "  |                   [||-|--|-||_           '--.-'-||-|--|-||\r\n"
			+ "  |-.____              ||_|__|_||-'-.           '---||_|__|_||_ _\r\n"
			+ "  |-'-.--'       ___.--||_|__|_||---' ___.--.       ||_|__|_||_'-\r\n"
			+ "  |---'         '---'--'--------'    '---'--'       '--------'\r\n"
			+ "  ;--.______________________________________________,--._________\r\n"
			+ "  :(o))---------------------------------------------:(o))--------\r\n"
			+ "  |`-: \\//|[_  /,.\\|| |,\\|[_  ((_ |[]|/,.\\|o)|o)|[_ |`-: ((_ |||\\\r\n"
			+ "  |__| // |[_  \\`'/|'-|`/|[_   _))|[]|\\`'/|| || |[_ |__|  _))|||`\r\n"
			+ "  |--|____________________________________________  |--|  _______\r\n"
			+ "  |  |       |`-||__|--|__|--|__|--|__|--|__|--|__| |__| ||__|--|\r\n"
			+ "  |  |_____  |._|.-------..-------..-------..----.| |  | |.------\r\n"
			+ "  |  |.-.-.| |  ||::'    ||::'    ||::'    ||:'  || |  | ||:'\r\n"
			+ "  |  || | || |  ||'      ||'      ||'      ||    || |  | ||____.:\r\n"
			+ "  |  ||-|-|| |  ||       ||       ||       ||    || |  | |.------\r\n"
			+ "  |  || | || |  ||      .||      .||      .||    || |  | ||:'\r\n"
			+ "  |__|'-'-'|/:._||___..::||___..::||___..::||__.:|| |__| ||____.:\r\n"
			+ "  |  |     || `-|---------------------------------.'|  |'.-------\r\n"
			+ " _|__|-----''-..| ________________________________|_|__|_|_______\r\n"
			+ "\r\n"
			+ " ________________________________________________________________\r\n"
			+ " -------------------------------------------------------------SSt";
	
	private static SupportItem[] supportItems;
	private static Weapon[] weapons;
	private static Armour[] armours;
	
	static {
		setUpSupportItems();
		setUpWeapons();
		setUpArmours();
	}
	
	public static void main(Player mainPlayer) {
		//Variables
		Scanner userInput		= TransferredData.USER_INPUT;
		
		//Workspace
		openingMessage();
		
		while (true) {
			int userAnswer;
			displayItemCategories();
			userAnswer	= userInput.nextInt();
			if (doesUserWantToExit(userAnswer)) {
				break;
			}
			String itemCategory	= getCategoryName(userAnswer);
			Item[] itemsArray	= getChosenItemArray(itemCategory);
			while (itemsArray != null && itemsArray.length > 0) {
				displayPlayerGold(mainPlayer);
				displayItemArray(itemsArray);
				userAnswer	= userInput.nextInt();
				if (doesUserWantToExit(userAnswer) || userAnswer > itemsArray.length) {
					break;
				}
				int selectedItemIndex	= (userAnswer - 1);
				if (isUserInputInBounds(selectedItemIndex, itemsArray.length)) {
					if (itemCategory.equals(SUPPORT_ITEM)) {
						Utilities.print("How much do you want to purchase?: ", true);
						purchaseSupportItem(mainPlayer, supportItems[selectedItemIndex], userInput.nextInt());
					} else {
						if (appendCombatItemIntoUserInv(mainPlayer, itemCategory, selectedItemIndex)) {
							itemsArray = removeValueFromArray(itemCategory, selectedItemIndex);
						}
					}
					Utilities.skipLines(1);
				}
			}
		}
		TransferredData.promptUserToContinue();
	}
	
	//Searches for weapon object from its name
	public static Weapon getWeaponFromName(String weaponName) {
		Weapon weaponReturn	= null;
		
		for (int x = 0; x < weapons.length; x++) {
			if (weapons[x].getName().equals(weaponName)) {
				return weapons[x];
			}
		}
		
		return weaponReturn;
	}
	
	//Searches for armour object from its name
	public static Armour getArmourFromName(String armourName) {
		Armour armourReturn	= null;
		
		for (int x = 0; x < armours.length; x++) {
			if (armours[x].getName().equals(armourName)) {
				return armours[x];
			}
		}
		
		return armourReturn;
	}
	
	//Searches for support item from its name
	public static SupportItem getSupportItemFromName(String itemName) {
		SupportItem itemReturn	= null;
		
		for (int x = 0; x < supportItems.length; x++) {
			if (supportItems[x].getName().equals(itemName)) {
				return supportItems[x];
			}
		}
		
		return itemReturn;
	}
	
	//Create supportItems array
	private static void setUpSupportItems() {
		final int SMALL_POTION_PRICE	= 50;
		final int LARGE_POTION_PRICE	= 100;
		final int GIGANTIC_POTION_PRICE	= 150;
		
		Potion smallHealthPotion	= new Potion();
		smallHealthPotion.setName("Small health potion");
		smallHealthPotion.setPrice(SMALL_POTION_PRICE);
		
		Potion largeHealthPotion	= new Potion();
		largeHealthPotion.setHealAmount(50);
		largeHealthPotion.setName("Large health potion");
		largeHealthPotion.setPrice(LARGE_POTION_PRICE);
		
		Potion giganticHealthPotion	= new Potion();
		giganticHealthPotion.setHealAmount(100);
		giganticHealthPotion.setName("Gigantic health potion");
		giganticHealthPotion.setPrice(GIGANTIC_POTION_PRICE);
		
		SupportItem[] itemArray			= {smallHealthPotion, largeHealthPotion, giganticHealthPotion};
		supportItems	= itemArray;
	};

	//Sets up weapons array
	private static void setUpWeapons() {
		Weapon steelMaul	= new Weapon();
		steelMaul.setName("Steel Maul");
		steelMaul.setDamage(10);
		steelMaul.setPrice(500);
		
		Weapon berserkSword	= new Weapon();
		berserkSword.setName("Berserker's Greatsword");
		berserkSword.setDamage(25);
		berserkSword.setPrice(1000);
		
		Weapon dragonSlayer	= new Weapon();
		dragonSlayer.setName("Dragon Slayer");
		dragonSlayer.setDamage(100);
		dragonSlayer.setPrice(2000);
		
		Weapon heavensWrath	= new Weapon();
		heavensWrath.setName("Heaven's Wrath");
		heavensWrath.setDamage(250);
		heavensWrath.setPrice(10000);
		
		Weapon[] weaponsArray	= {steelMaul, berserkSword, dragonSlayer, heavensWrath};
		weapons = weaponsArray;
	}
	
	//Sets up armors array
	private static void setUpArmours() {
		Armour chainMail	= new Armour();
		chainMail.setName("Chainmail Set");
		chainMail.setDurability(50);
		chainMail.setPercentReduction(10);
		chainMail.setPrice(500);
		
		Armour reptilianScales	= new Armour();
		reptilianScales.setName("Reptilian Scales");
		reptilianScales.setDurability(100);
		reptilianScales.setPercentReduction(25);
		reptilianScales.setPrice(1000);
		
		Armour ninthLegionArmour = new Armour();
		ninthLegionArmour.setName("9th Legion Armour");
		ninthLegionArmour.setDurability(250);
		ninthLegionArmour.setPercentReduction(50);
		ninthLegionArmour.setPrice(2000);
		
		Armour sanctumKnightsChestplate	= new Armour();
		sanctumKnightsChestplate.setName("Sanctum Knight's Chestplate");
		sanctumKnightsChestplate.setDurability(10000);
		sanctumKnightsChestplate.setPercentReduction(90);
		sanctumKnightsChestplate.setPrice(10000);
		
		Armour[] armourArray	= {chainMail, reptilianScales, ninthLegionArmour, sanctumKnightsChestplate};
		armours	= armourArray;
	}
	
	//Check if user wants to exit
	private static boolean doesUserWantToExit(int userInput) {
		return (userInput == 0);
	}
	
	//Displays this whenever user enters shop
	private static void openingMessage() {
		Utilities.print(ASCII_VISUAL, true);
		Utilities.skipLines(1);
		Utilities.print("Welcome to my shop, please have a look at my wares!", true);
	}
	
	//Displays item categories
	private static void displayItemCategories() {
		Utilities.skipLines(1);
		Utilities.print("\t1) Weapons\n\t2) Armours\n\t3) Support Items\n\nEnter the index of the item category you wish to look at or enter '0' to exit", true);
	}
	
	//Displays player gold
	private static void displayPlayerGold(Player mainPlayer) {
		Utilities.print("Player Gold: " + mainPlayer.getAmountGold(), true);
		Utilities.skipLines(1);
	}
	
	//Displays the items in the item category the user chose
	private static void displayItemArray(Item[] itemArray) {
		for (int x = 0; x < itemArray.length; x++) {
			Utilities.print(String.format("\t%d) %s", x + 1, itemArray[x].getItemInfo()), true);
		}
		Utilities.skipLines(1);
		Utilities.print("Select the item you wish to purchase or enter '0' to return", true);
	}
	
	//Gets the name of the category the user desires
	private static String getCategoryName(int userInput) {
		String stringReturn	= null;
		
		if (userInput <= 3 && userInput > 0) {
			if (userInput == 1) {
				stringReturn	= WEAPON;
			}else if (userInput == 2) {
				stringReturn	= ARMOUR;
			}else if (userInput == 3) {
				stringReturn	= SUPPORT_ITEM;
			}
		}
		
		return stringReturn;
	};
	
	//Gets the item array the user wants
	private static Item[] getChosenItemArray(String categoryName) {
		Item[] itemArray	= null;
		
		if (categoryName.equals(WEAPON)) {
			itemArray	= weapons;
		}else if (categoryName.equals(ARMOUR)) {
			itemArray	= armours;
		}else if (categoryName.equals(SUPPORT_ITEM)) {
			itemArray	= supportItems;
		}
		
		return itemArray;
	}
	
	//Checks if user has enough money to purchase support item and appends item into inventory
	private static void purchaseSupportItem(Player mainPlayer, SupportItem selectedItem, int amountOfItem) {
		if (amountOfItem > 0) {
			int totalPrice	= (selectedItem.getPrice()*amountOfItem);
			if (doesUserHaveEnoughGold(mainPlayer, totalPrice)){
				mainPlayer.modifyAmountGold(-totalPrice);
				mainPlayer.appendItem(selectedItem, amountOfItem);
			}	
		}
	}
	
	//Checks if user has enough gold to purchase item
	private static boolean doesUserHaveEnoughGold(Player mainPlayer, int goldPrice) {
		if (mainPlayer.getAmountGold() - goldPrice >= 0) {
			Utilities.print("Sucessfully purchased!", true);
			return true;
		}
		Utilities.print("You do not have enough gold to complete this purchase!", true);
		return false;
	}
	
	//Checks if user choice is valid
	private static boolean isUserInputInBounds(int userInput, int arrayLength) {
		if (userInput >= 0 && userInput < arrayLength) {
			return true;
		}
		return false;
	}
	
	//Adds combat item into its respective Player HashMap returns true if transaction was completed
	private static boolean appendCombatItemIntoUserInv(Player mainPlayer, String itemCategory, int selectedItemIndex) {
		int itemPrice	= 0;
		if (itemCategory.equals(ARMOUR)) {
			Armour chosenArmour	= armours[selectedItemIndex];
			itemPrice	= chosenArmour.getPrice();
			if (doesUserHaveEnoughGold(mainPlayer, itemPrice)) {
				mainPlayer.appendArmour(chosenArmour);
				mainPlayer.modifyAmountGold(-itemPrice);
				return true;
			}
		} else {
			Weapon chosenWeapon	= weapons[selectedItemIndex];
			itemPrice	= chosenWeapon.getPrice();
			if (doesUserHaveEnoughGold(mainPlayer, itemPrice)) {
				mainPlayer.appendWeapon(chosenWeapon);
				mainPlayer.modifyAmountGold(-itemPrice);
				return true;
			}
		}
		return false;
	}
	
	//Removes value from weapon or armour array and then sets new values
	private static Item[] removeValueFromArray(String itemCategory, int indexRemove) {
		Item[] arrayReturn	= null;
		int indexPlacement	= 0;
		
		if (itemCategory.equals(ARMOUR)) {
			Armour[] newArmourArray	= new Armour[armours.length - 1];
			
			for (int x = 0; x < armours.length; x++) {
				if (x == indexRemove) {
					continue;
				}
				newArmourArray[indexPlacement++] = armours[x];
			}
			
			armours	= newArmourArray;
			arrayReturn	= newArmourArray;
			
		} else {
			Weapon[] newWeaponArray	= new Weapon[weapons.length - 1];
			
			for (int x = 0; x < weapons.length; x++) {
				if (x == indexRemove) {
					continue;
				}
				newWeaponArray[indexPlacement++] = weapons[x];
			}
			
			weapons	= newWeaponArray;
			arrayReturn	= newWeaponArray;
			
		}
		
		return arrayReturn;
	}
	
}
