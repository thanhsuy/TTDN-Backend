package com.haui.btl.demo.Mapper;


import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackResponse toFeedbackResponse(Feedback feedback);

}
