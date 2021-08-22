package com.pokemon.pokedex.repository;

import com.pokemon.pokedex.model.dto.PokemonInfoDto;
import com.pokemon.pokedex.model.entity.Pokemon;

import java.util.List;

/**
 * <b>Class</b>:PokemonCustomRepository <br/>.
 *
 * @author Carlos
 */
public interface PokemonCustomRepository {
  List<Pokemon> findAllByOrderByCreatedAtAndCp(String orderByColumn, boolean ascending);

  List<PokemonInfoDto> findAllPokemonInfo(Long maxId);
}
