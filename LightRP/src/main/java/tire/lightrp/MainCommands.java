package tire.lightrp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommands implements CommandExecutor  {
    private LightRP plugin;
    public MainCommands(LightRP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1 && args[0] == "reload") {
            if(sender.hasPermission("lrp.reload")) {
                plugin.reloadConfig();
                sender.sendMessage("&2The config was successfully reloaded.".replace("&", "\u00a7"));
            } else {
                sender.sendMessage("&cYou do not have sufficient permissions to execute this command.".replace("&", "\u00a7"));
            }
            return true;
        }
        return false;
    }
}
