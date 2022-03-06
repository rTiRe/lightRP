package tire.lightrp;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LightRP extends JavaPlugin {

    @Override
    public void onEnable() {

        File config = new File(getDataFolder() + File.separator + "src/main/resources/config.yml");
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        String[] cmds = {"me", "do", "n"};

        for(String i : cmds) {
            try {
                String d = getConfig().getString(i + ".distance").trim();
                if(d.length() == 0) { d = "0"; }
                float f = Float.parseFloat(d);
            } catch(Exception e) {
                getLogger().info(i + ".distance can only contain a number!");
            }
        }

        getCommand("me").setExecutor(new RPCommands(this));
        getCommand("do").setExecutor(new RPCommands(this));
        getCommand("n").setExecutor(new RPCommands(this));

        getCommand("lrp").setExecutor(new MainCommands(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
