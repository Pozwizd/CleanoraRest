package com.example.cleanorarest.mapper;

import com.example.cleanorarest.entity.Category;
import com.example.cleanorarest.entity.Cleaning;
import com.example.cleanorarest.entity.CleaningSpecifications;
import com.example.cleanorarest.model.cleaning.CleaningRequest;
import com.example.cleanorarest.model.cleaning.CleaningResponse;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryMapper.class, CleaningSpecificationsMapper.class}
)
public interface CleaningMapper {

    Cleaning toEntity(CleaningRequest cleaningRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cleaning partialUpdate(CleaningRequest cleaningRequest, @MappingTarget Cleaning cleaning);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "cleaningSpecifications", target = "serviceSpecificationsId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "cleaningSpecifications.name", target = "serviceSpecificationsName")
    CleaningResponse toResponse(Cleaning cleaning);

    List<CleaningResponse> toResponseList(List<Cleaning> cleanings);

    default Page<CleaningResponse> toResponsePage(Page<Cleaning> servicesPage) {
        return servicesPage.map(this::toResponse);
    }

    default Long toCategoryId(Category category) {
        return category != null ? category.getId() : null;
    }

    default Long toServiceSpecificationsId(CleaningSpecifications cleaningSpecifications) {
        return cleaningSpecifications != null ? cleaningSpecifications.getId() : null;
    }
}
