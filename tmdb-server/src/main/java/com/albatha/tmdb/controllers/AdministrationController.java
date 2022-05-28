package com.albatha.tmdb.controllers;

import java.util.Calendar;

import com.albatha.tmdb.services.interfaces.AdministrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdministrationController {

    @Autowired
    private AdministrationService administrationService;
    
    @CrossOrigin
    @GetMapping("/fake/create")
    public ResponseEntity<String> createFakeDataInDatabase(){
        administrationService.createFakeDatas();
        log.info("Fake datas created in database. Date : " + Calendar.getInstance().getTime());
        return ResponseEntity.status(HttpStatus.OK).body("Fake datas created in database. Do not forget to delete them !");
    }

    @CrossOrigin
    @GetMapping("/fake/delete")
    public ResponseEntity<String> deleteFakeDataInDatabase(){
        administrationService.deleteFakeDatas();
        log.info("Fake datas deleted from database. Date : " + Calendar.getInstance().getTime());
        return ResponseEntity.status(HttpStatus.OK).body("Fake datas deleted from database");
    }
    
}
