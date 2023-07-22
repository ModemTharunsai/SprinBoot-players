package com.example.player.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
 import org.springframework.web.server.ResponseStatusException;
 import org.springframework.http.HttpStatus;
import java.util.*;
import com.example.player.model.Player;

import com.example.player.service.PlayerH2Service;


@RestController
class PlayerController {
    @Autowired
    public PlayerH2Service playerService;


    @GetMapping("/players")
    public ArrayList<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @GetMapping("/players/{playerId}")
    public Player getPlayerById(@PathVariable int playerId) {
        Player player = playerService.getPlayerById(playerId);
        if (player == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return player;
    }
     @PostMapping("/players")
     public Player addPlayer(@RequestBody Player player){
return playerService.addPlayer(player);
     }   

     @PutMapping("/players/{playerId}")
    public Player updatedPlayer(@PathVariable int playerId, @RequestBody Player player) {
        Player players = playerService.getPlayerById(playerId);
        if (players == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return playerService.updatedPlayer(playerId, player);
    }
     @DeleteMapping("/players/{playerId}")
     public void deleteBook(@PathVariable("playerId")int playerId){
        playerService.deletePlayer(playerId);
     }
}
