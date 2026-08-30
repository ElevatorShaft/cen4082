package com.example.gamecollection.repository;

import com.example.gamecollection.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByTitleContainingIgnoreCaseOrPlatformContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String platform, String genre);
    long countByPhysicalTrue();
    long countByPhysicalFalse();
}
