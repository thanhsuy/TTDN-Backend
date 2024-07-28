package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Service.SearchCarService;
import com.haui.btl.demo.dto.request.SearchCarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.SearchCarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchCar")
public class SearchCarController {

    @Autowired
    SearchCarService searchCarService;


    @PostMapping
    public ApiResponse<List<SearchCarResponse>> searchCar(@RequestBody SearchCarRequest searchCarRequest){
        return searchCarService.getListCar(searchCarRequest);
    }
}
