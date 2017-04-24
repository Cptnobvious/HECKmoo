package debug;

import world.Room;
import world.Zone;

public class MakeFakeZone {

	public static Zone getFakeZone(){
		Zone zone = new Zone();
		zone.addRoom(new Room());
		return zone;
	}
	
}
