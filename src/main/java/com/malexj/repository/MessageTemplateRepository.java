package com.malexj.repository;

import com.malexj.model.entity.MessageTemplateEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MessageTemplateRepository
    extends ReactiveMongoRepository<MessageTemplateEntity, String> {

  @Query("{'id': ?0 }")
  @Update(update = "{ $set: { isActive : false }}")
  Mono<Long> updateMessageTemplateStatusEntityById(String id);
}
