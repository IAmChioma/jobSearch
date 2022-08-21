package miu.edu.cs544.chioma.jobapp.controllers;

import miu.edu.cs544.chioma.jobapp.entities.Company;
import miu.edu.cs544.chioma.jobapp.entities.Job;
import miu.edu.cs544.chioma.jobapp.messaging.Sender;
import miu.edu.cs544.chioma.jobapp.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CompanyController {


    @Autowired
    private CompanyService companyService;
    @Autowired
    private Sender sender;



    @PostMapping(path = "/jobs/{id}/company")
    public Job createCompanyForJob(@PathVariable int id,@RequestBody Company company){
        return  companyService.createCompanyInJob(id,company);

    }


    @DeleteMapping(path = "/jobs/{id}/company/{companyId}")
    public String deleteCompanyById(@PathVariable int id, @PathVariable int companyId){
        return companyService.deleteCompanyInJob(id,companyId);

    }


    @GetMapping(path = "/jobs/{id}/company/{companyId}")
    public Company getCompanyInJob(@PathVariable int id,@PathVariable int companyId){
        return companyService.getJobCompany(id,companyId);


    }

    @PutMapping(path = "/jobs/{id}/company/{companyId}")
    public Company updateCompanyInJob(@PathVariable int id,@PathVariable int companyId, @RequestBody Company company){
        return companyService.updateCompanyInJob(id, companyId, company);
    }

}
