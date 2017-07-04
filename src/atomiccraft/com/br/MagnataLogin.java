package atomiccraft.com.br;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.Herbystar.TTA.TTA_Methods;

public class MagnataLogin implements Listener{
	private String magnataAtual;
	private String magnataAntigo;
	private String titleMagnataLogou;
	private String subtitleMagnataLogou;
	private String msgMagnataLogou;
	private String titleMagnataDeslogou;
	private String subtitleMagnataDeslogou;
	private String msgMagnataDeslogou;
  
	public MagnataLogin
	
	(
			Main main,
			String magnataAtual,
			String magnataAntigo, 
			String titleMagnataLogou,
			String subtitleMagnataLogou,
			String msgMagnataLogou,
			String titleMagnataDeslogou,
			String subtitleMagnataDeslogou,
			String msgMagnataDeslogou
	)
	
	{
	  }
	
	@EventHandler
	public void Magnata_logando(PlayerJoinEvent e){
		boolean logoueomagnata = true;
		Player p = e.getPlayer();
		if(p.getName() == magnataAtual){
			logoueomagnata = false;
     if(logoueomagnata == false){
		Collection<? extends Player> list = Bukkit.getOnlinePlayers();
		for (Player p2 : list) {
			TTA_Methods.sendTitle(p2, titleMagnataLogou.replaceAll("@magnata", magnataAtual).replaceAll("@exmagnata", magnataAntigo), 5, 100, 5, subtitleMagnataLogou.replaceAll("@magnata", magnataAtual).replaceAll("@exmagnata", magnataAntigo), 5, 100, 5);
		}
		Bukkit.getServer().broadcastMessage("");
		Bukkit.getServer().broadcastMessage(msgMagnataLogou.replaceAll("@magnata", magnataAtual).replaceAll("@exmagnata", magnataAntigo));
		Bukkit.getServer().broadcastMessage("");
     		}
		}
	}
	@EventHandler
	public void Magnata_deslogando(PlayerJoinEvent e){
		boolean logoueomagnata = false;
		Player p = e.getPlayer();
		if(p.getName() != magnataAtual){
			logoueomagnata = true;
     if(logoueomagnata == true){
		Collection<? extends Player> list = Bukkit.getOnlinePlayers();
		for (Player p2 : list) {
			TTA_Methods.sendTitle(p2, titleMagnataDeslogou.replaceAll("@magnata", magnataAtual).replaceAll("@exmagnata", magnataAntigo), 5, 100, 5, subtitleMagnataDeslogou.replaceAll("@magnata", magnataAtual).replaceAll("@exmagnata", magnataAntigo), 5, 100, 5);
		}
		Bukkit.getServer().broadcastMessage("");
		Bukkit.getServer().broadcastMessage(msgMagnataDeslogou.replaceAll("@magnata", magnataAtual).replaceAll("@exmagnata", magnataAntigo));
		Bukkit.getServer().broadcastMessage("");
     		}
		}
	}
}
