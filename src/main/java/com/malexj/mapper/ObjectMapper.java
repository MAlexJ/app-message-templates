package com.malexj.mapper;

import com.malexj.model.entity.TemplateEntity;
import com.malexj.model.request.TemplateRequest;
import com.malexj.model.response.TemplateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** MapStruct mapper: */
@Mapper(componentModel = "spring")
public interface ObjectMapper {

  @Mapping(source = "active", target = "isActive")
  TemplateResponse entityToResponse(TemplateEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(source = "isActive", target = "active")
  TemplateEntity requestToEntity(TemplateRequest request);
}
