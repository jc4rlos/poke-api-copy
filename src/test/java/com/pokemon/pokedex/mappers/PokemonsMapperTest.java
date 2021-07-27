package com.pokemon.pokedex.mappers;

import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PokemonsMapperTest {

  private PokemonsMapper pokemonsMapper = Mappers.getMapper(PokemonsMapper.class);

  @Test
  void pokemonDtoToPokemon(){
    PokemonDto pokemonDto = PokemonDto.builder().id(1L).build();
    Pokemon pokemon = pokemonsMapper.pokemonDtoToPokemon(pokemonDto);
    assertThat(pokemonDto.getId()).isEqualTo(pokemon.getId());
  }

  @Test
  void pokemonDtoToPokemon_when_pokemonSaveDto_is_null() {
    Assertions.assertThat(pokemonsMapper.pokemonDtoToPokemon(null)).isNull();
  }

  @Test
  void pokemonToPokemonDto(){
    Pokemon pokemon = Pokemon.builder().id(1L).build();
    PokemonDto pokemonDto = pokemonsMapper.pokemonToPokemonDto(pokemon);
    assertThat(pokemon.getId()).isEqualTo(pokemonDto.getId());
  }

  @Test
  void pokemonToPokemonDto_when_pokemonSaveDto_is_null() {
    Assertions.assertThat(pokemonsMapper.pokemonToPokemonDto(null)).isNull();
  }


}
