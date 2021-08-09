package com.pokemon.pokedex.mappers;

import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonMoveDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PokemonMapperTest {

  @Test
  void pokemonSaveDtoToPokemon() {
    final Pokemon pokemon = PokemonMapper.pokemonDtoToPokemon(getPokemonDto());
    Assertions.assertThat(pokemon).isNotNull();
  }

  @Test
  void pokemonSaveDtoToPokemon_when_pokemonSaveDto_is_null() {
    Assertions.assertThat(PokemonMapper.pokemonDtoToPokemon(null)).isNull();
  }

  @Test
  void pokemonPatchNameDtoToPokemon() {
    final Pokemon pokemon = PokemonMapper.pokemonPatchNameDtoToPokemon(getPokemonPatchNameDto());
    Assertions.assertThat(pokemon).isNotNull();
  }

  @Test
  void pokemonPatchNameDtoToPokemon_when_pokemonPatchNameDtoToPokemon_is_null() {
    Assertions.assertThat(PokemonMapper.pokemonPatchNameDtoToPokemon(null)).isNull();
  }

  @Test
  void pokemonPatchFavoriteDtoToPokemon() {
    final Pokemon pokemon = PokemonMapper.pokemonPatchFavoriteDtoToPokemon(getPokemonPatchFavoriteDto());
    Assertions.assertThat(pokemon).isNotNull();
  }

  @Test
  void pokemonPatchFavoriteDtoToPokemon_when_pokemonPatchFavoriteDtoToPokemon_is_null() {
    Assertions.assertThat(PokemonMapper.pokemonPatchFavoriteDtoToPokemon(null)).isNull();
  }

  @Test
  void pokemonToPokemonDto_when_pokemonGetDto_is_null() {
    Assertions.assertThat(PokemonMapper.pokemonToPokemonDto(null)).isNull();
  }

  @Test
  void pokemonToPokemonDto_when_moves_is_null() {
    final Pokemon pokemon = PokemonMapper.pokemonDtoToPokemon(getPokemonDto());
    pokemon.setMoves(null);
    Assertions.assertThat(PokemonMapper.pokemonToPokemonDto(pokemon)).isNotNull();
  }

  @Test
  void pokemonDtoMoveToPokemonMove_when_pokemonDtoMove_is_null(){
    Assertions.assertThat(PokemonMapper.pokemonDtoMoveToPokemonMove(null)).isNull();
  }

  @Test
  void pokemonMoveToPokemonMoveDto_when_pokemonMove_is_null(){
    Assertions.assertThat(PokemonMapper.pokemonMoveToPokemonMoveDto(null)).isNull();
  }


  private PokemonDto getPokemonDto() {
    final List<PokemonMoveDto> moves = new ArrayList<>();
    moves.add(getPokemonMoveDto());
    return PokemonDto.builder()
            .name("pokemon")
            .moves(moves)
            .build();
  }

  private PokemonPatchFavoriteDto getPokemonPatchFavoriteDto() {
    return PokemonPatchFavoriteDto.builder().favorite(true).id(1L).build();
  }

  private PokemonPatchNameDto getPokemonPatchNameDto() {
    return PokemonPatchNameDto.builder().name("pokemon").build();
  }

  private PokemonMoveDto getPokemonMoveDto(){
    return PokemonMoveDto.builder().id(1L).build();
  }
}