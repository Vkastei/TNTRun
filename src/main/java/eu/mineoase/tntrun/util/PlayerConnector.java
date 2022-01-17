package eu.mineoase.tntrun.util;

import eu.mineoase.tntrun.TNTRun;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerConnector {

    public static void connect(Player player, String servername) {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(servername);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        player.sendPluginMessage(TNTRun.getInstance(), "BungeeCord", b.toByteArray());
    }
}
