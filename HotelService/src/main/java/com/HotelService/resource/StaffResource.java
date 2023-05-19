package com.HotelService.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staffs")
public class StaffResource {

    @GetMapping
    public ResponseEntity<List<String>> getStaffs(){
        List<String> staffList = Arrays.asList("Anish", "Madan", "Sabin", "Mahesh", "Progresh", "Bidur", "Bibek");
        return ResponseEntity.ok(staffList);
    }
}
