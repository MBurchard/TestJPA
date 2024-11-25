package de.mbur.test_jpa.data.beta

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "BetaData", schema = "TestJPA")
class BetaEntity(
  @Id
  @Tsid
  val id: Long? = null,

  @CreatedDate
  @Column(nullable = false, updatable = false)
  var created: Instant? = null,

  @LastModifiedDate
  var modified: Instant? = null,

  @Version
  @Column(nullable = false)
  var vnr: Int = 0,

  @Column(nullable = false)
  var content: String?,
) {

  override fun toString(): String {
    return "BetaEntity(id=$id, created=$created, modified=$modified, vnr=$vnr, content=$content)"
  }
}
