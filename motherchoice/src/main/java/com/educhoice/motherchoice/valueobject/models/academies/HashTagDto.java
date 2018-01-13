package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.HashTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HashTagDto {

    private long academyId;
    private long hashTagId;
    private String hashTagText;

    public HashTagDto(HashTag tag) {
        this.hashTagId = tag.getId();
        this.hashTagText   = tag.getTitle();
    }

    public HashTag generateTagEntity() {
        HashTag tag = new HashTag();
        tag.setTitle(this.hashTagText);

        return tag;
    }
}
