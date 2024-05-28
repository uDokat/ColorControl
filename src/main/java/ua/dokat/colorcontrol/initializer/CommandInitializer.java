package ua.dokat.colorcontrol.initializer;

import ua.dokat.colorcontrol.commands.adm.RealmInfo;
import ua.dokat.colorcontrol.commands.game.Lobby;
import ua.dokat.colorcontrol.commands.game.StartGame;
import ua.dokat.colorcontrol.commands.game.StopGame;
import ua.dokat.colorcontrol.commands.realmoperation.CloseRealm;
import ua.dokat.colorcontrol.commands.TestCommand;
import ua.dokat.colorcontrol.commands.game.JoinToRealm;
import ua.dokat.colorcontrol.commands.realmoperation.RestartRealm;
import ua.dokat.colorcontrol.commands.test.GetCube;

public class CommandInitializer {
    public CommandInitializer(){
        new RealmInfo("realmInfo");
        new RestartRealm("restart");
        new Lobby("lobby");
        new CloseRealm("close");
        new JoinToRealm("join");

        // Deprecated
        new StartGame("start");
        new StopGame("stop");

        new TestCommand();
        new GetCube();
    }
}
