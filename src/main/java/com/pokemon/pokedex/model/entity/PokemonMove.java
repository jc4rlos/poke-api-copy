package com.pokemon.pokedex.model.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * <b>Class</b>: PokemonMoves <br/>.
 *
 * @author Carlos <br/>
 */
@Entity()
@Table(name = "pokemon_moves")
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PokemonMove extends Audit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String type;
  private Integer power;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pokemon_id" ,nullable = false)
  private Pokemon pokemon;
}
