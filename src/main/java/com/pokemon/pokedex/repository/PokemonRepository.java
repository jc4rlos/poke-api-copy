package com.pokemon.pokedex.repository;

import com.pokemon.pokedex.model.dto.PokemonInfoDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import com.pokemon.pokedex.model.projections.PokemonRegionsProjection;
import com.pokemon.pokedex.util.PokemonQueries;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * <b>Class</b>:PokemonRepository <br/>.
 *
 * @author Carlos
 */
@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>, PokemonCustomRepository {

  @Modifying
  @Transactional
  @Query(nativeQuery = true, value = "UPDATE pokemons SET candy = (candy + :candy) WHERE pokedex_id = :pokedexId AND deleted = 0")
  int updatePokemonCandies(@Param("candy") Integer candy, @Param("pokedexId") Long pokedexId);


  @Query(nativeQuery = true, value = "select id, name from pokemons where id >= ?1")
  Collection<PokemonInfoDto> findAllPokemonInfoOne(Long maxId);

  @Query(nativeQuery = true, value = PokemonQueries.POKEMON_REGION_PROJECTION_QUERY)
  Collection<PokemonRegionsProjection> findAllPokemonRegions(@Param("name") String name);
}
