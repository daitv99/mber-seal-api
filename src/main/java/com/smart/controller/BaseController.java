package com.smart.controller;

import com.smart.dto.handler.ResponseHandler;
import com.smart.dto.search.SearchDto;
import com.smart.service.BaseSearchService;
import com.smart.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class BaseController<T, ID> {

    protected BaseService<T, ID> baseService;

    protected BaseSearchService<T, ? extends SearchDto> searchService;

    protected BaseController(BaseService<T, ID> baseService,
                             BaseSearchService<T, ? extends SearchDto> searchService) {
        this.baseService = baseService;
        this.searchService = searchService;
    }

    //    @PostMapping("/page")
//    public <S extends SearchDto> ResponseEntity<Object>  getPage(@Valid @RequestBody S searchDto) {
//        return ResponseHandler.generateResponse(HttpStatus.OK, "", searchService.page(searchDto));
//    }
//
//    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll() {
        return ResponseHandler.generateResponse(HttpStatus.OK, "", baseService.getAll());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") ID id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, "", baseService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody T t) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "", baseService.creatOrUpdate(null, t));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") ID id,
                                         @Valid @RequestBody T t) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "", baseService.creatOrUpdate(id, t));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") ID id) {
        return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, "", baseService.deleteById(id));
    }

    @DeleteMapping("/permanently-deleted/{id}")
    public ResponseEntity<Object> deletedByIdPermanently(@PathVariable("id") ID id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, "", baseService.deletedByIdPermanently(id));
    }
}
