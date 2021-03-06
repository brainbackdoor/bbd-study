package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DummyTuition {
	long tuitionId;
	double preSchooleAvgTuition;
	double elementaryAvgTuition;
	double middleAvgTuition;
	double highAvgTuition;
}
