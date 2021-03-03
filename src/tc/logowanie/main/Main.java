package tc.logowanie.main;

import org.bukkit.plugin.java.JavaPlugin;

import tc.logowanie.cmds.ChangePass;
import tc.logowanie.cmds.Login;
import tc.logowanie.cmds.Register;
import tc.logowanie.events.onJoin;
import tc.logowanie.events.onMove;
import tc.logowanie.playerdata.PlayerData;

public class Main extends JavaPlugin {
	
	PlayerData pd;
	
	public Main() {
		pd = PlayerData.getInstance();
	}
	
	public void onEnable() {
		pd.setup(this);
		
		new ChangePass(this);
		new Login(this);
		new Register(this);
		
		getServer().getPluginManager().registerEvents(new onJoin(), this);
		getServer().getPluginManager().registerEvents(new onMove(), this);
	}
}
