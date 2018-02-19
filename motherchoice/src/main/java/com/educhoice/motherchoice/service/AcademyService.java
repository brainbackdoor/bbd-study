package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import com.educhoice.motherchoice.utils.exceptions.entity.NoAcademyIdException;
import com.educhoice.motherchoice.valueobject.models.academies.AcademyDto;
import com.educhoice.motherchoice.valueobject.models.academies.EmptyAcademy;
import com.educhoice.motherchoice.valueobject.models.academies.ImageUploadDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.AcademyTaggingDto;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcademyService {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private NewQuestionStoreService questionService;

    public Academy saveAcademy(Academy academy) {
        return academyRepository.save(academy);
    }

    public Academy updateAcademy(AcademyDto dto) {
        Academy originalAcademy = academyRepository.findOne(dto.getId());

        originalAcademy.update(dto);
        return academyRepository.save(originalAcademy);
    }

    public Academy getAcademyById(long academyId) {
        return academyRepository.findByAcademyId(academyId).orElseThrow(() -> new NoAcademyIdException("ID에 맞는 학원이 검색되지 않았습니다."));
    }

    public Academy getAcademyByName(String name) {
        return academyRepository.findByAcademyName(name).orElseThrow(() -> new NoAcademyIdException("이름에 맞는 학원이 검색되지 않았습니다."));
    }

    public List<Academy> findMultipleAcademiesById(List<Long> academyIds) {
        return academyIds.stream().map(i -> academyRepository.findByAcademyId(i)).map(a -> a.orElseThrow(() -> new NoAcademyIdException("ID에 맞는 학원이 검색되지 않았습니다."))).collect(Collectors.toList());
    }

    public List<Academy> findMultipleAcademiesByQueryDto(AcademyQueryDto dto) {
        return academyRepository.findAcademiesByQuery(dto).orElse(Arrays.asList(new Academy()));
    }

    public List<AcademyDto> getAcademyDtos(AcademyQueryDto dto) {
        List<Academy> academies = academyRepository.findAcademiesByQuery(dto).orElse(Arrays.asList(new EmptyAcademy()));

        return academies.stream().map(a -> AcademyDto.generateAcademyDto(a, questionService.getAverageInquiryResponseRate(a))).collect(Collectors.toList());
    }

    public List<AcademyTaggingDto> findMultipleAcademiesByNameContaining(String name) {
        return academyRepository.findByAcademyNameContaining(name).stream().map(AcademyTaggingDto::getDto).collect(Collectors.toList());
    }

    public void saveImages(ImageUploadDto imageDto) {
        Academy targetAcademy = academyRepository.findByAcademyId(imageDto.getAcademyId()).orElseThrow(() -> new NoAcademyIdException("ID에 맞는 학원이 검색되지 않았습니다."));

        targetAcademy.setImages(imageDto.getImgs());
        academyRepository.save(targetAcademy);
    }
}
