package com.example.gamecollection.controller;

import com.example.gamecollection.model.Game;
import com.example.gamecollection.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {
    private final GameService gameService;
    public GameController(GameService gameService) { this.gameService = gameService; }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String search, @RequestParam(defaultValue = "all") String collectionType, Model model) {
        model.addAttribute("games", gameService.searchGames(search, collectionType));
        model.addAttribute("search", search);
        model.addAttribute("collectionType", collectionType);
        model.addAttribute("totalGames", gameService.getTotalGames());
        model.addAttribute("physicalGames", gameService.getPhysicalGames());
        model.addAttribute("digitalGames", gameService.getDigitalGames());
        return "index";
    }

    @PostMapping("/add")
    public String addGame(@ModelAttribute Game game) { gameService.addGame(game); return "redirect:/"; }

    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) { gameService.deleteGame(id); return "redirect:/"; }
}
