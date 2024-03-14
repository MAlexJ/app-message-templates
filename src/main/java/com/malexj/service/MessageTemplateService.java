package com.malexj.service;

import com.malexj.mapper.ObjectMapper;
import com.malexj.model.request.MessageTemplateRequest;
import com.malexj.model.response.MessageTemplateResponse;
import com.malexj.repository.MessageTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageTemplateService {

  private final ObjectMapper mapper;

  private final MessageTemplateRepository repository;

  public Flux<MessageTemplateResponse> findAll() {
    return repository.findAll().map(mapper::entityToResponse);
  }

  public Mono<MessageTemplateResponse> findById(String id) {
    return repository.findById(id).map(mapper::entityToResponse);
  }

  public Mono<MessageTemplateResponse> save(MessageTemplateRequest request) {
    return Mono.fromSupplier(() -> mapper.requestToEntity(request))
        .flatMap(repository::save)
        .map(mapper::entityToResponse);
  }

  public Mono<Long> updateStatusEntity(String id) {
    return repository
        .updateMessageTemplateStatusEntityById(id)
        .doOnNext(resp -> log.info("Records updated - {}", resp));
  }
}
