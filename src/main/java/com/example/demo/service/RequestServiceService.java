package com.example.demo.service;

import com.example.demo.entity.RequestService;
import com.example.demo.repository.RequestServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceService {

    @Autowired
    private RequestServiceRepository requestRepo;

    public RequestService saveRequest(RequestService req) {
        return requestRepo.save(req);
    }
}
