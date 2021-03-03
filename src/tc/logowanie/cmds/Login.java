package tc.logowanie.cmds;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tc.logowanie.events.onJoin;
import tc.logowanie.main.Main;
import tc.logowanie.playerdata.PlayerData;

public class Login implements CommandExecutor {
	
	Main plugin;
	PlayerData pd;
	
	public Login(Main m) {
		plugin = m;
		m.getCommand("login").setExecutor(this);
		
		pd = PlayerData.getInstance();	
	}
	
	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String lab, String[] args) {
		Player p = (Player) sender;
		UUID uuid = p.getUniqueId();
		
		if(onJoin.loggedIn.get(uuid) == false) {
			if(args.length == 1) {
				if(pd.getData().getString(uuid + ".pass") != null) {
					String pass = args[0];
					String actualPass = pd.getData().getString(uuid + ".pass");
					
					if(pass.equals(actualPass)) {
						onJoin.loggedIn.put(uuid, true);
						
						p.sendMessage("§7You have been logged!");
					} else {
						p.kickPlayer("§7You entered your password incorrectly "
								+ "§7Try again");
					}
				} else {
					p.sendMessage("§7Before logging in §eYou must register!");
					p.sendMessage("§7Usage: §e/register [password] [repeat password]");
				}
			} else {
				p.sendMessage("§7Usage: §e/login [password]");
			}
		} else {
			p.sendMessage("You are already login!");
		}
		return false;
	}

}
