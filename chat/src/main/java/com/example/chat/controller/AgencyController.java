package com.example.chat.controller;

import com.example.chat.entity.Agency;
import com.example.chat.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping
    public List<Agency> getAllAgencies() {
        return agencyService.getAllAgencies();
    }

    @GetMapping("/{id}")
    public Optional<Agency> getAgencyById(@PathVariable Long id) {
        return agencyService.getAgencyById(id);
    }

    @PostMapping
    public Agency createAgency(@RequestBody Agency agency) {
        return agencyService.saveAgency(agency);
    }

    @PutMapping("/{id}")
    public Agency updateAgency(@PathVariable Long id, @RequestBody Agency agency) {
        agency.setId(id);
        return agencyService.saveAgency(agency);
    }

    @DeleteMapping("/{id}")
    public void deleteAgency(@PathVariable Long id) {
        agencyService.deleteAgency(id);
    }
}
