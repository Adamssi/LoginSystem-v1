package tc.logowanie.playerdata;

public class PlayerAPI {
	
	private static PlayerData pd;
	
	static {
		pd = PlayerData.getInstance();
	}

}
