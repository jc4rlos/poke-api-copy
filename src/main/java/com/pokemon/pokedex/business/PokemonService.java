package com.pokemon.pokedex.business;

import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.model.dto.PokemonGetDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.dto.PokemonSaveDto;

import java.util.List;

/**
 * <b>Class</b>:PokemonService <br/>.
 *
 * @author Carlos
 */
public interface PokemonService {
  PokemonGetDto savePokemon(PokemonSaveDto pokemonSaveDto);

  List<PokemonGetDto> findAllPokemons();

  PokemonGetDto deletePokemonById(Long id) throws PokemonNotFoundException;

  PokemonGetDto findPokemonById(Long id) throws PokemonNotFoundException;

  PokemonGetDto updatePokemon(PokemonSaveDto pokemonSaveDto, Long id) throws PokemonNotFoundException;

  PokemonGetDto patchNamePokemon(PokemonPatchNameDto pokemonPatchNameDto, Long id) throws PokemonNotFoundException;

  PokemonGetDto patchFavoritePokemon(PokemonPatchFavoriteDto pokemonPatchFavoriteDto, Long id) throws PokemonNotFoundException;


}
