package asciiHeroQuest;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import asciiHeroQuest.Items.*;
import asciiHeroQuest.Items.Items.*;

//This class handles file writing and data saving of this game

public class DataStore {
	
	public static final String SAVE_FORMAT	= "weaponEquipped,%s\n"
			+ "armourEquipped,%s\n"
			+ "weapons,%s\n"
			+ "armours,%s\n"
			+ "items,%s\n"
			+ "itemAmounts,%s\n"
			+ "amountGold,%d\n"
			+ "playerLevel,%d\n";
	
	private static final String FILE_PATH		= "Save/SaveFile.txt";
	
	private static final java.net.URL FILE_URL	= DataStore.class.getResource(FILE_PATH);
	
	//Sets weaponEquipped value of saved data in player object
	private static void setWeaponEquipped(Player mainPlayer, String weaponName) {
		Weapon weaponObject	= ShopSystem.getWeaponFromName(weaponName);
		if (weaponObject != null) {
			mainPlayer.appendWeapon(weaponObject);
			mainPlayer.setWeaponEquipped(weaponObject);
		}
	}
	
	//Sets armourEquipped value in player object from saved data
	private static void setArmourEquipped(Player mainPlayer, String armourName) {
		Armour armourObject		= ShopSystem.getArmourFromName(armourName);
		if (armourObject != null) {
			mainPlayer.appendArmour(armourObject);
			mainPlayer.setArmourEquipped(armourObject);
		}
	}
	
	//Sets weapons into weapon Hash Map in player object
	private static void setWeaponsHashMap(Player mainPlayer, String[] weaponNames) {
		for (int x = 1; x < weaponNames.length; x++) {
			Weapon weaponObject	= ShopSystem.getWeaponFromName(weaponNames[x]);
			if (weaponObject != null) {
				mainPlayer.appendWeapon(weaponObject);
			}
		}
	}
	
	//Sets armours into armour Hash Map in player object
	private static void setArmoursHashMap(Player mainPlayer, String[] armourNames) {
		for (int x = 1; x < armourNames.length; x++) {
			Armour armourObject		= ShopSystem.getArmourFromName(armourNames[x]);
			if (armourObject != null) {
				mainPlayer.appendArmour(armourObject);
			}
		}
	}
	
	//Sets support items into support items array in player object
	private static void setSupportItemArray(Player mainPlayer, String[] itemNames) {
		for (int x = 1; x < itemNames.length; x++) {
			SupportItem	itemObject	= ShopSystem.getSupportItemFromName(itemNames[x]);
			if (itemObject != null) {
				mainPlayer.appendItem(itemObject, 0);
			}
		}
	}
	
	//Sets support item amounts
	private static void setSupportItemAmounts(Player mainPlayer, String[] itemAmounts) {
		SupportItem[] itemArray	= mainPlayer.getItems();
		for (int x = 1; x < itemAmounts.length; x++) {
			if (x <= itemArray.length) {
				itemArray[x - 1].appendItemAmount(Integer.parseInt(itemAmounts[x]));
			}
		}
	}
	
	//Sorts the data saved and sets values accordingly
	private static void sortSavedData(Player mainPlayer, String savedData) {
		String[] stringArray	= savedData.split(",");
		if (stringArray.length > 1) {
			switch(stringArray[0]) {
				case "weaponEquipped": {
					setWeaponEquipped(mainPlayer, stringArray[1]);
					break;
				}
				case "armourEquipped": {
					DataStore.setArmourEquipped(mainPlayer, stringArray[1]);
					break;
				}
				case "weapons": {
					DataStore.setWeaponsHashMap(mainPlayer, stringArray);
					break;
				}
				case "armours": {
					DataStore.setArmoursHashMap(mainPlayer, stringArray);
					break;
				}
				case "items": {
					DataStore.setSupportItemArray(mainPlayer, stringArray);
					break;
				}
				case "itemAmounts": {
					DataStore.setSupportItemAmounts(mainPlayer, stringArray);
					break;
				}
				case "amountGold": {
					mainPlayer.modifyAmountGold(Integer.parseInt(stringArray[1]));
					break;
				}
				case "playerLevel": {
					mainPlayer.setPlayerLevel(Integer.parseInt(stringArray[1]));
					break;
				}
			}
		}
	}
	
	//Reads save file
	private static void readSaveFile(Player mainPlayer) {
		try {
			String filePath	= Paths.get(FILE_URL.toURI()).toString();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String lineRead = null;
			while((lineRead = bufferedReader.readLine()) != null) {
				sortSavedData(mainPlayer, lineRead);
			}
			bufferedReader.close();
			
		} catch(IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//Main method
	public static void main(Player mainPlayer) {
		try {
			String filePath	= Paths.get(FILE_URL.toURI()).toString();
			PrintWriter myPrintWriter	= new PrintWriter(new FileWriter(filePath));
			myPrintWriter.println(mainPlayer.convertToSaveableString());
			myPrintWriter.close();
		} catch(IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//Erases player data
	public static void erasePlayerData() {
		try {
			String filePath	= Paths.get(FILE_URL.toURI()).toString();
			PrintWriter myPrintWriter	= new PrintWriter(new FileWriter(filePath));
			myPrintWriter.println("");
			myPrintWriter.close();
		} catch(IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//Returns saved player data if it exists
	public static Player getPlayerData() {
		Player mainPlayer	= new Player();
		
		readSaveFile(mainPlayer);
		mainPlayer.__init__();
		
		return mainPlayer;
	}
	
}
