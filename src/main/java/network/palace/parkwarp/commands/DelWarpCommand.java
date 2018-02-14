package network.palace.parkwarp.commands;

import network.palace.core.command.CommandException;
import network.palace.core.command.CommandMeta;
import network.palace.core.command.CommandPermission;
import network.palace.core.command.CoreCommand;
import network.palace.core.player.Rank;
import network.palace.parkwarp.ParkWarp;
import network.palace.parkwarp.handlers.Warp;
import network.palace.parkwarp.utils.WarpUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(description = "Delete a warp")
@CommandPermission(rank = Rank.MOD)
public class DelWarpCommand extends CoreCommand {

    public DelWarpCommand() {
        super("delwarp");
    }

    @Override
    protected void handleCommandUnspecific(CommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            WarpUtil wu = ParkWarp.getInstance().getWarpUtil();
            final String w = args[0];
            final Warp warp = wu.findWarp(w);
            if (warp == null) {
                sender.sendMessage(ChatColor.RED + "Warp not found!");
                return;
            }
            Bukkit.getScheduler().runTaskAsynchronously(ParkWarp.getInstance(), () -> {
                wu.removeWarp(warp);
                wu.removeWarp(warp);
                wu.updateWarps();
                sender.sendMessage(ChatColor.GRAY + "Warp " + w + " has been removed.");
            });
            return;
        }
        sender.sendMessage(ChatColor.RED + "/delwarp [Warp Name]");
    }
}
