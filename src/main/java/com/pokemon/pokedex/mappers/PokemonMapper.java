package com.pokemon.pokedex.mappers;

import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonMoveDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import com.pokemon.pokedex.model.entity.PokemonMove;

import java.util.ArrayList;
import java.util.List;

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
   *
   * @param pokemonDto .
   * @return Pokemon
   */
  public static Pokemon pokemonDtoToPokemon(final PokemonDto pokemonDto) {
    if (pokemonDto == null) {
      return null;
    }
    List<PokemonMove> moves = new ArrayList<>();
    final Pokemon pokemon = Pokemon.builder()
            .id(pokemonDto.getId())
            .pokedexId(pokemonDto.getPokedexId())
            .order(pokemonDto.getOrder())
            .name(pokemonDto.getName())
            .cp(pokemonDto.getCp())
            .hp(pokemonDto.getHp())
            .weight(pokemonDto.getWeight())
            .height(pokemonDto.getHeight())
            .type(pokemonDto.getType())
            .gender(pokemonDto.getGender())
            .candy(pokemonDto.getCandy())
            .starDust(pokemonDto.getStarDust())
            .image(pokemonDto.getImage())
            .evolution(pokemonDto.getEvolution())
            .shiny(pokemonDto.isShiny())
            .moves(moves)
            .requiredCandies(pokemonDto.getRequiredCandies())
            .favorite(pokemonDto.isFavorite()).build();

    for (PokemonMoveDto pokemonMoveDto : pokemonDto.getMoves()) {
      final PokemonMove pokemonMove = pokemonDtoMoveToPokemonMove(pokemonMoveDto);
      pokemonMove.setPokemon(pokemon);
      moves.add(pokemonMove);
    }
    return pokemon;
  }

  /**
   * pokemonPatchNameDtoToPokemon .
   *
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
   *
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
   *
   * @param pokemon .
   * @return PokemonGetDto
   */
  public static PokemonDto pokemonToPokemonDto(final Pokemon pokemon) {
    if (pokemon == null) {
      return null;
    }
    List<PokemonMoveDto> moves = new ArrayList<>();
    if (pokemon.getMoves() != null) {
      for (PokemonMove pokemonMove : pokemon.getMoves()) {
        final PokemonMoveDto pokemonMoveDto = pokemonMoveToPokemonMoveDto(pokemonMove);
        moves.add(pokemonMoveDto);
      }
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
            .requiredCandies(pokemon.getRequiredCandies())
            .moves(moves)
            .favorite(pokemon.isFavorite()).build();
  }

  /**
   * pokemonDtoMoveToPokemonMove .
   *
   * @param pokemonMoveDto .
   * @return PokemonMove
   */
  public static PokemonMove pokemonDtoMoveToPokemonMove(final PokemonMoveDto pokemonMoveDto) {
    if (pokemonMoveDto == null) {
      return null;
    }
    return PokemonMove.builder()
            .name(pokemonMoveDto.getName())
            .type(pokemonMoveDto.getType())
            .power(pokemonMoveDto.getPower())
            .build();
  }

  /**
   * pokemonMoveToPokemonMoveDto .
   *
   * @param pokemonMove .
   * @return PokemonMoveDto
   */
  public static PokemonMoveDto pokemonMoveToPokemonMoveDto(final PokemonMove pokemonMove) {
    if (pokemonMove == null) {
      return null;
    }
    return PokemonMoveDto.builder()
            .id(pokemonMove.getId())
            .name(pokemonMove.getName())
            .type(pokemonMove.getType())
            .power(pokemonMove.getPower())
            .build();
  }

}
