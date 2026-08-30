package com.example.gamecollection.service;

import com.example.gamecollection.model.Game;
import com.example.gamecollection.repository.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService implements CommandLineRunner {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) { this.gameRepository = gameRepository; }

    @Override
    public void run(String... args) {
    }

    public List<Game> searchGames(String search) {
        if (search == null || search.trim().isEmpty()) return gameRepository.findAll();
        String query = search.trim();
        return gameRepository.findByTitleContainingIgnoreCaseOrPlatformContainingIgnoreCaseOrGenreContainingIgnoreCase(query, query, query);
    }
    public void addGame(Game game) { gameRepository.save(game); }
    public void deleteGame(Long id) { gameRepository.deleteById(id); }
    public long getTotalGames() { return gameRepository.count(); }
    public long getPhysicalGames() { return gameRepository.countByPhysicalTrue(); }
    public long getDigitalGames() { return gameRepository.countByPhysicalFalse(); }
}
