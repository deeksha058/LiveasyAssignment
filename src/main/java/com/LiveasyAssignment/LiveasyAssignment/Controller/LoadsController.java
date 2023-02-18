package com.LiveasyAssignment.LiveasyAssignment.Controller;

import com.LiveasyAssignment.LiveasyAssignment.Model.Loads;
import com.LiveasyAssignment.LiveasyAssignment.Service.LoadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class LoadsController {
    @Autowired
    private LoadsService loadsService;

    @PostMapping("/load")
    public ResponseEntity<String>  saveLoadsData(@RequestBody Loads loads){
        loadsService.saveLoadsData(loads);
        return new ResponseEntity<>("Data Saved Successfully!", HttpStatus.OK);
    }

    @GetMapping("/load")
    public ResponseEntity<List<Loads>> loadsWithTheShipperId(@RequestParam(value = "shipper_id" , required = false)UUID uuid){
        return new ResponseEntity<>(loadsService.loadsByShipperId(uuid) , HttpStatus.OK);
    }

    @GetMapping("load/{loadId}")
    public ResponseEntity<Loads> loadsDataById(@PathVariable("loadId") Integer id){
        Loads dataById = loadsService.getDataById(id);
        return new ResponseEntity<>(dataById, HttpStatus.OK);
    }

    @PutMapping("load/{loadId}")
    public ResponseEntity<String> updateLoadsData(@RequestBody Loads loads , @PathVariable("loadId") Integer id ) {
        Loads newLoadsData = loadsService.updateLoadsData(loads, id);
        if (newLoadsData == null){
            return  new ResponseEntity<>("Data with  Id " + id +  " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
    }

    @DeleteMapping("load/{loadId}")
    public ResponseEntity<String> deleteLoadsData(@PathVariable("loadId") Integer id) {
        Loads deleteLoadsData = loadsService.deleteLoadsData(id);
        if(deleteLoadsData == null){
            return  new ResponseEntity<>("Data with this Id " + id  + " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Deleted Successfully!", HttpStatus.OK);
    }

    @GetMapping("/load/getAll")
    public ResponseEntity<List<Loads>> loadsData(){
        return new ResponseEntity<>(loadsService.getAllData() , HttpStatus.OK);
    }

}
