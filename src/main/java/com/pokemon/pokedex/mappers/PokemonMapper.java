package com.pokemon.pokedex.mappers;

import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <b>Class</b>:PokemonMapper <br/>.
 *
 * @author Carlos
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PokemonMapper {


  /**
   * pokemonSaveDtoToPokemon .
   * @param pokemonSaveDto .
   * @return Pokemon
   */
  public static Pokemon pokemonDtoToPokemon(final PokemonDto pokemonSaveDto) {
    if (pokemonSaveDto == null) {
      return null;
    }
    return Pokemon.builder()
            .id(pokemonSaveDto.getId())
            .pokedexId(pokemonSaveDto.getPokedexId())
            .order(pokemonSaveDto.getOrder())
            .name(pokemonSaveDto.getName())
            .cp(pokemonSaveDto.getCp())
            .hp(pokemonSaveDto.getHp())
            .weight(pokemonSaveDto.getWeight())
            .height(pokemonSaveDto.getHeight())
            .type(pokemonSaveDto.getType())
            .gender(pokemonSaveDto.getGender())
            .candy(pokemonSaveDto.getCandy())
            .starDust(pokemonSaveDto.getStarDust())
            .image(pokemonSaveDto.getImage())
            .evolution(pokemonSaveDto.getEvolution())
            .shiny(pokemonSaveDto.isShiny())
            .favorite(pokemonSaveDto.isFavorite()).build();
  }

  /**
   * pokemonPatchNameDtoToPokemon .
   * @param pokemonPatchNameDto .
   * @return Pokemon
   */
  public static Pokemon pokemonPatchNameDtoToPokemon(final PokemonPatchNameDto pokemonPatchNameDto) {
    if (pokemonPatchNameDto == null) {
      return null;
    }
    return Pokemon.builder()
            .id(pokemonPatchNameDto.getId())
            .name(pokemonPatchNameDto.getName()).build();
  }

  /**
   * pokemonPatchFavoriteDtoToPokemon .
   * @param pokemonPatchFavoriteDto .
   * @return Pokemon
   */
  public static Pokemon pokemonPatchFavoriteDtoToPokemon(final PokemonPatchFavoriteDto pokemonPatchFavoriteDto) {
    if (pokemonPatchFavoriteDto == null) {
      return null;
    }
    return Pokemon.builder()
            .id(pokemonPatchFavoriteDto.getId())
            .favorite(pokemonPatchFavoriteDto.isFavorite()).build();
  }

  /**
   * pokemonToPokemonGetDto .
   * @param pokemon .
   * @return PokemonGetDto
   */
  public static PokemonDto pokemonToPokemonDto(final Pokemon pokemon) {
    if (pokemon == null) {
      return null;
    }
    return PokemonDto.builder()
            .id(pokemon.getId())
            .pokedexId(pokemon.getPokedexId())
            .order(pokemon.getOrder())
            .name(pokemon.getName())
            .cp(pokemon.getCp())
            .hp(pokemon.getHp())
            .weight(pokemon.getWeight())
            .height(pokemon.getHeight())
            .type(pokemon.getType())
            .gender(pokemon.getGender())
            .candy(pokemon.getCandy())
            .starDust(pokemon.getStarDust())
            .image(pokemon.getImage())
            .evolution(pokemon.getEvolution())
            .shiny(pokemon.isShiny())
            .favorite(pokemon.isFavorite()).build();
  }
}
