package tire.lightrp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPCommands implements CommandExecutor {
    private LightRP plugin;
    public RPCommands(LightRP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length >= 1) {
            if (sender instanceof Player) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++) sb.append(args[i]).append(" ");
                if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
                String msg = sb.toString().trim();
                String d = plugin.getConfig().getString(cmd.getName() + ".distance").trim();
                if (d.length() == 0) {
                    d = "0";
                }
                float distance = Float.parseFloat(d);
                String format = plugin.getConfig().getString(cmd.getName() + ".format").trim()
                        .replace("&", "\u00a7")
                        .replace("%displayName%", ((Player) sender).getDisplayName())
                        .replace("%name%", ((Player) sender).getName())
                        .replace("%message%", msg);
                if (distance == 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(format);
                    }
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (((Player) sender).getLocation().distance(p.getLocation()) <= distance && ((Player) sender).getWorld().equals(p.getWorld())) {
                            p.sendMessage(format);
                        }
                    }
                }
                return true;
            } else {
                plugin.getLogger().info("You cannot use this command as a console");
                return true;
            }
        }
        return false;
    }
}
