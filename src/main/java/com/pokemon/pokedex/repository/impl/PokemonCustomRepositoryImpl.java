package com.pokemon.pokedex.repository.impl;

import com.pokemon.pokedex.model.dto.PokemonInfoDto;
import com.pokemon.pokedex.model.dto.PokemonRegionsDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import com.pokemon.pokedex.model.entity.PokemonRegion;
import com.pokemon.pokedex.model.enums.OrderByColumn;
import com.pokemon.pokedex.repository.PokemonCustomRepository;
import com.pokemon.pokedex.util.PokemonQueries;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;


/**
 * <b>Class</b>:PokemonCustomRepositoryImpl <br/>.
 *
 * @author Carlos
 */
public class PokemonCustomRepositoryImpl implements PokemonCustomRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Pokemon> findAllByOrderByCreatedAtAndCp(final String orderByColumn, final boolean ascending) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Pokemon> criteriaQuery = builder.createQuery(Pokemon.class);
    Root<Pokemon> pokemonRoot = criteriaQuery.from(Pokemon.class);
    String column = "order";
    if (orderByColumn.equals(OrderByColumn.DATE.name())) {
      column = "createdAt";
    }
    if (orderByColumn.equals(OrderByColumn.CP.name())) {
      column = "cp";
    }
    Expression<?> expression = pokemonRoot.get(column);
    Order order = ascending ? builder.asc(expression) : builder.desc(expression);
    criteriaQuery.select(pokemonRoot);
    criteriaQuery.orderBy(order);
    return entityManager.createQuery(criteriaQuery).getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<PokemonInfoDto> findAllPokemonInfo(final Long maxId) {
    Query query = entityManager.createNativeQuery("select p.id, p.name from pokemons p where p.id >= :maxId", Tuple.class);
    query.setParameter("maxId", maxId);
    List<Tuple> pokemons = query.getResultList();
    return pokemons.stream().map(tuple -> PokemonInfoDto.builder()
            .id(Long.parseLong(tuple.get("id").toString()))
            .name(tuple.get("name").toString()).build()).collect(Collectors.toList());

  }

  @Override
  @SuppressWarnings("unchecked")
  public List<PokemonRegionsDto> findAllPokemonRegionsDto(final String name) {
    Query query = entityManager.createNativeQuery(PokemonQueries.POKEMON_REGION_PROJECTION_QUERY, Tuple.class);
    query.setParameter("name","%".concat(name).concat("%"));
    List<Tuple> pokemons = query.getResultList();
    return pokemons.stream()
            .map(tuple -> PokemonRegionsDto.builder()
                    .id(Long.parseLong(tuple.get("id").toString()))
                    .pokedexId(Long.parseLong(tuple.get("pokedex_id").toString()))
                    .name(tuple.get("name", String.class))
                    .type(tuple.get("type", String.class))
                    .region(tuple.get("region", String.class))
                    .generation(tuple.get("generation", String.class))
                    .build()).collect(Collectors.toList());
  }

  @Override
  public List<PokemonRegionsDto> findAllPokemonRegionsCriteria(final String name) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
    Root<Pokemon> pokemonRoot = criteriaQuery.from(Pokemon.class);
    Join<Pokemon, PokemonRegion> pokemonRegion = pokemonRoot.join("id", JoinType.INNER);
    criteriaQuery.multiselect(pokemonRoot,pokemonRegion);
    ParameterExpression<String> pokemonName = builder.parameter(String.class);
    criteriaQuery.where(builder.like(pokemonRoot.get("name"),pokemonName));

    TypedQuery<Tuple> query = entityManager.createQuery(criteriaQuery);
    query.setParameter(pokemonName,name);

    List<Tuple> pokemons = query.getResultList();
    return pokemons.stream()
            .map(tuple -> PokemonRegionsDto.builder()
                    .id(Long.parseLong(tuple.get("id").toString()))
                    .pokedexId(Long.parseLong(tuple.get("pokedex_id").toString()))
                    .name(tuple.get("name", String.class))
                    .type(tuple.get("type", String.class))
                    .region(tuple.get("region", String.class))
                    .generation(tuple.get("generation", String.class))
                    .build()).collect(Collectors.toList());
  }
}
