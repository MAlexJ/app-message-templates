package com.malexj.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "message_templates")
@TypeAlias("MessageTemplateEntity")
public class MessageTemplateEntity implements Persistable<String> {
  @MongoId private String id;

  private String description;

  @Indexed(unique = true)
  private String template;

  private boolean isActive;

  @CreatedDate private LocalDateTime created;

  @Override
  @JsonIgnore
  public boolean isNew() {
    return Objects.isNull(getCreated());
  }
}
