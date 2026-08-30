package com.example.gamecollection.service;

import com.example.gamecollection.model.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final List<Game> games = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public GameService() {
        games.add(new Game(
                idCounter.getAndIncrement(),
                "Persona 5 Royal",
                "PS5",
                "RPG",
                true
        ));

        games.add(new Game(
                idCounter.getAndIncrement(),
                "The Legend of Zelda: Tears of the Kingdom",
                "Nintendo Switch",
                "Adventure",
                true
        ));

        games.add(new Game(
                idCounter.getAndIncrement(),
                "Cyberpunk 2077",
                "PS5",
                "RPG",
                false
        ));
    }

    public List<Game> getAllGames() {
        return games;
    }

    public void addGame(Game game) {
        game.setId(idCounter.getAndIncrement());
        games.add(game);
    }

    public void deleteGame(Long id) {
        games.removeIf(game -> game.getId().equals(id));
    }

    public List<Game> searchGames(String search) {
        if (search == null || search.trim().isEmpty()) {
            return games;
        }

        String query = search.toLowerCase();

        return games.stream()
                .filter(game ->
                        game.getTitle().toLowerCase().contains(query)
                        || game.getPlatform().toLowerCase().contains(query)
                        || game.getGenre().toLowerCase().contains(query)
                )
                .collect(Collectors.toList());
    }

    public int getTotalGames() {
        return games.size();
    }

    public long getPhysicalGames() {
        return games.stream()
                .filter(Game::isPhysical)
                .count();
    }

    public long getDigitalGames() {
        return games.stream()
                .filter(game -> !game.isPhysical())
                .count();
    }
}
