package de.mbur.test_jpa.data.alpha

import io.hypersistence.utils.spring.repository.BaseJpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlphaEntityRepository: BaseJpaRepository<AlphaEntity, Long>
