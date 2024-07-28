package com.haui.btl.demo.dto.response;

import com.haui.btl.demo.Entity.Additionalfunctions;
import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.Termofuse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ViewCarDetailsResponse {
    private Car car;
    private List<Termofuse> termsOfUse;
    private List<Additionalfunctions> additionalFunctions;
}
