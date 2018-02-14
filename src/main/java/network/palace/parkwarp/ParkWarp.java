package network.palace.parkwarp;

import lombok.Getter;
import network.palace.core.plugin.Plugin;
import network.palace.core.plugin.PluginInfo;
import network.palace.parkwarp.commands.*;
import network.palace.parkwarp.dashboard.PacketListener;
import network.palace.parkwarp.utils.WarpUtil;
import org.bukkit.Bukkit;

@PluginInfo(name = "ParkWarp", version = "1.0.5", depend = "Core", canReload = true)
public class ParkWarp extends Plugin {
    @Getter private static ParkWarp instance;
    @Getter private WarpUtil warpUtil;

    @Override
    protected void onPluginEnable() throws Exception {
        instance = this;
        warpUtil = new WarpUtil();
        Bukkit.getPluginManager().registerEvents(new PacketListener(), this);
        warpUtil.refreshWarps();
        getLogger().info("Warps loaded!");
        registerCommand(new DelWarpCommand());
        registerCommand(new SetWarpCommand());
        registerCommand(new UpdateWarpCommand());
        registerCommand(new WarpCommand());
        registerCommand(new WRLCommand());
    }

    @Override
    public void onPluginDisable() {
        warpUtil.clearWarps();
    }
}
