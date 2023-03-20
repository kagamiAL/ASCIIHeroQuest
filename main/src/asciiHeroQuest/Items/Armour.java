package asciiHeroQuest.Items;

import asciiHeroQuest.Utilities;

public class Armour extends Items.CombatItem {
	private double percentReduction = 0;
	
	{
		type = "Armour";
	}
	
	public void setPercentReduction(double newPercent) {
		percentReduction	= newPercent;
	}
	
	public void setDurability(double newDura) {
		this.durability = newDura;
	}
	
	public String getItemInfo() {
		return super.getItemInfo() + String.format("\n\t\tDurability: %.0f\n\t\tDamage Reduction: %.0f%%", this.durability, this.percentReduction);
	}
	
	
	public double getReducedDamage(double damageDealt) {
		if (!isItemUseable()) {return damageDealt;} //Uses method from parent class to check if durability > 0
		
		double damageReturn 	= damageDealt;
		double percentDamage 	= damageReturn*(percentReduction/100);
		
		damageReturn 	-= percentDamage; //Damage is reduced by percent
		decrementDurability(percentDamage); //Decrements durability
		Utilities.print(String.format("%s SUCESSFULLY REDUCED MOB'S ATTACK TO %.1f DAMAGE", this.getName(), damageReturn), true);
		
		return damageReturn;
	}
	
}