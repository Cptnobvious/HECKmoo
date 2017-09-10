package verb.globalverbs;

import gameutils.Announcement;
import playermanager.Player;
import utility.ColorStrings;
import utility.RandomUtility;
import utility.StringUtility;
import verb.Verb;

public class VerbExRoll extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"!roll"};
		alias = temp;
		return true;
	}
	
	@Override
	public boolean setFlags() {
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordList(str);
		long rolled = 0;
		long max = 0;
		if (args.length == 1){
			rolled = RandomUtility.getRandomNumber(0, 100);
		} else if (args.length == 2){
			try {
				max = Long.parseLong(args[1]);
			} catch (NumberFormatException e){
				sendFeedback(ply, ColorStrings.RED, "That was not a number!");
				return false;
			}
			rolled = RandomUtility.getRandomNumber(0, (int) max);
		}
		
		String ann = ply.getActor().getName() + " rolled a " + rolled + "!";
		ann = ColorStrings.getColoredText(true, ColorStrings.YELLOW, ColorStrings.BLACK, ann);
		
		Announcement.announceToRoomRaw(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), ann);
		
		return false;
		
	}

	@Override
	public String getHelpText() {
		return "!roll\n!roll <maxvalue>";
	}

}
