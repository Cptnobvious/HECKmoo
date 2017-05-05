package verb.adminverbs;

import java.util.ArrayList;

import playermanager.Player;
import textparser.TextParser;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbAtHelp extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@help"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String qverb = StringUtility.getWordInString(str, 2);
		if (qverb == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, StringUtility.getLinebreak()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, getHelpText()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, StringUtility.getLinebreak()));
			ArrayList<String> verbs = TextParser.getAllAdminVerbs();
			String templines = "";
			for (int i = 0; i < verbs.size(); i++){
				templines = templines + verbs.get(i) + "     ";
				if (((i % 5) == 0 && (i > 1)) || ((i + 1) == verbs.size())){
					ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, templines));
				}
			}
			return true;
		}
		
		Verb verb = TextParser.findVerb(ply, qverb);
		if (verb != null){
			String help = ColorStrings.getColoredText(ColorStrings.CYAN, verb.getHelpText());
			ply.sendMessageToClient(help);
			return true;
		}
		
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, "Verb not found"));
		
		return false;
	}

	@Override
	public String getHelpText() {
		return "@help <command>\n@help <subject>";
	}

}
