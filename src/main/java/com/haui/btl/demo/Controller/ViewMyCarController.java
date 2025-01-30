package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Service.ViewMyCarService;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/viewMyCar")
public class ViewMyCarController {

    @Autowired
    ViewMyCarService viewMyCarService;

    @GetMapping()
    public ApiResponse viewMyCars(){
        return viewMyCarService.viewMyCars();
    }
    @GetMapping("/{idcar}")
    public ApiResponse viewMyCarById(@PathVariable("idcar") Integer idcar){
        return viewMyCarService.viewMyCarById(idcar);
    }
}
