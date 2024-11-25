package de.mbur.test_jpa.data.alpha

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "AlphaData", schema = "TestJPA")
class AlphaEntity(
  @Id
  @Tsid
  val id: Long? = null,

  @Column(nullable = false)
  var content: String? = null,
) {
  override fun toString(): String {
    return "AlphaEntity(id=$id, content=$content)"
  }
}
