package verb.globalverbs;

import playermanager.Player;
import verb.Verb;
import world.World;

public class VerbMap extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"map", "m"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String map = "";
		
		for (int y = 0; y < 50; y++){
			for (int x = 0; x < 50; x++){
				map = map + World.getZoneByPlayer(ply).getMap().getMapPoint(x, y);
			}
			map = map + "\n";
		}
		
		ply.sendMessageToClient(map);
		
		return true;
	}

	@Override
	public String getHelpText() {
		return "Shows you the map";
	}

}
