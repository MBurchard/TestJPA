package de.mbur.test_jpa

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(
  repositoryBaseClass = BaseJpaRepositoryImpl::class
)
class JpaConfiguration {

  @Bean
  fun auditorAware(): AuditorAware<String> {
    return AuditorAware { Optional.of("system") }
  }
}
