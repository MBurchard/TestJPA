package de.mbur.test_jpa.data.alpha

import de.mbur.test_jpa.JpaConfiguration
import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

private val log = KotlinLogging.logger {}

@ActiveProfiles("test")
@DataJpaTest
@Import(JpaConfiguration::class)
class AlphaEntityRepositoryTest {

  @Autowired
  private lateinit var alphaEntityRepository: AlphaEntityRepository

  @Test
  fun `test AlphaEntity persistence`() {
    // given
    val entity = AlphaEntity(
      content = "test content",
    )

    // when
    val savedEntity = alphaEntityRepository.persist(entity)

    // then
    assertThat(savedEntity.id).isNotNull
    assertThat(savedEntity.content).isEqualTo("test content")

    log.debug { "saved entity: $savedEntity" }
  }
}
