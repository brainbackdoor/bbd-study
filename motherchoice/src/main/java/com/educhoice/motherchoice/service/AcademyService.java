package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import com.educhoice.motherchoice.utils.exceptions.NoAcademyIdException;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.AcademyTaggingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademyService {

    @Autowired
    private AcademyRepository academyRepository;

    public List<Academy> findMultipleAcademiesById(List<Long> academyIds) {
        return academyIds.stream().map(i -> academyRepository.findByAcademyId(i)).map(a -> a.orElseThrow(() -> new NoAcademyIdException("ID에 맞는 학원이 검색되지 않았습니다."))).collect(Collectors.toList());
    }

    public List<AcademyTaggingDto> findMultipleAcademiesByNameContaining(String name) {
        return academyRepository.findByAcademyNameContaining(name).stream().map(AcademyTaggingDto::getDto).collect(Collectors.toList());
    }

}
