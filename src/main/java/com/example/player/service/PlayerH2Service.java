package com.example.player.service;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import com.example.player.repository.PlayerRepository;
import com.example.player.model.Player;
import java.util.*;
import com.example.player.model.PlayerRowMapper;
@Service
public class PlayerH2Service implements PlayerRepository{
    @Autowired
    private JdbcTemplate db;
@Override
public ArrayList<Player>getPlayers(){
List<Player>playerList=db.query("select * from player",new PlayerRowMapper());
return new ArrayList<>(playerList);

}
@Override
public Player getPlayerById(int playerId){
    try{
Player player=db.queryForObject("select * from player where id = ?", new PlayerRowMapper(),playerId);
          return player;
       }catch (Exception e){
throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
}
@Override
public Player addPlayer(Player player){
    db.update("INSERT INTO player(playerName,jerseyNumber,role)values(?,?,?)",player.getplayerName(),player.getjerseyNumber(),player.getrole());
   
    return getPlayerById(player.getplayerId());
}
@Override
public Player updatedPlayer(int playerId,Player player){
    if(player.getplayerName()!=null){
        db.update("UPDATE player SET playerName=? WHERE id=?",player.getplayerName(),playerId);
    }
    if(player.getjerseyNumber()!=0){
        db.update("UPDATE player SET jerseyNumber=? WHERE id=?",player.getjerseyNumber(),playerId);
    }
    if(player.getrole()!=null){
        db.update("UPDATE player SET role=? WHERE id=?",player.getrole(),playerId);
    }
    return getPlayerById(playerId);
    }
@Override
public void deletePlayer(int playerId){
    db.update("DELETE FROM player WHERE id=?",playerId);
}
}
