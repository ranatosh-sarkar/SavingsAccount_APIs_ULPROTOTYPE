package com.ranatosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ranatosh.entityModel.TestModel;
import com.ranatosh.entityModel.TransactionsModel;
import com.ranatosh.serviceLogic.TestServiceLogic;
import com.ranatosh.serviceLogic.TransactionsServiceLogic;

@RestController
public class TransactionsController {

    @Autowired private TransactionsServiceLogic svc;

    @PostMapping("/deposit")
    public TransactionsModel deposit(@RequestBody TransactionsModel dto) {
        return svc.deposit(dto);
    }

    @PostMapping("/withdraw")
    public TransactionsModel withdraw(@RequestBody TransactionsModel dto) {
        return svc.withdraw(dto);
    }

    @PostMapping("/transferFund/{toContact}")
    public TransactionsModel transfer(@PathVariable long toContact,
                                      @RequestBody TransactionsModel dto) {
        return svc.transferFund(dto, toContact);
    }

    @GetMapping("/displayStatement/{contact}")
    public List<TransactionsModel> statement(@PathVariable long   contact,
                                             @RequestParam String password) {
        return svc.statement(contact, password);
    }
}
