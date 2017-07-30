package atomiccraft.com.br;

import java.io.File;
import java.util.Collection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.Herbystar.TTA.TTA_Methods;
import net.milkbowl.vault.economy.Economy;

public class MuMagnata extends JavaPlugin implements Listener,CommandExecutor{
	private static final Logger log = Logger.getLogger("Minecraft");
	private static Economy econ = null;
    

	@Override
    public void onEnable(){
		getServer().getPluginCommand("magnata").setExecutor(this);
	if ((Bukkit.getPluginManager().getPlugin("Vault") == null) || 
		(!Bukkit.getPluginManager().getPlugin("Vault").isEnabled()))

    {
      Bukkit.getLogger().warning(getConfig().getString("Prefixo-Do-Plugin") + "Vault não encontrado!");
    }
    else
    {
      setupEconomy();
      Bukkit.getLogger().info("Vault encontrado com sucesso.");
    }
    	if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    getServer().getConsoleSender().sendMessage("§3Plugin: §6MuMagnata §2- §3Desenvolvedor: §6Mushu949 §2 §3Prefixo: ["+ getConfig().getString("Prefixo-Do-Plugin") +"§3]");

  if (!new File(getDataFolder(), "config.yml").exists()){
	saveResource("config.yml", false);
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Verificando config...");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§4ERRO: Config inexistente! §aCriando aquivo config.yml...");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§2Arquivo config.yml criado!");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§c------------------------------------------------------------------------");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§c- ATENÇÂO: Reinicie o servidor para o correto funcionamento do plugin! -");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§c------------------------------------------------------------------------");}else{
	getConfig().set("Magnata-antigo", getConfig().getString("Magnata_atual"));
	saveConfig();
	reloadConfig();
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Verificando config...");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§2O arquivo config.yml já existe!");
	setupEconomy();

	new BukkitRunnable() {
		public void run() {
			Checar();
			}
	}.runTaskLater(this, 10*20);
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Plugin habilitado!");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Magnata atual: §2" + getConfig().getString("Magnata_atual"));			            
	getServer().getPluginManager().registerEvents(this, this);	
}

}

public void onDisable(){;
log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
getConfig().set("Magnata-antigo", getConfig().getString("Magnata_atual"));
reloadConfig();
saveConfig();
getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Magnata atual: §2" + getConfig().getString("Magnata_atual"));
getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Magnata antigo: §2" + getConfig().getString("Magnata-antigo"));
getServer().getConsoleSender().sendMessage("§3[MuMagnata] §6Plugin desabilitado!");
getServer().getConsoleSender().sendMessage("§3[MuMagnata] §6Salvando config.yml!");
HandlerList.unregisterAll();
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



    public static Economy getEcononomy() {
        return econ;
    }

	public void Checar(){
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run(){
				getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Verificando se existe um novo magnata...");
				boolean novo = false;
				for(OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()){
					if(player.getName().toLowerCase() != getConfig().getString("Magnata_atual").toLowerCase()){
					if(econ.getBalance(player.getName()) > econ.getBalance(getConfig().getString("Magnata_atual"))){
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo01").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo02").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo03").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo04").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo05").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo06").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo07").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo08").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo09").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo10").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo11").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo12").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo13").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo14").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo15").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo16").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo17").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo18").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo19").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-antigo20").replaceAll("@exmagnata", getConfig().getString("Magnata_atual")));
						getConfig().set("Magnata_atual", player.getName());
						saveConfig();
	    				reloadConfig();
	                    novo = true;
					}
				}
			}
			if(novo == true){
				Collection<? extends Player> list = Bukkit.getOnlinePlayers();
				for (Player p : list) {
					TTA_Methods.sendTitle(//API
							p, getConfig().getString("title-novo-magnata")
							.replaceAll("@magnata", getConfig().getString("Magnata_atual"))
							.replaceAll("@exmagnata", getConfig().getString("Magnata-antigo"))
							, 5, 100, 5 //Tempo do title.
							, getConfig().getString("subtitle-novo-magnata")
							.replaceAll("@magnata", getConfig().getString("Magnata_atual"))
							.replaceAll("@exmagnata", getConfig().getString("Magnata-antigo"))
							, 5, 100, 5); //Tempo do subtitle.
				}
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage(getConfig().getString("msg-novo-magnata01").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")));
				Bukkit.getServer().broadcastMessage(getConfig().getString("msg-novo-magnata02").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")));
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual01").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual02").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual03").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual04").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual05").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual06").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual07").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual08").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual09").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual10").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual11").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual12").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual13").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual14").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual15").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual16").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual17").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual18").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual19").replaceAll("@magnata", getConfig().getString("Magnata_atual")));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), getConfig().getString("Comandos-magnata-atual20").replaceAll("@magnata", getConfig().getString("Magnata_atual")));

				}
		              }
        }.runTaskLater(this, 300*20); //Temporizador que verifica se existe um nono magnata, ex1: 10*20 = depois de 10 segundo verificar. ex2: 300*20 = depois de 300 segundos verificar.
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(!(arg0 instanceof Player)) {
			arg0.sendMessage("§2"+ getConfig().getString("Prefixo-Do-Plugin") + " " + getConfig().getString("ERRO01"));
			return true;
		}
	    if (arg1.getName().equalsIgnoreCase("magnata")) {//comando
	      Player p = (Player)arg0;
	      if (getConfig().getString("Magnata_atual") == null)
	      {
		    p.sendMessage("");
	        p.sendMessage(getConfig().getString("msg-null-comando-magnata").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")));
		    p.sendMessage("");
	        return true;
	      }
	      p.sendMessage("");
	      p.sendMessage("§2"+ getConfig().getString("Prefixo-Do-Plugin") + " " + getConfig().getString("msg-comando-magnata").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")));
	      p.sendMessage("");

	        return true;
	    }
		return false;
}
	 
	@EventHandler
	public void Magnata_logando(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(p.getName().equalsIgnoreCase(getConfig().getString("Magnata_atual"))){
		Collection<? extends Player> list = Bukkit.getOnlinePlayers();
		for (Player p2 : list) {
			TTA_Methods.sendTitle(//API
					p2, getConfig().getString("title-magnata-logou")
					.replaceAll("@magnata", getConfig().getString("Magnata_atual"))
					.replaceAll("@exmagnata", getConfig().getString("Magnata-antigo"))
					, 5, 40, 5 //Tempo do title.
					, getConfig().getString("subtitle-magnata-logou")
					.replaceAll("@magnata", getConfig().getString("Magnata_atual"))
					.replaceAll("@exmagnata", getConfig().getString("Magnata-antigo"))
					, 5, 40, 5); //Tempo do subtitle.
		}
		Bukkit.getServer().broadcastMessage("");
		Bukkit.getServer().broadcastMessage("§2"+ getConfig().getString("Prefixo-Do-Plugin") + " " + getConfig().getString("msg-magnata-logou").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")));
		Bukkit.getServer().broadcastMessage("");
     		
		}
	}
	@EventHandler
	public void Magnata_deslogando(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(p.getName().equalsIgnoreCase(getConfig().getString("Magnata_atual"))){
		Collection<? extends Player> list = Bukkit.getOnlinePlayers();
		for (Player p2 : list) {
			TTA_Methods.sendTitle(// API
					p2, getConfig().getString("title-magnata-deslogou")
					.replaceAll("@magnata", getConfig().getString("Magnata_atual"))
					.replaceAll("@exmagnata", getConfig().getString("Magnata-antigo"))
					, 5, 40, 5, //Tempo do title.
					getConfig().getString("subtitle-magnata-deslogou")
					.replaceAll("@magnata", getConfig().getString("Magnata_atual"))
					.replaceAll("@exmagnata", getConfig().getString("Magnata-antigo"))
					, 5, 40, 5); //Tempo do subtitle.
		}
		Bukkit.getServer().broadcastMessage("");
		Bukkit.getServer().broadcastMessage("§2"+ getConfig().getString("Prefixo-Do-Plugin") + " " + getConfig().getString("msg-magnata-deslogou").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")));
		Bukkit.getServer().broadcastMessage("");
		}
	}
}
 