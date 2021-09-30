package com.pokemon.pokedex.business;

import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonInfoDto;
import com.pokemon.pokedex.model.dto.PokemonPageDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.dto.PokemonRegionsDto;
import com.pokemon.pokedex.model.projections.PokemonRegionsProjection;

import java.util.List;

/**
 * <b>Class</b>:PokemonService <br/>.
 *
 * @author Carlos
 */
public interface PokemonService {
  PokemonDto savePokemon(PokemonDto pokemonDto) throws PokemonNotFoundException;

  List<PokemonDto> findAllPokemons(String orderByColumn, boolean ascending);

  List<PokemonDto> findAllPokemonsSort(String orderByColumn, boolean ascending);

  PokemonDto deletePokemonById(Long id) throws PokemonNotFoundException;

  PokemonDto findPokemonById(Long id) throws PokemonNotFoundException;

  PokemonDto updatePokemon(PokemonDto pokemonDto, Long id) throws PokemonNotFoundException;

  PokemonDto patchNamePokemon(PokemonPatchNameDto pokemonPatchNameDto, Long id) throws PokemonNotFoundException;

  PokemonDto patchFavoritePokemon(PokemonPatchFavoriteDto pokemonPatchFavoriteDto, Long id) throws PokemonNotFoundException;

  List<PokemonInfoDto> findAllPokemonInfo(Long maxId);

  PokemonPageDto findAllPageable(int page, int size);

  List<PokemonRegionsProjection> findAllPokemonRegions(String name);

  List<PokemonRegionsDto> findAllPokemonRegionsDto(String name);

  List<PokemonRegionsDto> findAllPokemonRegionsCriteria(String name);
}
