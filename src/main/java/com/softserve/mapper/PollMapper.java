package com.softserve.mapper;

import com.softserve.dto.PollDTO;
import com.softserve.model.Poll;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PollOptionsMapper.class})
public interface PollMapper {
    @Mapping(source = "id", target = "idPoll")
    Poll sourceToDestination(PollDTO source);
    @InheritInverseConfiguration
    PollDTO destinationToSource(Poll destination);
}
