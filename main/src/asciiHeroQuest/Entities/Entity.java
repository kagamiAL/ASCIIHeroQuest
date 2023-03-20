package asciiHeroQuest.Entities;

import asciiHeroQuest.StatusEffects;
import asciiHeroQuest.Utilities;

public class Entity {
	protected String name;
	protected double currentHealth	= 100;
	protected double maximumHealth	= 100;
	protected double baseAttack			= 10;
	protected int statusCounter		= 0;
	protected String statusEffect	= "None";
	
	public String getName() {
		return name;
	}
	
	public void setName(String nameSet) {
		this.name = nameSet;
	}
	
	public double getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(double baseAttack) {
		this.baseAttack = baseAttack;
	}

	public String getStatusEffect() {
		return statusEffect;
	}
	
	public void decrementStatusCounter() { //Statuses only last 3 turns, so whenever it's the player turn the statusCounter is decremented
		if (this.statusCounter - 1 < 0) {
			this.statusCounter	= 0;
			this.setStatusEffect("None");
			return;
		}
		this.statusCounter	-= 1;
	}
	
	public void setStatusEffect(String newStatus) {
		if (newStatus != statusEffect && StatusEffects.STATUSES.contains(newStatus)) {
			this.statusCounter	= StatusEffects.STATUS_LENGTH;
			this.statusEffect 	= newStatus;
		}
	}

	public double getMaximumHealth() {
		return maximumHealth;
	}

	public void setMaximumHealth(double maximumHealth) {
		this.maximumHealth 	= maximumHealth;
		this.currentHealth	= maximumHealth;
	}
	
	public double getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = currentHealth;
	}

	public boolean isAlive() {
		if (currentHealth > 0) {
			return true;
		}
		return false;
	}
	
	public void damageEntity(double inflictedDamage) {
		double newHealth	= Math.max(currentHealth - inflictedDamage, 0);
		this.currentHealth		= newHealth;
	}
	
	public void displayHealth() {
		final int BARS_USED	= 25;
		String stringUse	= this.getName() + "'s Health: |";
		String stringUse2	= "| %.1f/%.1f";
		int barRepeatAmount	= (int)Math.ceil((currentHealth/maximumHealth)*BARS_USED);
		Utilities.print(stringUse + "=".repeat(barRepeatAmount) + " ".repeat(BARS_USED - barRepeatAmount) + String.format(stringUse2, currentHealth, maximumHealth), true);
	}
	
}
