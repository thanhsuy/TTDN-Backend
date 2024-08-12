package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Entity.Additionalfunctions;
import com.haui.btl.demo.Entity.Termofuse;
import com.haui.btl.demo.Service.AddCarService;
import com.haui.btl.demo.dto.request.AddCarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AddCarController {

    @Autowired
    AddCarService addCarService;

    @PostMapping("/addcar")
    @CrossOrigin(origins = "http://localhost:3000")
    public ApiResponse addCar(@RequestBody AddCarRequest request)
    {
        return addCarService.addCar(request);
    }

    @PostMapping("/addterm")
    public ApiResponse addTermofuse(@RequestBody Termofuse termofuse){return addCarService.addTerm(termofuse);}

    @PostMapping("/addfunction")
    public ApiResponse addFunction(@RequestBody Additionalfunctions additionalfunctions){return addCarService.addFunctions(additionalfunctions);}
}
