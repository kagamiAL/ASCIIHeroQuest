package asciiHeroQuest;

import java.util.HashMap;

import asciiHeroQuest.Entities.Entity;

public class SkillClass {

	public static class Skill {
		
		private int SKILL_COOLDOWN		= 1;
		
		private String skillName		= "";
		private int skillDamage			= 0;
		private int cooldownCounter		= 0;
		private String skillSpecialty	= "None";
		private String skillDescription	= "";
		
		//Attacks enemy with skills and applies status effect
		public void main(Player mainPlayer, Entity enemyMob) {
			if (this.skillIsOnCooldown()) {return;}
			enemyMob.damageEntity(this.skillDamage + mainPlayer.getTotalDamage());
			if (enemyMob.getStatusEffect().equals("None")) {
				enemyMob.setStatusEffect(this.skillSpecialty);
				Utilities.print(String.format(skillDescription, enemyMob.getName()), true);
				Utilities.skipLines(1);
				Utilities.print(String.format("%s is procced with %s!", enemyMob.getName(), skillSpecialty), true);				
				this.cooldownCounter	= this.SKILL_COOLDOWN;
			}
		}
		
		//Updates cool down per round
		public void updateCooldown() {
			if (this.skillIsOnCooldown()) {
				this.cooldownCounter--;
			}
		}
		
		//If skill is on cool down returns true
		public boolean skillIsOnCooldown() {
			return (cooldownCounter > 0);
		}
		
		//Returns skill damage
		public int getSkillDamage() {
			return skillDamage;
		}
		
		//Sets skill damage
		public void setSkillDamage(int skillDamage) {
			this.skillDamage = skillDamage;
		}
		
		//Gets skill specialty
		public String getSkillSpecialty() {
			return skillSpecialty;
		}
		
		//Sets skill specialty
		public void setSkillSpecialty(String skillSpecialty) {
			if (StatusEffects.STATUSES.contains(skillSpecialty)) {
				this.skillSpecialty = skillSpecialty;
			}
		}
		
		//Returns skill description
		public String getSkillDescription() {
			return skillDescription;
		}
		
		//Sets skill description
		public void setSkillDescription(String skillDescription) {
			this.skillDescription = skillDescription;
		}

		
		//Gets skill name
		public String getSkillName() {
			return skillName;
		}
		
		//Sets skill name
		public void setSkillName(String skillName) {
			this.skillName = skillName;
		}
		
		
		
	}
	
	public static Skill[] skillArray;
		
	static {
		Skill Thrust	= new Skill();
		Thrust.setSkillName("Vicious Thrust");
		Thrust.setSkillDamage(50);
		Thrust.setSkillSpecialty("Bleeding");
		Thrust.setSkillDescription("You thrust at %s's neck, ripping apart the connected muscle running along it");
		
		
		
		Skill[] newSkillArray	= {Thrust,};
		SkillClass.skillArray	= newSkillArray;
	}
	
}
