package com.pokemon.pokedex.business;

import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;

import java.util.List;

/**
 * <b>Class</b>:PokemonService <br/>.
 *
 * @author Carlos
 */
public interface PokemonService {
  PokemonDto savePokemon(PokemonDto pokemonDto) throws PokemonNotFoundException;

  List<PokemonDto> findAllPokemons(String orderByColumn, boolean ascending);

  PokemonDto deletePokemonById(Long id) throws PokemonNotFoundException;

  PokemonDto findPokemonById(Long id) throws PokemonNotFoundException;

  PokemonDto updatePokemon(PokemonDto pokemonDto, Long id) throws PokemonNotFoundException;

  PokemonDto patchNamePokemon(PokemonPatchNameDto pokemonPatchNameDto, Long id) throws PokemonNotFoundException;

  PokemonDto patchFavoritePokemon(PokemonPatchFavoriteDto pokemonPatchFavoriteDto, Long id) throws PokemonNotFoundException;


}
