package com.educhoice.motherchoice.valueobject.models.academies;

import java.util.List;
import java.util.stream.Collectors;

import com.educhoice.motherchoice.models.persistent.Academy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageListDto {

    private String imageDisplayTypes;
    private String uri;
    
    
    public static List<ImageListDto> generateImageList(Academy academy) {
        return academy.getImages().stream().map(i -> {
                return ImageListDto.builder()
                                .imageDisplayTypes(i.getImageDisplayTypes().toString())
                                .uri(i.getUri())
                                .build();
        }).collect(Collectors.toList());
    }
    
}