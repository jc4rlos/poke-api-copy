package com.pokemon.pokedex.repository.impl;

import com.pokemon.pokedex.model.entity.Pokemon;
import com.pokemon.pokedex.model.enums.OrderByColumn;
import com.pokemon.pokedex.repository.PokemonCustomRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
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
}
