package asciiHeroQuest;

public class DialogueSystem {

	private static final int TYPEWRITER_TIME		= 25;
	
	public static final String[] GAME_OVER_TEXT		= {
			"GAME OVER! You failed to protect the village from the wretched dragon",
			"Death envelops your decaying body, eradicating the progress you've made",
			"DATA ERASED!"
	};
	
	public static final String[] OPENING_DIALOGUE	= {
			"Darkness, darkness is all you can see, it fills your vision leaving no room for light to pierce through",
			"A faint voice, lost in the vast abyss that covers your eyes, gradually becomes louder and louder",
			"A sudden shock in your spine disturbs your rest, you awake to being surrounded by quaint yet stylish furniture"
	};
	
	public static final String[] ARYA_DIALOGUE		= {
			"By the heavens! You've awoken! I was starting to worry that you might have passed",
			"Goodness, you look as if you know naught about our settlement's situation",
			"You see, our village was once rich and prosperous, until Damocles burned it down to ruins",
			"We've yet to scout a hero to slay that abomination of a dragon that terrorizes our village",
			"Could it be? Could you save our town from that accursed beast?",
			"Excuse my manners, I forgot to ask for your name. What is your name hero?"
	};
	
	public static final String[] ARYA_DIALOGUE2		= {
			"%s, a fine name indeed. I'm delighted to have met you",
			"Now, you must head to town %s to get started with your quest",
			"Here's a lump sum of 1100 gold to help you on your quest",
	};
	
	public static final String[] ARTEMIS_DIALOGUE	= {
		"HEY YOU, You look like you could rough a few mobs up",
		"Our crops lately have been raided by Damocles's henchmen, the skeletons",
		"These suckers don't go down easily, which is why I need you",
		"How's it sound, feel like beating up some skeletons for a lump sum of gold?"
	};
	
	public static final String[] BRUTUS_DIALOGUE	= {
		"Damned ghouls, terrorizing the local town square, bastards have nothing better to do",
		"Hey you, yeah you, you look quite capable, well versed in the sword I assume?",
		"Ah, to hell with it, just swing the damn sword alright, you'll be rewarded",
		"Go on now, the ghouls aren't gonna wait for you",
	};
	
	public static final String[] ASTORIA_DIALOGUE	= {
		"***You encounter a mortally wounded priest bleeding out on the marble floor***",
		"***You scan your surroundings, hidden within the Paladin's Sanctum, spectres viciously hunt for human flesh***",
		"Please...help us...I don't think we can hold out any longer",
		"What is it that you want? ...gold? Please...just rescue my sisters...I BEG OF YOU",
	};
	
	public static final String[] GAME_COMPLETE		= {
		"Your blade hacks through the beast's scaly neck, its lifeless corpse resting along the lush forest",
		"Cheers roar amongst the crowd that has gathered behind you, you have finally ended Damocles's tyranny",
		"A noble hero indeed, %s, you have freed the town from its imprisonnment",
		"GAME COMPLETE!",
	};
	
	//Prints out text and prompts user to continue
	public static void main(String[] dialogueArray, String speakerName) {
		for (int x = 0; x < dialogueArray.length; x++) {
			String text = dialogueArray[x];
			System.out.print(speakerName + ": ");
			for(int i = 0; i < text.length(); i++){
			    System.out.printf("%c", text.charAt(i));
			    Utilities.wait(TYPEWRITER_TIME);
			}
			Utilities.skipLines(1);
			Utilities.skipLines(1);
			TransferredData.promptUserToContinue();
		}
	}
	
	//Sets player name in dialogue
	public static void setNameInDialogue(String[] dialogueArray, String playerName) {
		for (int x = 0; x < dialogueArray.length; x++) {
			String dialogueString	= dialogueArray[x];
			if (dialogueString.contains("%")) {
				dialogueArray[x]	= String.format(dialogueString, playerName);
			}
		}
	}
	
}
