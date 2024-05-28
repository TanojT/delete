package com.app.userdetails.controller;

import com.app.common.dto.AssetDetails;
import com.app.userdetails.service.AssetDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/assets")
public class AssetDetailsController {

    private final AssetDetailsService assetDetailsService;

    @PostMapping
    public List<AssetDetails> create(@RequestBody AssetDetails assetDetails){
        assetDetailsService.create(assetDetails);
        return assetDetailsService.getAll();
    }

    @PutMapping
    public List<AssetDetails> update(@RequestBody AssetDetails assetDetails){
        assetDetailsService.update(assetDetails);
        return assetDetailsService.getAll();
    }

    @GetMapping("/getById/{id}")
    public AssetDetails getById(@PathVariable String id){
        return assetDetailsService.getById(id);
    }

    @GetMapping("/getAll")
    public List<AssetDetails> getAll(){
        return assetDetailsService.getAll();
    }

    @DeleteMapping("/{id}")
    public List<AssetDetails> delete(@PathVariable String id){
        assetDetailsService.delete(id);
        return assetDetailsService.getAll();
    }
}
