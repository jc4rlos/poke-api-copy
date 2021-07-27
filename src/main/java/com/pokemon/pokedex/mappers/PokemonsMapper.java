package com.pokemon.pokedex.mappers;

import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * <b>Class</b>:PokemonMapper <br/>.
 *
 * @author Carlos
 */
@Mapper(componentModel = "spring")
public interface PokemonsMapper {

  @Mapping(ignore = true, target = "createdAt")
  @Mapping(ignore = true, target = "updatedAt")
  Pokemon pokemonDtoToPokemon(PokemonDto pokemonDto);

  PokemonDto pokemonToPokemonDto(Pokemon pokemon);

  Pokemon pokemonPatchNameDtoToPokemon(PokemonPatchNameDto pokemonPatchNameDto);

  Pokemon pokemonPatchFavoriteDtoToPokemon(PokemonPatchFavoriteDto pokemonPatchFavoriteDto);
}
