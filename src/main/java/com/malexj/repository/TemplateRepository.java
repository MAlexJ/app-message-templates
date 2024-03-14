package com.malexj.repository;

import com.malexj.model.entity.TemplateEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TemplateRepository
    extends ReactiveMongoRepository<TemplateEntity, String> {

  @Query("{'id': ?0 }")
  @Update(update = "{ $set: { isActive : false }}")
  Mono<Long> updateTemplateStatusEntityById(String id);
}
