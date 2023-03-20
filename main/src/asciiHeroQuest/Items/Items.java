package asciiHeroQuest.Items;
import asciiHeroQuest.Player;
import asciiHeroQuest.Utilities;

public class Items {

	//Regular Item
	
	public static class Item {
		protected String type = ""; //Determines what combat item this is
		protected String name 	= "";
		protected Player mainPlayer;
		protected int price	= 0; //How much does the item cost

		//Sets the owner of the item
		public void setPlayerOwner(Player player) {
			mainPlayer = player;
		}
		
		//Gets what type of item this is
		public String getType() {
			return type;
		}

		//Gets the name of the item
		public String getName() {
			return name;
		}

		//Sets the name of the item
		public void setName(String name) {
			this.name = name;
		}

		//Get item price
		public int getPrice() {
			return price;
		}

		//Set item price
		public void setPrice(int price) {
			this.price = price;
		}
		
		//Returns a string that describes the item
		public String getItemInfo() {
			return String.format("%s:\n\t\tPrice: %d", this.getName(), this.getPrice());
		}
	}
	
	//Support Item
	
	public static class SupportItem extends Item {
		protected int amountOfItem	= 0;
		
		public void appendItemAmount(int x) {
			this.amountOfItem += x;
		}
		
		public boolean isItemUseable() {
			return (amountOfItem > 0);
		}
		
		public int getAmountOfItem() {
			return amountOfItem;
		}
		
		public void decrementAmountOfItem() {
			if (amountOfItem - 1 < 0) {
				this.amountOfItem = 0;
				return;
			}
			this.amountOfItem -= 1;
		}
		
		public void useItem() {
			decrementAmountOfItem();
			return;
		}
		
		public void setUpSupportItem(Player mainPlayer) {
			this.setPlayerOwner(mainPlayer);
			mainPlayer.appendItem(this, 0);
		}
		
	}
	
	//Combat Item
	
	public static class CombatItem extends Item {
		protected double durability	= 0;
		
		protected void decrementDurability(double decreaseAmt) { 
			if (durability - decreaseAmt < 0) {
				durability = 0;
				Utilities.print(this.getName() + " IS BROKEN", true);
				return;
			}
			durability -= decreaseAmt;
		}
		
		protected boolean isItemUseable() {
			return (durability > 0);
		}
		
	}
	
	
}
