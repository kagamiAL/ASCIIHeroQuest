package asciiHeroQuest.Entities;

import asciiHeroQuest.StatusEffects;
import asciiHeroQuest.Utilities;

public class Mobs {

	public static class Mob extends Entity {
		
		protected String asciiDesign;
		protected String asciiLogo;
		protected String attackSpecialty; //Attack specialty is just the status effect the mob can inflict
		protected int procChance; //How often will the status occur when player is attacked

		public String getAttackSpecialty() { 
			return attackSpecialty;
		}

		public void setAttackSpecialty(String attackSpecialty) {
			if (StatusEffects.STATUSES.contains(attackSpecialty)) {
				this.attackSpecialty = attackSpecialty;
			}
		}

		public int getProcChance() {
			return procChance;
		}
		
		public void setProcChance(int procChance) {
			this.procChance = procChance;
		}
		
		
		public void printAsciiLogo() { //Logo is a smaller ascii art visual
			Utilities.print(asciiLogo, true);
		}

		public void setAsciiLogo(String asciiLogo) {
			this.asciiLogo = asciiLogo;
		}

		public void printAsciiDesign() {
			Utilities.print(asciiDesign, true);
		}

		public void setAsciiDesign(String asciiDesign) {
			this.asciiDesign = asciiDesign;
		}

		public void attackEntity(Entity enemyEntity, double scaleFactor) {
			
			enemyEntity.damageEntity(baseAttack*scaleFactor);

			if (enemyEntity.getStatusEffect().equals("None")) { //If no status effect currently active then
				int randomChance = (int)(Math.random()*100);
	
				if (randomChance <= procChance) {
					Utilities.print(String.format("%s is procced with %s!", enemyEntity.getName(), attackSpecialty), true);
					enemyEntity.setStatusEffect(attackSpecialty);
				}
			}
		}
		
	}
	
	public static class Skeleton extends Mob {
		
		final double BASE_ATTACK 	=  15;
		final double MAX_HEALTH		= 125;
		final String SPECIALITY		= "Bleeding";
		final int PROC_CHANCE		= 25;
		
		{
			this.setName("SKELETON");
			this.setBaseAttack(BASE_ATTACK);
			this.setMaximumHealth(MAX_HEALTH);
			this.setAttackSpecialty(SPECIALITY);
			this.setProcChance(PROC_CHANCE);
			this.setAsciiLogo(".AMMMMMMMMMMA.          \r\n"
					+ "       .AV. :::.:.:.::MA.        \r\n"
					+ "      A' :..        : .:`A       \r\n"
					+ "     A'..              . `A.     \r\n"
					+ "    A' :.    :::::::::  : :`A    \r\n"
					+ "    M  .    :::.:.:.:::  . .M    \r\n"
					+ "    M  :   ::.:.....::.:   .M    \r\n"
					+ "    V : :.::.:........:.:  :V    \r\n"
					+ "   A  A:    ..:...:...:.   A A   \r\n"
					+ "  .V  MA:.....:M.::.::. .:AM.M   \r\n"
					+ " A'  .VMMMMMMMMM:.:AMMMMMMMV: A  \r\n"
					+ ":M .  .`VMMMMMMV.:A `VMMMMV .:M: \r\n"
					+ " V.:.  ..`VMMMV.:AM..`VMV' .: V  \r\n"
					+ "  V.  .:. .....:AMMA. . .:. .V   \r\n"
					+ "   VMM...: ...:.MMMM.: .: MMV    \r\n"
					+ "       `VM: . ..M.:M..:::M'      \r\n"
					+ "         `M::. .:.... .::M       \r\n"
					+ "          M:.  :. .... ..M       \r\n"
					+ " VK       V:  M:. M. :M .V       \r\n"
					+ "          `V.:M.. M. :M.V'");
			this.setAsciiDesign("                              _.--\"\"-._\r\n"
					+ "  .                         .\"         \".\r\n"
					+ " / \\    ,^.         /(     Y             |      )\\\r\n"
					+ "/   `---. |--'\\    (  \\__..'--   -   -- -'\"\"-.-'  )\r\n"
					+ "|        :|    `>   '.     l_..-------.._l      .'\r\n"
					+ "|      __l;__ .'      \"-.__.||_.-'v'-._||`\"----\"\r\n"
					+ " \\  .-' | |  `              l._       _.'\r\n"
					+ "  \\/    | |                   l`^^'^^'j\r\n"
					+ "        | |                _   \\_____/     _\r\n"
					+ "        j |               l `--__)-'(__.--' |\r\n"
					+ "        | |               | /`---``-----'\"1 |  ,-----.\r\n"
					+ "        | |               )/  `--' '---'   \\'-'  ___  `-.\r\n"
					+ "        | |              //  `-'  '`----'  /  ,-'   I`.  \\\r\n"
					+ "      _ L |_            //  `-.-.'`-----' /  /  |   |  `. \\\r\n"
					+ "     '._' / \\         _/(   `/   )- ---' ;  /__.J   L.__.\\ :\r\n"
					+ "      `._;/7(-.......'  /        ) (     |  |            | |\r\n"
					+ "      `._;l _'--------_/        )-'/     :  |___.    _._./ ;\r\n"
					+ "        | |                 .__ )-'\\  __  \\  \\  I   1   / /\r\n"
					+ "        `-'                /   `-\\-(-'   \\ \\  `.|   | ,' /\r\n"
					+ "                           \\__  `-'    __/  `-. `---'',-'\r\n"
					+ "                              )-._.-- (        `-----'\r\n"
					+ "                             )(  l\\ o ('..-.\r\n"
					+ "                       _..--' _'-' '--'.-. |\r\n"
					+ "                __,,-'' _,,-''            \\ \\\r\n"
					+ "               f'. _,,-'                   \\ \\\r\n"
					+ "              ()--  |                       \\ \\\r\n"
					+ "                \\.  |                       /  \\\r\n"
					+ "                  \\ \\                      |._  |\r\n"
					+ "                   \\ \\                     |  ()|\r\n"
					+ "                    \\ \\                     \\  /\r\n"
					+ "                     ) `-.                   | |\r\n"
					+ "                    // .__)                  | |\r\n"
					+ "                 _.//7'                      | |\r\n"
					+ "               '---'                         j_| `\r\n"
					+ "                                            (| |\r\n"
					+ "                                             |  \\\r\n"
					+ "                                             |lllj\r\n"
					+ "                                             |||||  -nabis");
		}
	}
	
	public static class Ghoul extends Mob {
		final double BASE_ATTACK 	= 50;
		final double MAX_HEALTH		= 250;
		final String SPECIALITY		= "Poisoned";
		final int PROC_CHANCE		= 30;
		
		{
			this.setName("GHOUL");
			this.setBaseAttack(BASE_ATTACK);
			this.setMaximumHealth(MAX_HEALTH);
			this.setAttackSpecialty(SPECIALITY);
			this.setProcChance(PROC_CHANCE);
			this.setAsciiLogo("                            ,-.\r\n"
					+ "       ___,---.__          /'|`\\          __,---,___\r\n"
					+ "    ,-'    \\`    `-.____,-'  |  `-.____,-'    //    `-.\r\n"
					+ "  ,'        |           ~'\\     /`~           |        `.\r\n"
					+ " /      ___//              `. ,'          ,  , \\___      \\\r\n"
					+ "|    ,-'   `-.__   _         |        ,    __,-'   `-.    |\r\n"
					+ "|   /          /\\_  `   .    |    ,      _/\\          \\   |\r\n"
					+ "\\  |           \\ \\`-.___ \\   |   / ___,-'/ /           |  /\r\n"
					+ " \\  \\           | `._   `\\\\  |  //'   _,' |           /  /\r\n"
					+ "  `-.\\         /'  _ `---'' , . ``---' _  `\\         /,-'\r\n"
					+ "     ``       /     \\    ,='/ \\`=.    /     \\       ''\r\n"
					+ "             |__   /|\\_,--.,-.--,--._/|\\   __|\r\n"
					+ "             /  `./  \\\\`\\ |  |  | /,//' \\,'  \\\r\n"
					+ "eViL        /   /     ||--+--|--+-/-|     \\   \\\r\n"
					+ "           |   |     /'\\_\\_\\ | /_/_/`\\     |   |\r\n"
					+ "            \\   \\__, \\_     `~'     _/ .__/   /\r\n"
					+ "             `-._,-'   `-._______,-'   `-._,-'");
			this.setAsciiDesign("                                           .\"\"--.._\r\n"
					+ "                                           []      `'--.._\r\n"
					+ "                                           ||__           `'-,\r\n"
					+ "                                         `)||_ ```'--..       \\\r\n"
					+ "                     _                    /|//}        ``--._  |\r\n"
					+ "                  .'` `'.                /////}              `\\/\r\n"
					+ "                 /  .\"\"\".\\              //{///    \r\n"
					+ "                /  /_  _`\\\\            // `||\r\n"
					+ "                | |(_)(_)||          _//   ||\r\n"
					+ "                | |  /\\  )|        _///\\   ||\r\n"
					+ "                | |L====J |       / |/ |   ||\r\n"
					+ "               /  /'-..-' /    .'`  \\  |   ||\r\n"
					+ "              /   |  :: | |_.-`      |  \\  ||\r\n"
					+ "             /|   `\\-::.| |          \\   | ||\r\n"
					+ "           /` `|   /    | |          |   / ||\r\n"
					+ "         |`    \\   |    / /          \\  |  ||\r\n"
					+ "        |       `\\_|    |/      ,.__. \\ |  ||\r\n"
					+ "        /                     /`    `\\ ||  ||\r\n"
					+ "       |           .         /        \\||  ||\r\n"
					+ "       |                     |         |/  ||\r\n"
					+ "       /         /           |         (   ||\r\n"
					+ "      /          .           /          )  ||\r\n"
					+ "     |            \\          |             ||\r\n"
					+ "    /             |          /             ||\r\n"
					+ "   |\\            /          |              ||\r\n"
					+ "   \\ `-._       |           /              ||\r\n"
					+ "    \\ ,//`\\    /`           |              ||\r\n"
					+ "     ///\\  \\  |             \\              ||\r\n"
					+ "    |||| ) |__/             |              ||\r\n"
					+ "    |||| `.(                |              ||\r\n"
					+ "    `\\\\` /`                 /              ||\r\n"
					+ "       /`                   /              ||\r\n"
					+ " jgs  /                     |              ||\r\n"
					+ "     |                      \\              ||\r\n"
					+ "    /                        |             ||\r\n"
					+ "  /`                          \\            ||\r\n"
					+ "/`                            |            ||\r\n"
					+ "`-.___,-.      .-.        ___,'            ||\r\n"
					+ "         `---'`   `'----'`");
		}	
	}

	public static class Specter extends Mob {
		final double BASE_ATTACK 	= 100;
		final double MAX_HEALTH		= 650;
		final String SPECIALITY		= "Shocked";
		final int PROC_CHANCE		= 35;
		
		{
			this.setName("SPECTER");
			this.setBaseAttack(BASE_ATTACK);
			this.setMaximumHealth(MAX_HEALTH);
			this.setAttackSpecialty(SPECIALITY);
			this.setProcChance(PROC_CHANCE);
			this.setAsciiLogo("     .-.\r\n"
					+ "   .'   `.\r\n"
					+ "   :g g   :\r\n"
					+ "   : o    `.\r\n"
					+ "  :         ``.\r\n"
					+ " :             `.\r\n"
					+ ":  :         .   `.\r\n"
					+ ":   :          ` . `.\r\n"
					+ " `.. :            `. ``;\r\n"
					+ "    `:;             `:'\r\n"
					+ "       :              `.\r\n"
					+ " jgs    `.              `.     .\r\n"
					+ "          `'`'`'`---..,___`;.-'");
			this.setAsciiDesign("\r\n"
					+ "              .o######0o.\r\n"
					+ "             0###########0.      .\r\n"
					+ "            o####\" \"######0.    (## m#o\r\n"
					+ "            ####(    ######0  ._ ##.##\"nn\r\n"
					+ "            0####o   ###\" ## (##o.######\"\r\n"
					+ "    o00o.    0#####o,##. ,#\"  \"#######(\r\n"
					+ "  .0#####0.   0###########0     ########\r\n"
					+ " .0#######0.   \"0#########\"  _.o###'\"00\"\r\n"
					+ ".0###########o._ \"\"################       _  .\r\n"
					+ "0####\" \"#########################0      .0#0n0\r\n"
					+ "#####.   \"\"#####################\"    _  0#####\r\n"
					+ "0#####.     \"###################._.o##o.#####\"\r\n"
					+ "\"0#####..##mn \"\"#############################\r\n"
					+ "  \"0#######\"\"_    \"\"##################\"#####\"\r\n"
					+ "     \"\"####m###m      \"\"############\"   ####\r\n"
					+ "    .########\"\"\"         .########\"     \"##\"\r\n"
					+ "    ####\"##\"###o        (0######\"        \"\"\r\n"
					+ "    \"##\".###,##     .o#o \"\"####.\r\n"
					+ "         \"##\"      .0############.\r\n"
					+ "                 .n##RADIUS#######\r\n"
					+ "");
		}	
	}
	
	public static class Dragon extends Mob {
		final double BASE_ATTACK 	= 250;
		final double MAX_HEALTH		= 5000;
		final String SPECIALITY		= "Bleeding";
		final int PROC_CHANCE		= 45;
		
		{
			this.setName("DAMOCLES, HELL'S GUARDIAN");
			this.setBaseAttack(BASE_ATTACK);
			this.setMaximumHealth(MAX_HEALTH);
			this.setAttackSpecialty(SPECIALITY);
			this.setProcChance(PROC_CHANCE);
			this.setAsciiLogo(",,\r\n"
					+ "`\"\"*$b..\r\n"
					+ "     \"\"*$o.\r\n"
					+ "         \"$$o.\r\n"
					+ "           \"*$$o.\r\n"
					+ "              \"$$$o.\r\n"
					+ "                \"$$$$bo...       ..o:\r\n"
					+ "                  \"$$$$$$$$booocS$$$    ..    ,.\r\n"
					+ "               \".    \"*$$$$SP     V$o..o$$. .$$$b\r\n"
					+ "                \"$$o. .$$$$$o. ...A$$$$$$$$$$$$$$b\r\n"
					+ "          \"\"bo.   \"*$$$$$$$$$$$$$$$$$$$$P*$$$$$$$$:\r\n"
					+ "             \"$$.    V$$$$$$$$$P\"**\"\"*\"'   VP  * \"l\r\n"
					+ "               \"$$$o.4$$$$$$$$X\r\n"
					+ "                \"*$$$$$$$$$$$$$AoA$o..oooooo..           .b\r\n"
					+ "                       .X$$$$$$$$$$$P\"\"     \"\"*oo,,     ,$P\r\n"
					+ "                      $$P\"\"V$$$$$$$:    .        \"\"*****\"\r\n"
					+ "                    .*\"    A$$$$$$$$o.4;      .\r\n"
					+ "                         .oP\"\"   \"$$$$$$b.  .$;\r\n"
					+ "                                  A$$$$$$$$$$P\r\n"
					+ "                                  \"  \"$$$$$P\"\r\n"
					+ "                                      $$P*\"\r\n"
					+ "                                     .$\"\r\n"
					+ "                                     \"");
			this.setAsciiDesign("\r\n"
					+ "                                        ,   ,\r\n"
					+ "                                        $,  $,     ,\r\n"
					+ "                                        \"ss.$ss. .s'\r\n"
					+ "                                ,     .ss$$$$$$$$$$s,\r\n"
					+ "                                $. s$$$$$$$$$$$$$$`$$Ss\r\n"
					+ "                                \"$$$$$$$$$$$$$$$$$$o$$$       ,\r\n"
					+ "                               s$$$$$$$$$$$$$$$$$$$$$$$$s,  ,s\r\n"
					+ "                              s$$$$$$$$$\"$$$$$$\"\"\"\"$$$$$$\"$$$$$,\r\n"
					+ "                              s$$$$$$$$$$s\"\"$$$$ssssss\"$$$$$$$$\"\r\n"
					+ "                             s$$$$$$$$$$'         `\"\"\"ss\"$\"$s\"\"\r\n"
					+ "                             s$$$$$$$$$$,              `\"\"\"\"\"$  .s$$s\r\n"
					+ "                             s$$$$$$$$$$$$s,...               `s$$'  `\r\n"
					+ "                         `ssss$$$$$$$$$$$$$$$$$$$$####s.     .$$\"$.   , s-\r\n"
					+ "                           `\"\"\"\"$$$$$$$$$$$$$$$$$$$$#####$$$$$$\"     $.$'\r\n"
					+ "                                 \"$$$$$$$$$$$$$$$$$$$$$####s\"\"     .$$$|\r\n"
					+ "                                  \"$$$$$$$$$$$$$$$$$$$$$$$$##s    .$$\" $\r\n"
					+ "                                   $$\"\"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\"   `\r\n"
					+ "                                  $$\"  \"$\"$$$$$$$$$$$$$$$$$$$$S\"\"\"\"'\r\n"
					+ "                             ,   ,\"     '  $$$$$$$$$$$$$$$$####s\r\n"
					+ "                             $.          .s$$$$$$$$$$$$$$$$$####\"\r\n"
					+ "                 ,           \"$s.   ..ssS$$$$$$$$$$$$$$$$$$$####\"\r\n"
					+ "                 $           .$$$S$$$$$$$$$$$$$$$$$$$$$$$$#####\"\r\n"
					+ "                 Ss     ..sS$$$$$$$$$$$$$$$$$$$$$$$$$$$######\"\"\r\n"
					+ "                  \"$$sS$$$$$$$$$$$$$$$$$$$$$$$$$$$########\"\r\n"
					+ "           ,      s$$$$$$$$$$$$$$$$$$$$$$$$#########\"\"'\r\n"
					+ "           $    s$$$$$$$$$$$$$$$$$$$$$#######\"\"'      s'         ,\r\n"
					+ "           $$..$$$$$$$$$$$$$$$$$$######\"'       ....,$$....    ,$\r\n"
					+ "            \"$$$$$$$$$$$$$$$######\"' ,     .sS$$$$$$$$$$$$$$$$s$$\r\n"
					+ "              $$$$$$$$$$$$#####\"     $, .s$$$$$$$$$$$$$$$$$$$$$$$$s.\r\n"
					+ "   )          $$$$$$$$$$$#####'      `$$$$$$$$$###########$$$$$$$$$$$.\r\n"
					+ "  ((          $$$$$$$$$$$#####       $$$$$$$$###\"       \"####$$$$$$$$$$\r\n"
					+ "  ) \\         $$$$$$$$$$$$####.     $$$$$$###\"             \"###$$$$$$$$$   s'\r\n"
					+ " (   )        $$$$$$$$$$$$$####.   $$$$$###\"                ####$$$$$$$$s$$'\r\n"
					+ " )  ( (       $$\"$$$$$$$$$$$#####.$$$$$###'                 .###$$$$$$$$$$\"\r\n"
					+ " (  )  )   _,$\"   $$$$$$$$$$$$######.$$##'                .###$$$$$$$$$$\r\n"
					+ " ) (  ( \\.         \"$$$$$$$$$$$$$#######,,,.          ..####$$$$$$$$$$$\"\r\n"
					+ "(   )$ )  )        ,$$$$$$$$$$$$$$$$$$####################$$$$$$$$$$$\"\r\n"
					+ "(   ($$  ( \\     _sS\"  `\"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$S$$,\r\n"
					+ " )  )$$$s ) )  .      .   `$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\"'  `$$\r\n"
					+ "  (   $$$Ss/  .$,    .$,,s$$$$$$##S$$$$$$$$$$$$$$$$$$$$$$$$S\"\"        '\r\n"
					+ "    \\)_$$$$$$$$$$$$$$$$$$$$$$$##\"  $$        `$$.        `$$.\r\n"
					+ "        `\"S$$$$$$$$$$$$$$$$$#\"      $          `$          `$\r\n"
					+ "            `\"\"\"\"\"\"\"\"\"\"\"\"\"'         '           '           '");
		}	
	}
	
}
