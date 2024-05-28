package ua.dokat.colorcontrol.initializer;

import ua.dokat.colorcontrol.listeners.*;

public class EventListenerInitializer {
    public EventListenerInitializer(){
        new JoinPlayerListener();
        new BlockBreakListener();
        new GuiClickListener();
        new ClickItemListener();
//        new PlayerTeleportListener();
        new DropItemInLobbyListener();
        new DeathListener();
        new PlayerMoveListener();
//        new PlayerDamageListener();

        new TeamSelectionListener();
    }
}
