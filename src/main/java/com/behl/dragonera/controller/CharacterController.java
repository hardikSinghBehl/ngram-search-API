package com.behl.dragonera.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.behl.dragonera.dto.SearchResponseDto;
import com.behl.dragonera.service.CharacterService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<SearchResponseDto> characterSearchRetreivalHandler(
            @RequestParam(name = "keyword", required = false) final String keyword,
            @RequestParam(name = "count", required = false) Integer count,
            @RequestParam(name = "page", required = false) final Integer pageNumber) {
        final var response = characterService.findAllMatching(keyword, pageNumber, count);
        return ResponseEntity.ok(SearchResponseDto.builder().count(response.size())
                .page(response.size() == 0 ? null : pageNumber == null ? 1 : pageNumber).result(response).build());
    }

}
