package asciiHeroQuest.Items;

public class Weapon extends Items.CombatItem {
	private double damage		= 0;
	
	{
		type = "Weapon";
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public String getItemInfo() {
		return String.format("%s:\n\t\tPrice: %d\n\t\tDamage: %.0f", this.getName(), this.getPrice(), this.getDamage());
	}
	
}

