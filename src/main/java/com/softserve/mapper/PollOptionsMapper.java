package com.softserve.mapper;

import com.softserve.dto.PollOptionDTO;
import com.softserve.model.PollOption;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PollOptionsMapper {
    @Mapping(target = "poll.idPoll", source = "pollID")
    PollOption sourceToDestination(PollOptionDTO source);

    @InheritInverseConfiguration
    PollOptionDTO destinationToSource(PollOption destination);
}
