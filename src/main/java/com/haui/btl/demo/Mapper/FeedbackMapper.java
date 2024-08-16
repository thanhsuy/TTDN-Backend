package com.haui.btl.demo.Mapper;


import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    @Mapping(target = "carName", ignore = true)
    @Mapping(target = "carModel", ignore = true)
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "bookingStartDate", ignore = true)
    @Mapping(target = "bookingEndDate", ignore = true)
    FeedbackResponse toFeedbackResponse(Feedback feedback);

}
