package com.example.model;

import java.sql.Time;
import java.util.Arrays;

import com.example.model.DateTime.WeekDays;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DummyImage {
	public enum ImageDisplayTypes {
		FULL_SIZED(0), MID_SIZED(1), THUMB(2);

		private int code;

		ImageDisplayTypes(int code) {
			this.code = code;
		}

		public static ImageDisplayTypes getImageDisplayTypesByCode(int code) {
			return Arrays.stream(ImageDisplayTypes.values()).filter(types -> types.isMatchingCode(code)).findAny()
					.get();
		}

		private boolean isMatchingCode(int code) {
			return code == this.code;
		}

		@JsonValue
		public int getCode() {
			return this.code;
		}

	}

	public DummyImage(long imageId, int code, String uri) {
		this.imageId = imageId;
		this.imageDisplayTypes = ImageDisplayTypes.getImageDisplayTypesByCode(code);
		this.uri = uri;

	}

	private long imageId;
	private ImageDisplayTypes imageDisplayTypes;
	private String uri;

	
	
}
