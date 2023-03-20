package asciiHeroQuest;
import java.util.HashSet;

public class StatusEffects {
	
	public static final int BLEED_DAMAGE	= 5;
	
	public static final int STATUS_LENGTH	= 3;

	public static final HashSet<String> STATUSES 	= new HashSet<String>();
	
	private static final String[] ENTITY_STATUSES	= {"Bleeding", "Shocked", "Poisoned", "None"};
	
	static {
		for (int x = 0; x < ENTITY_STATUSES.length; x++) {
			STATUSES.add(ENTITY_STATUSES[x]);
		}
	}
	
}
