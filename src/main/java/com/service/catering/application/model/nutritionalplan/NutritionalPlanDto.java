package com.service.catering.application.model.nutritionalplan;

import lombok.Data;

@Data
public class NutritionalPlanDto {

	public String id;
	public String planDetails;
	public String nutritionistId;
	public Customer customer;
	public boolean isDelivered;
	public String analysisResultIds;
	public String createdDate;

}
