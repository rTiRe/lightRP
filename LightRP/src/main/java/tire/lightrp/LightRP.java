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
        saveConfig();
        reloadConfig();

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

        getCommand("me").setExecutor(new Commands(this , "me"));
        getCommand("do").setExecutor(new Commands(this , "do"));
        getCommand("n").setExecutor(new Commands(this , "n"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
