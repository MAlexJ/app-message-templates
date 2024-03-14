package com.malexj.mapper;

import com.malexj.model.entity.MessageTemplateEntity;
import com.malexj.model.request.MessageTemplateRequest;
import com.malexj.model.response.MessageTemplateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** MapStruct mapper: */
@Mapper(componentModel = "spring")
public interface ObjectMapper {

  @Mapping(source = "active", target = "isActive")
  MessageTemplateResponse entityToResponse(MessageTemplateEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(source = "isActive", target = "active")
  MessageTemplateEntity requestToEntity(MessageTemplateRequest request);
}
