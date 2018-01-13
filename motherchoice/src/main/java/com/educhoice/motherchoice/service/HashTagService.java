package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.HashTag;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import com.educhoice.motherchoice.models.persistent.repositories.HashTagRepository;
import com.educhoice.motherchoice.utils.exceptions.entity.NoEntityFoundException;
import com.educhoice.motherchoice.valueobject.models.academies.HashTagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashTagService {

    @Autowired
    private HashTagRepository hashTagRepository;

    @Autowired
    private AcademyRepository academyRepository;

    public HashTag saveHashTag(HashTag tag) {
        return hashTagRepository.save(tag);
    }

    public List<HashTag> findExistingHashTags(String tagTitle) {
        return hashTagRepository.findByTitleContaining(tagTitle);
    }

    public HashTagDto updateHashtag(HashTagDto dto) {
        if(dto.getHashTagId() == 0) {
            return new HashTagDto(hashTagRepository.save(dto.generateTagEntity()));
        }

        HashTag tag = hashTagRepository.findById(dto.getHashTagId()).orElseThrow(() -> new NoEntityFoundException("검색한 해시태그가 존재하지 않습니다."));
        updateHashTagInfo(dto, tag);
        return new HashTagDto(hashTagRepository.save(tag));

    }

    private void updateHashTagInfo(HashTagDto dto, HashTag tag) {
        Academy targetAcademy = academyRepository.findOne(dto.getAcademyId());

        tag.addAcademy(targetAcademy);
    }


}
