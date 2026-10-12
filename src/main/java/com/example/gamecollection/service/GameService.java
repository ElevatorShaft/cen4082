package com.example.gamecollection.service;

import com.example.gamecollection.model.Game;
import com.example.gamecollection.repository.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;

@Service
public class GameService implements CommandLineRunner {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) { this.gameRepository = gameRepository; }

    @Override
    public void run(String... args) {
    }
	
	public List<Game> searchGames(String search) {
		return searchGames(search, "all");
	}

    public List<Game> searchGames(String search, String collectionType) {
		List<Game> matchingGames;
        if (search == null || search.trim().isEmpty()) 
			matchingGames = gameRepository.findAll();
		else {
			String query = search.trim();
			matchingGames = gameRepository.findByTitleContainingIgnoreCaseOrPlatformContainingIgnoreCaseOrGenreContainingIgnoreCase(query, query, query);
		}

        String filter = collectionType == null ? "all" : collectionType.trim().toLowerCase(Locale.ROOT);

        return switch (filter) {
            case "physical" ->
                matchingGames.stream()
                        .filter(game -> !game.isPhysical())
                        .toList();
            case "digital" ->
                matchingGames.stream()
                        .filter(game -> !game.isPhysical())
                        .toList();
            default -> matchingGames;
        };
    }
    public void addGame(Game game) { gameRepository.save(game); }
    public void deleteGame(Long id) { gameRepository.deleteById(id); }
    public long getTotalGames() { return gameRepository.count(); }
    public long getPhysicalGames() { return gameRepository.countByPhysicalTrue(); }
    public long getDigitalGames() { return gameRepository.countByPhysicalFalse(); }
}
