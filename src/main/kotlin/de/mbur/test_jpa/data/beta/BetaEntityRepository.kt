package de.mbur.test_jpa.data.beta

import io.hypersistence.utils.spring.repository.BaseJpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BetaEntityRepository: BaseJpaRepository<BetaEntity, Long>
