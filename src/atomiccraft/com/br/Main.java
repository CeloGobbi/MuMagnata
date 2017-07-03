package atomiccraft.com.br;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener{

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    private static Chat chat = null;

    @Override
    public void onEnable(){
		System.out.println("§3[MuMagnata] §6Plugin habilitado!");
if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);

            return;
        }

if (!new File(getDataFolder(), "config.yml").exists()){
	saveResource("config.yml", false);

	getServer().getConsoleSender().sendMessage("§3[MuMagnata] §6Verificando config...");
	getServer().getConsoleSender().sendMessage("§3[MuMagnata] §4ERRO Config inexistente! §aCriando aquivo config.yml...");
	getServer().getConsoleSender().sendMessage("§3[MuMagnata] §2Arquivo config.yml criado!");
}else{
	getServer().getConsoleSender().sendMessage("§3[MuMagnata] §6Verificando config...");
	getServer().getConsoleSender().sendMessage("§3[MuMagnata] §2@O arquivo config.yml já existe!");

	setupEconomy();
	new BukkitRunnable() {
		public void run() {
			Checar();
			}
	}.runTaskLater(this, 10*20);
	Bukkit.getServer().getPluginManager().registerEvents(this, this);
}
        setupChat();

    }

public void onDisable(){
log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
}

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }


    public static Economy getEcononomy() {
        return econ;
    }
    public static Chat getChat() {
        return chat;
    }

	public void Checar(){
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run(){
				boolean novo = false;
				for(OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()){
					if(player.getName().toLowerCase() != getConfig().getString("Magnata_atual").toLowerCase()){
					if(econ.getBalance(player.getName()) > econ.getBalance(getConfig().getString("Magnata_atual"))){
						getConfig().set("Magnata_atual", player.getName());
						saveConfig();
						reloadConfig();
	                    novo = true;
					}
				}
			}
					if(novo == true){
						Bukkit.getServer().broadcastMessage("§2[Magnata] §aTemos um novo magnata!");
						Bukkit.getServer().broadcastMessage("§2[Magnata] §aParabéns para" + " " + getConfig().getString("Magnata_atual") + "!");
			  }
			}
		}.runTaskTimer(this, 5*20, 5*20);
     }
}
 