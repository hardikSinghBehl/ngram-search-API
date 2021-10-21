package com.behl.dragonera.dto;

import java.util.List;

import com.behl.dragonera.entity.Character;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CharacterResponseWrapperDto {

    private final Integer page;
    private final Integer count;
    private final List<Character> result;

}
