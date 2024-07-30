package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.ViewCarDetailsService;
import com.haui.btl.demo.dto.response.ViewCarDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cars")
public class ViewCarDetailsController {

    @Autowired
    private ViewCarDetailsService viewCarService;

    @GetMapping("/{id}")
    public ResponseEntity<ViewCarDetailsResponse> getCarDetails(@PathVariable int id) {
        ViewCarDetailsResponse carDetails = viewCarService.getCarDetails(id);
        return ResponseEntity.ok(carDetails);
    }

}
