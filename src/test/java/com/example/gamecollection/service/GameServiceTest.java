package com.example.gamecollection.service;

import com.example.gamecollection.model.Game;
import com.example.gamecollection.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    private GameRepository gameRepository;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameRepository = Mockito.mock(GameRepository.class);
        gameService = new GameService(gameRepository);
    }

    @Test
    void searchGamesShouldFindMatchingTitle() {
        Game persona = new Game("Persona 5 Royal", "PS5", "RPG", true);
        Game cyberpunk = new Game("Cyberpunk 2077", "PS5", "RPG", false);

        when(gameRepository
                .findByTitleContainingIgnoreCaseOrPlatformContainingIgnoreCaseOrGenreContainingIgnoreCase(
                        "Persona", "Persona", "Persona"))
                .thenReturn(List.of(persona));

        List<Game> results = gameService.searchGames("Persona");

        assertEquals(1, results.size());
        assertEquals("Persona 5 Royal", results.get(0).getTitle());
    }

    @Test
    void emptySearchShouldReturnAllGames() {
        Game persona = new Game("Persona 5 Royal", "PS5", "RPG", true);
        Game cyberpunk = new Game("Cyberpunk 2077", "PS5", "RPG", false);

        when(gameRepository.findAll())
                .thenReturn(List.of(persona, cyberpunk));

        List<Game> results = gameService.searchGames("");

        assertEquals(2, results.size());
    }

    @Test
    void addGameShouldSaveGameToRepository() {
        Game game = new Game("Elden Ring", "PS5", "RPG", true);

        gameService.addGame(game);

        verify(gameRepository).save(game);
    }

    @Test
    void deleteGameShouldDeleteGameFromRepository() {
        gameService.deleteGame(5L);

        verify(gameRepository).deleteById(5L);
    }

    @Test
    void statisticsShouldCountPhysicalAndDigitalGames() {
		when(gameRepository.count()).thenReturn(3L);
        when(gameRepository.countByPhysicalTrue()).thenReturn(2L);
        when(gameRepository.countByPhysicalFalse()).thenReturn(1L);
		
		assertEquals(3, gameService.getTotalGames());
        assertEquals(2, gameService.getPhysicalGames());
        assertEquals(1, gameService.getDigitalGames());
    }
}