package com.pokemon.pokedex.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * <b>Class</b>: Audit <br/>.
 *
 * @author Carlos <br/>
 */
@MappedSuperclass
@Getter
@Setter
public  class Audit {

  @Column(name = "created_at")
  @CreatedDate
  private Timestamp createdAt;
  @Column(name = "updated_at")
  @LastModifiedDate
  private Timestamp updatedAt;

}
