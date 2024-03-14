package com.malexj.controller;

import com.malexj.model.request.TemplateRequest;
import com.malexj.model.response.TemplateResponse;
import com.malexj.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * WebFlux supports using a single value reactive type to produce the ResponseEntity asynchronously,
 * and/or single and multi-value reactive types for the body. This allows a variety of async
 * responses with ResponseEntity as follows:
 *
 * <ul>
 *   <li>ResponseEntity<Mono<T>> or ResponseEntity<Flux<T>> make the response status and headers
 *       known immediately while the body is provided asynchronously at a later point. Use Mono if
 *       the body consists of 0..1 values or Flux if it can produce multiple values.
 *   <li>Mono<ResponseEntity<T>> provides all three: response status, headers, and body,
 *       asynchronously at a later point. This allows the response status and headers to vary
 *       depending on the outcome of asynchronous request handling.
 *   <li>Mono<ResponseEntity<Mono<T>>> or Mono<ResponseEntity<Flux<T>>> are yet another possible,
 *       albeit less common alternative. They provide the response status and headers asynchronously
 *       first and then the response body, also asynchronously, second.
 * </ul>
 *
 * Link to info: <a
 * href="https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/responseentity.html">ResponseEntity</a>
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/templates")
public class RestApiController {

  private final TemplateService service;

  @GetMapping
  public ResponseEntity<Flux<TemplateResponse>> findAll() {
    log.info("HTTP: find all templates");
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mono<TemplateResponse>> findById(@PathVariable String id) {
    log.info("HTTP: find template bu id - {}", id);
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping
  public ResponseEntity<Mono<TemplateResponse>> create(
      @RequestBody TemplateRequest request) {
    log.info("HTTP: create new template - {}", request);
    return ResponseEntity.ok(service.save(request));
  }

  /**
   * Issue with PATCH <a
   * href="https://stackoverflow.com/questions/45200142/spring-rest-partial-update-with-patch-method">Http
   * Patch method</a>
   */
  @PatchMapping("/{id}")
  public Mono<ResponseEntity<Void>> inactive(@PathVariable String id) {
    log.info("HTTP: change template status to inactive by id - {}", id);
    return service
        .updateStatusEntity(id)
        .map(resp -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
  }
}
