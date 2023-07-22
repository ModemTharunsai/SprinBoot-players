package com.example.player.repository;
import com.example.player.model.Player;

import java.util.ArrayList;

public interface PlayerRepository {
    ArrayList<Player>getPlayers();

    Player getPlayerById(int playerId);
    Player addPlayer(Player player);
    Player updatedPlayer(int PlayerId,Player player);
    void deletePlayer(int PlayerId);

}
