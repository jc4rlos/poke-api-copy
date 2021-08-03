package com.pokemon.pokedex.model.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE pokemons SET deleted=1 WHERE id = ?")
@Where(clause = "deleted =0")
public class Pokemon extends Audit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "pokedex_id")
  private Long pokedexId;
  private String name;
  @Column(name = "order_number")
  private Integer order;
  private Integer cp;
  private Integer hp;
  private BigDecimal weight;
  private BigDecimal height;
  private String type;
  private String gender;
  private Integer candy;
  @Column(name = "star_dust")
  private Integer starDust;
  private String image;
  private boolean favorite;
  private boolean shiny;
  private String evolution;

  @Column(name = "required_candies")
  private Integer requiredCandies;

  @Builder.Default
  private Boolean deleted = Boolean.FALSE;

  @OneToMany(mappedBy = "pokemon",cascade = CascadeType.REMOVE)
  private List<PokemonMove> moves ;

}
