package world.prefabs;

import world.Room;
import world.Zone;

public class Origin {
	
	public static Zone getOriginZone(){
		Zone zone = new Zone();
		zone.setZoneName("The Origin");
		zone.setZoneID("ORGNA");
		Room room = new Room();
		room.setRoomName("The Origin");
		room.setRoomDescription("This is where it all begins");
		zone.addRoom(room);
		return zone;
	}

}
