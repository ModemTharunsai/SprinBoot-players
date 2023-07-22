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
List<Player>playerList=db.query("Select * from team",new PlayerRowMapper());
 ArrayList<Player>players=new ArrayList<>(playerList);
return players;

}
@Override
public Player getPlayerById(int playerId){
    try{
Player player=db.queryForObject("select * from team where id = ?", new PlayerRowMapper(),playerId);
          return player;
       }catch (Exception e){
throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
}
@Override
public Player addPlayer(Player player){
    db.update("insert into team(playerName,jerseyNumber,role)values(?,?,?)",player.getplayerName(),player.getjerseyNumber(),player.getrole());
   Player savedplayer=db.queryForObject("select * from team where playerName=? and jerseyNumber=? and role=?",new PlayerRowMapper(),player.getplayerName(),player.getjerseyNumber(),player.getrole());
    return savedplayer;
}
@Override
public Player updatedPlayer(int playerId,Player player){
    if(player.getplayerName()!=null){
        db.update("UPDATE team SET playerName=? WHERE id=?",player.getplayerName(),playerId);
    }
    if(player.getjerseyNumber()!=0){
        db.update("UPDATE team SET jerseyNumber=? WHERE id=?",player.getjerseyNumber(),playerId);
    }
    if(player.getrole()!=null){
        db.update("UPDATE team SET role=? WHERE id=?",player.getrole(),playerId);
    }
    return getPlayerById(playerId);
    }
@Override
public void deletePlayer(int playerId){
    db.update("DELETE FROM team WHERE id=?",playerId);
}
}
