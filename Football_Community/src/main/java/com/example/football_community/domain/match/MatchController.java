package com.example.football_community.domain.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/football-community/match")
@RestController
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/create")
    public ResponseEntity<Match> create(
            @RequestBody Match match
    ) {
        Match createdMatch = matchService.createMatch(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
    }

    @PutMapping("/edit/{matchId}")
    public ResponseEntity<MatchDTO> updateMatch(
            @PathVariable Long matchId,
            @RequestBody MatchDTO matchDTO
    ) {
        MatchDTO updatedMatch = matchService.updateMatch(matchId, matchDTO);
        return ResponseEntity.ok(updatedMatch);
    }

    @DeleteMapping("/delete/{matchId}")
    public ResponseEntity<String> deleteMatch(
            @PathVariable Long matchId
    ) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok("매치가 취소되었습니다.");
    }
}
