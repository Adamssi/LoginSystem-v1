package tc.logowanie.cmds;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tc.logowanie.events.onJoin;
import tc.logowanie.main.Main;
import tc.logowanie.playerdata.PlayerData;

public class Register implements CommandExecutor {
	
	Main plugin;
	PlayerData pd;
	
	public Register(Main m) {
		plugin = m;
		m.getCommand("register").setExecutor(this);
		
		pd = PlayerData.getInstance();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String lab, String[] args) {	
		Player p = (Player) sender;
		UUID uuid = p.getUniqueId();
		
		if(pd.getData().getString(uuid + ".pass") == null) {
			if(args.length == 2) {
				String pass1 = args[0];
				
				if(args[0].equalsIgnoreCase(args[1])) {
					pd.getData().set(uuid + ".pass", pass1);
					pd.saveData();
					
					onJoin.loggedIn.put(uuid, true);
					
					p.sendMessage("§7You have been registered!");
				} else {
					p.sendMessage("§7These passwords do not match!");
				}
			} else {
				p.sendMessage("§7Usage: §e/register [password] [repeat password]");
			}
		} else {
			p.sendMessage("§7You are already registered!");
		}
		return false;
	}
}
