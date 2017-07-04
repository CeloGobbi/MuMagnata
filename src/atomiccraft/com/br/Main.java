package atomiccraft.com.br;

import java.io.File;
import java.util.Collection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.Herbystar.TTA.TTA_Methods;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener{
	public String magnataAtual;
	public String magnataAntigo;
	public String titleMagnataLogou;
	public String subtitleMagnataLogou;
	public String msgMagnataLogou;
	public String titleMagnataDeslogou;
	public String subtitleMagnataDeslogou;
	public String msgMagnataDeslogou;
	public String msgComandoMagnata;
	public static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    

	@Override
    public void onEnable(){
	    Bukkit.getServer().getPluginManager().registerEvents(new Comandos(this, magnataAtual, magnataAntigo, msgComandoMagnata), this);
	    Bukkit.getServer().getPluginManager().registerEvents(new MagnataLogin(this, magnataAntigo, magnataAntigo, magnataAntigo, magnataAntigo, magnataAntigo, magnataAntigo, magnataAntigo, magnataAntigo), this);
	    getCommand("magnata").setExecutor(new Comandos(this, magnataAntigo, magnataAntigo, magnataAntigo));
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

	getServer().getConsoleSender().sendMessage("§3Plugin: §6MuMagnata §2- §3Desenvolvedor: §6Mushu949 §2 §3Prefixo: ["+ getConfig().getString("Prefixo-Do-Plugin") +"]");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Plugin habilitado!");
	getServer().getConsoleSender().sendMessage("§3" + getConfig().getString("Prefixo-Do-Plugin")+ " " +"§6Magnata atual: §2" + getConfig().getString("Magnata_atual"));			            
	String MagnataAtual = getConfig().getString("Magnata_atual", "Mushu949");
	String MagnataAntigo = getConfig().getString("Magnata-antigo", "Mushu");
	String titleMagnataLogou = getConfig().getString("title-magnata-logou", "§2Magnata");
	String subtitleMagnataLogou = getConfig().getString("subtitle-magnata-logou", "§2O magnata §f@magnata §2logou no servidor!");
	String msgMagnataLogou = getConfig().getString("msg-magnata-logou", "§2O magnata §f@magnata §2logou no servidor!");
	String titleMagnataDeslogou = getConfig().getString("title-magnata-deslogou", "§2Magnata");
	String subtitleMagnataDeslogou = getConfig().getString("subtitle-magnata-deslogou", "§2O magnata §f@magnata §2deslogou o servidor");
	String msgMagnataDeslogou = getConfig().getString("title-magnata-deslogou", "§2O magnata §f@magnata §2deslogou o servidor");
	String msgComandoMagnata = getConfig().getString("msg-comando-magnata", "§2O magnata atual é: §f@exmagnata");
    new MagnataLogin
    (
    		this,
    		MagnataAtual,
    		MagnataAntigo,
    		titleMagnataLogou,
    		subtitleMagnataLogou,
    		msgMagnataLogou,
    		titleMagnataDeslogou,
    		subtitleMagnataDeslogou,
    		msgMagnataDeslogou
    	);
    new Comandos
    (
    		this,
    		MagnataAtual,
    		MagnataAntigo,
    		msgComandoMagnata
    	);
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
					TTA_Methods.sendTitle(p, getConfig().getString("title-novo-magnata").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")), 5, 100, 5, getConfig().getString("subtitle-novo-magnata").replaceAll("@magnata", getConfig().getString("Magnata_atual")).replaceAll("@exmagnata", getConfig().getString("Magnata-antigo")), 5, 100, 5);
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
        }.runTaskLater(this, 10*20);
	}
}
 