package com.pokemon.pokedex.repository;

import com.pokemon.pokedex.model.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>Class</b>:PokemonRepository <br/>.
 * @author Carlos
 */
@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Long>,PokemonCustomRepository {
  @Modifying
  @Query(nativeQuery = true,value = "UPDATE pokemons SET candy = (candy + :candy) WHERE pokedex_id = :pokedexId AND deleted = 0")
  int updatePokemonCandies(@Param("candy") int candy, @Param("pokedexId") Long pokedexId);
}
