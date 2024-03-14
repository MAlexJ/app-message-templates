package com.malexj.model.response;

import java.time.LocalDateTime;

public record MessageTemplateResponse(
    String id, String description, String template, boolean isActive, LocalDateTime created) {}
