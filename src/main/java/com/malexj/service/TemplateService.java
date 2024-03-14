package com.malexj.service;

import com.malexj.mapper.ObjectMapper;
import com.malexj.model.request.TemplateRequest;
import com.malexj.model.response.TemplateResponse;
import com.malexj.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateService {

  private final ObjectMapper mapper;

  private final TemplateRepository repository;

  public Flux<TemplateResponse> findAll() {
    return repository.findAll().map(mapper::entityToResponse);
  }

  public Mono<TemplateResponse> findById(String id) {
    return repository.findById(id).map(mapper::entityToResponse);
  }

  public Mono<TemplateResponse> save(TemplateRequest request) {
    return Mono.fromSupplier(() -> mapper.requestToEntity(request))
        .flatMap(repository::save)
        .map(mapper::entityToResponse);
  }

  public Mono<Long> updateStatusEntity(String id) {
    return repository
        .updateTemplateStatusEntityById(id)
        .doOnNext(resp -> log.info("Records updated - {}", resp));
  }
}
