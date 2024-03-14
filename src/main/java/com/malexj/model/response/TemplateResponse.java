package com.malexj.model.response;

import java.time.LocalDateTime;

public record TemplateResponse(
    String id, String description, String template, boolean isActive, LocalDateTime created) {}
