package net.blwsmartware.museum.mapper;

import net.blwsmartware.museum.dto.request.museum.MuseumRequest;
import net.blwsmartware.museum.dto.request.museum.MuseumUpdate;
import net.blwsmartware.museum.dto.response.museum.MuseumResponse;
import net.blwsmartware.museum.entity.Museum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MuseumMapper {
    @Mapping(target = "category", ignore = true)
    Museum toMuseum(MuseumRequest request);
    @Mapping(target = "category", ignore = true)
    void updateMuseum(MuseumUpdate museumUpdate, @MappingTarget Museum museum);
    MuseumResponse toMuseumResponse(Museum museum);
}
