package asciiHeroQuest.Items;

import asciiHeroQuest.Utilities;

public class Potion extends Items.SupportItem {
	private int healAmount		= 25;
	
	private String asciiVisual	= " mmm\r\n"
			+ " )-(\r\n"
			+ "(   )\r\n"
			+ "|   |\r\n"
			+ "|   |\r\n"
			+ "|___|";
	
	
	{
		this.type	= "HealthPotion";
	}
	
	//Returns how much the Potion heals
	public int getHealAmount() {
		return healAmount;
	}

	//Sets how much the potion heals
	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	//Player uses potion
	public void useItem() {
		if(mainPlayer == null || !this.isItemUseable()){return;}
		Utilities.print(asciiVisual, true);
		Utilities.print(String.format("Used a %s, healed %d health!", this.getName(), this.getHealAmount()), true);
		double totalAddedHealth	= mainPlayer.getCurrentHealth() + healAmount;
		this.decrementAmountOfItem();
		if (totalAddedHealth > mainPlayer.getMaximumHealth()) { //If the healed health exceeds maximum health just heal player to maximum health
			mainPlayer.setCurrentHealth(mainPlayer.getMaximumHealth());
			return;
		}
		mainPlayer.setCurrentHealth(totalAddedHealth);
	}
	
	public String getItemInfo() {
		String stringReturn	= super.getItemInfo();
		return stringReturn + "\n\t\tHeal Amount: " + this.getHealAmount();
	}
	
}
