package com.pokemon.pokedex.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * <b>Class</b>: Pokemon <br/>.
 *
 * @author Carlos <br/>
 */

@Entity()
@Table(name = "pokemons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column(name = "pokedex_id")
  private Long pokedexId;
  @Column
  private String name;
  @Column(name = "order_number")
  private Integer order;
  @Column
  private Integer cp;
  @Column
  private Integer hp;
  @Column
  private BigDecimal weight;
  @Column
  private BigDecimal height;
  @Column
  private String type;
  @Column
  private String gender;
  @Column
  private Integer candy;
  @Column(name = "star_dust")
  private Integer starDust;
  @Column
  private String image;
  @Column
  private boolean favorite;
  @Column
  private boolean shiny;
  @Column
  private String evolution;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Timestamp updatedAt;
}
