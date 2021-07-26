package com.pokemon.pokedex.mappers;

import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.dto.PokemonSaveDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonMapperTest {

  @Test
  void pokemonSaveDtoToPokemon() {
    final Pokemon pokemon = PokemonMapper.pokemonSaveDtoToPokemon(getPokemonSaveDto());
    Assertions.assertThat(pokemon).isNotNull();
  }

  @Test
  void pokemonSaveDtoToPokemon_when_pokemonSaveDto_is_null() {
    Assertions.assertThat(PokemonMapper.pokemonSaveDtoToPokemon(null)).isNull();
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
  void pokemonToPokemonGetDto_when_pokemonGetDto_is_null() {
    Assertions.assertThat(PokemonMapper.pokemonToPokemonGetDto(null)).isNull();
  }

  private PokemonSaveDto getPokemonSaveDto() {
    return PokemonSaveDto.builder()
            .name("pokemon").build();
  }

  private PokemonPatchFavoriteDto getPokemonPatchFavoriteDto() {
    return PokemonPatchFavoriteDto.builder().favorite(true).id(1L).build();
  }

  private PokemonPatchNameDto getPokemonPatchNameDto() {
    return PokemonPatchNameDto.builder().name("pokemon").build();
  }

}