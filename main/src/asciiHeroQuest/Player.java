package asciiHeroQuest;
import java.util.HashMap;
import java.util.Set;

import asciiHeroQuest.Entities.Entity;
import asciiHeroQuest.Items.Armour;
import asciiHeroQuest.Items.Items;
import asciiHeroQuest.Items.Weapon;
import asciiHeroQuest.SkillClass.*;

public class Player extends Entity {

	private final String ASCII_VISUAL	= "      _,.\r\n"
			+ "    ,` -.)\r\n"
			+ "   ( _/-\\\\-._\r\n"
			+ "  /,|`--._,-^|            ,\r\n"
			+ "  \\_| |`-._/||          ,'|\r\n"
			+ "    |  `-, / |         /  /\r\n"
			+ "    |     || |        /  /\r\n"
			+ "     `r-._||/   __   /  /\r\n"
			+ " __,-<_     )`-/  `./  /\r\n"
			+ "'  \\   `---'   \\   /  /\r\n"
			+ "    |           |./  /\r\n"
			+ "    /           //  /\r\n"
			+ "\\_/' \\         |/  /\r\n"
			+ " |    |   _,^-'/  /\r\n"
			+ " |    , ``  (\\/  /_\r\n"
			+ "  \\,.->._    \\X-=/^\r\n"
			+ "  (  /   `-._//^`\r\n"
			+ "   `Y-.____(__}\r\n"
			+ "    |     {__)\r\n"
			+ "          ()";
	
	private Weapon weaponEquipped;
	private Armour armourEquipped;
	private HashMap<String, Weapon> weapons = new HashMap<String, Weapon>();;
	private HashMap<String, Armour> armours = new HashMap<String, Armour>();;
	private Items.SupportItem[] items	= {};
	private int amountGold 	= 0;
	private int playerLevel	= 0;
	private Skill[] playerSkills 		= {};

	//Adds Item to the items array in Player object
	private void addNewItemToArray(Items.SupportItem itemAdd) {
		Items.SupportItem[] oldItemArray	= items;
		items	= new Items.SupportItem[oldItemArray.length + 1]; //Create new item array with oldItemArray.length + 1
				
		for (int x = 0; x < oldItemArray.length; x++) {
			items[x]	= oldItemArray[x]; //Fill new array with old items
		}
		
		items[oldItemArray.length] = itemAdd; //Add new item to new array
	}
	
	//Finds cached item in items array
	private Items.SupportItem searchForItem(Items.SupportItem itemAppend) {
		Items.SupportItem itemReturn	= null;
		
		for (int x = 0; x < items.length; x++) {
			if (items[x].getName().equals(itemAppend.getName())) { //Search through item array, if a cached item already exists return it
				return items[x];
			}
		}

		return itemReturn;
	}
	
	//Gets equipped combatItemName if it exists
	private String getCombatItemName(Items.CombatItem combatItem) {
		String stringReturn	= "null";
		
		if (combatItem != null) {
			return combatItem.getName();
		}
		
		return stringReturn;
	}
	
	//Converts combat item HashMap into a string that can be saved
	private String combatItemMapToString(Set<String> keySet) {
		String stringReturn	= "";
		
		for (String itemName: keySet) {
			if (stringReturn.isBlank()) {
				stringReturn	= itemName;
				continue;
			}
			stringReturn += String.format(",%s", itemName);
		}
		
		return stringReturn;
	}
	
	//Converts all currently owned items into a string
	private String supportItemArrayToString() {
		String stringReturn	= "";
		
		for (int x = 0; x < items.length; x++) {
			String itemName	= items[x].getName();
			if (stringReturn.isBlank()) {
				stringReturn	= itemName;
				continue;
			}
			stringReturn += String.format(",%s", itemName);
		}
		
		return stringReturn;
	}
	
	//Converts item amounts into a string
	private String supportItemAmountsToString() {
		String stringReturn	= "";
		
		for (int x = 0; x < items.length; x++) {
			String itemName	= Integer.toString(items[x].getAmountOfItem());
			if (stringReturn.isBlank()) {
				stringReturn	= itemName;
				continue;
			}
			stringReturn += String.format(",%s", itemName);
		}
		
		return stringReturn;
	}
		
	//Scales maximum health to player level
	private void scalePlayerHealthWithLevel() {
		final int HEALTH_SCALING 	= 75;
		final int BASE_HEALTH		= 100;
		this.setMaximumHealth(BASE_HEALTH + (Math.max(this.playerLevel - 1, 0)*HEALTH_SCALING));
	}
	
	//Updates player skills with their current level
	private void updatePlayerSkills() {
		int skillIndex	= this.playerLevel;
		if (skillIndex > SkillClass.skillArray.length) {
			skillIndex	= SkillClass.skillArray.length;
		}
		Skill[] newSkillArray	= new Skill[skillIndex];
		
		for (int x = 0; x < skillIndex; x++) {
			newSkillArray[x]	= SkillClass.skillArray[x];
		}
		
		playerSkills	= newSkillArray;
	}
	
	//Initializes everything after all values have been set
	public void __init__() {
		this.scalePlayerHealthWithLevel();
		this.updatePlayerSkills();
	}
	
	//Returns armor HashMap
	public HashMap<String, Armour> getArmours() {
		return armours;
	}

	//Returns weapon HashMap
	public HashMap<String, Weapon> getWeapons() {
		return weapons;
	}
	
	//Returns total player damage base + weapon damage
	public double getTotalDamage() {
		double damageReturn	= baseAttack;
		
		if (weaponEquipped != null) {
			damageReturn	+= weaponEquipped.getDamage();
		}
		
		return damageReturn;
	}
	
	//Attacks another entity object
	public void attackEntity(Entity enemyEntity, double damageScaling) {
		double damageInflicted	= getBaseAttack();
		
		if (weaponEquipped != null) {
			damageInflicted += weaponEquipped.getDamage(); //If player has weapon do additional damage
		}
		
		enemyEntity.damageEntity(damageInflicted*damageScaling);
	}
	
	//Damages player object
	public void damageEntity(double inflictedDamage) {
		if (armourEquipped != null) {
			inflictedDamage	= armourEquipped.getReducedDamage(inflictedDamage); //Armour reduces any incoming damage but takes the full damage
		}
		super.damageEntity(inflictedDamage);
	}
	
	//If cached item object already exists increment "amount" attribute if not add the item to items array and 
	public void appendItem(Items.SupportItem itemAppend, int amountAdd) {
		Items.SupportItem cachedItem	= searchForItem(itemAppend); //If there is an existing item representation in array
		if (cachedItem == null) {
			itemAppend.setPlayerOwner(this);
			cachedItem	= itemAppend;
			addNewItemToArray(itemAppend); //Actually add the new item to the array
		}
		cachedItem.appendItemAmount(amountAdd); //Append "amount" value in item
	}
	
	//Searches item through name
	public Items.SupportItem searchForItemWithIndex(int itemIndex) {
		Items.SupportItem itemReturn	= null;
		
		for (int x = itemIndex - 1; x < items.length; x++) {
			Items.SupportItem supportItem	= items[x];
			if (supportItem.isItemUseable()) {
				return supportItem;
			}
		}

		return itemReturn;
	}
	
	//Prints user inventory
	public void displayInventory() {
		int displayIndex	= 0;
		Utilities.print("INVENTORY:", true);
		for (int x = 0; x < items.length; x++) {
			Items.SupportItem supportItem	= items[x];
			if (supportItem.isItemUseable()) {
				Utilities.print(String.format("\t%d. %s x%d", ++displayIndex, supportItem.getName(), supportItem.getAmountOfItem()), true);
			}
		}
	}

	//Returns the current weapon equipped
	public Weapon getWeaponEquipped() {
		return weaponEquipped;
	}

	//Sets the current weapon equipped
	public void setWeaponEquipped(Weapon weaponEquipped) {
		this.weaponEquipped = weaponEquipped;
	}

	//Returns the current armour equipped
	public Armour getArmourEquipped() {
		return armourEquipped;
	}

	//Sets the current armour equipped
	public void setArmourEquipped(Armour armourEquipped) {
		this.armourEquipped = armourEquipped;
	}

	//Displays player visual
	public void displayAsciiVisual() {
		Utilities.print(ASCII_VISUAL, true);
	}
	
	//Get amount of player gold
	public int getAmountGold() {
		return amountGold;
	}

	//Modify amount of gold
	public void modifyAmountGold(int numberAdd) {
		if (this.amountGold + numberAdd < 0) {return;}
		this.amountGold += numberAdd;
	}
	
	//Returns items array
	public Items.SupportItem[] getItems() {
		return items;
	}

	//Returns Skills array
	public Skill[] getSkillsArray() {
		return this.playerSkills;
	}
	
	//Adds new skill to skill array
	public void appendSkillArray(Skill newSkill) {
		Skill[] oldSkillArray	= this.playerSkills;
		this.playerSkills		= new Skill[oldSkillArray.length + 1]; //Create new item array with oldItemArray.length + 1
				
		for (int x = 0; x < oldSkillArray.length; x++) {
			this.playerSkills[x]	= oldSkillArray[x]; //Fill new array with old items
		}
		
		this.playerSkills[oldSkillArray.length] = newSkill; //Add new item to new array
	}
	
	//Append armor to armor HashMap
	public void appendArmour(Armour newArmour) {
		if (armours.get(newArmour.getName()) != null) {return;}
		newArmour.setPlayerOwner(this);
		armours.put(newArmour.getName(), newArmour);
	}
	
	//Append weapon to weapon HashMao
	public void appendWeapon(Weapon newWeapon) {
		if (weapons.get(newWeapon.getName()) != null) {return;}
		newWeapon.setPlayerOwner(this);
		weapons.put(newWeapon.getName(), newWeapon);
	}
	
	//Returns player's current level
	public int getPlayerLevel() {
		return playerLevel;
	}

	//Sets player's current level
	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
		this.scalePlayerHealthWithLevel();
		this.updatePlayerSkills();
	}
	
	//Makes it so that player object can be saved
	public String convertToSaveableString() {
		return String.format(
				DataStore.SAVE_FORMAT, 
				getCombatItemName(weaponEquipped), 
				getCombatItemName(armourEquipped),
				combatItemMapToString(weapons.keySet()),
				combatItemMapToString(armours.keySet()),
				supportItemArrayToString(),
				supportItemAmountsToString(),
				this.amountGold,
				this.playerLevel				
			);
	}
	
}
