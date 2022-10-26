package com.softserve.service;

import com.softserve.dto.PollOptionDTO;
import com.softserve.mapper.PollOptionsMapper;
import com.softserve.repository.PollOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollOptionService {
    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Autowired
    private PollOptionsMapper pollOptionMapper;

    public void savePollOptions(PollOptionDTO pollOptionDTO) {
        pollOptionRepository.save(pollOptionMapper.sourceToDestination(pollOptionDTO));
    }
}
