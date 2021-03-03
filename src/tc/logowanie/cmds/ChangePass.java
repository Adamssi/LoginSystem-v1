package tc.logowanie.cmds;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tc.logowanie.events.onJoin;
import tc.logowanie.main.Main;
import tc.logowanie.playerdata.PlayerData;

public class ChangePass implements CommandExecutor {
	
	Main plugin;
	PlayerData pd;
	
	public ChangePass(Main m) {
		plugin = m;
		m.getCommand("changepass").setExecutor(this);
		
		pd = PlayerData.getInstance();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String lab, String[] args) {
		Player p = (Player) sender;
		UUID uuid = p.getUniqueId();
		
		if(onJoin.loggedIn.get(uuid) == true) {
			if(args.length == 3) {
				String old = args[0];
				String new1 = args[1];
				String new2 = args[2];
				String actualPass = pd.getData().getString(uuid + ".pass");
				
				if(old.equals(actualPass)) {
					if(new1.equals(new2)) {
						pd.getData().set(uuid + ".pass", new2);
						pd.saveData();
						
						p.sendMessage("§7Your password has been changed!");
					} else {
						p.sendMessage("§7Your new passwords do not match!");
					}
				} else {
					p.sendMessage("§7Your old password does not match!");	
				} 
			} else {
				p.sendMessage("§7Usage: §e/changepass [old password] [new password] [repeat new password]");
			}
		} else {
			p.sendMessage("§7You must login to change your password");
		}
		return false;
	}
}
