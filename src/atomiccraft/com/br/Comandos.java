package atomiccraft.com.br;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Comandos implements Listener, CommandExecutor{
	public String magnataAtual;
	public String magnataAntigo;
	public String titleMagnataLogou;
	public String subtitleMagnataLogou;
	public String msgMagnataLogou;
	public String titleMagnataDeslogou;
	public String subtitleMagnataDeslogou;
	public String msgMagnataDeslogou;
	public String msgComandoMagnata;
  
	public Comandos
	
	(
			Main main,
			String magnataAtual,
			String magnataAntigo,
			String msgComandoMagnata
	)
	
	{
	  }

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("magnata")){
			Player p = (Player)sender;
			p.sendMessage(msgComandoMagnata.replaceAll("@magnata", magnataAtual).replaceAll("@exmagnata", magnataAntigo));
		}
		return true;
	}

}
