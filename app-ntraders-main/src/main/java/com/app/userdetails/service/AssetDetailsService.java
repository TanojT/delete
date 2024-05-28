package com.app.userdetails.service;

import com.app.common.dto.AssetDetails;
import com.app.userdetails.repository.AssetDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetDetailsService {
    private final AssetDetailsRepository assetDetailsRepository;

    public void create(AssetDetails assetDetails){
        assetDetailsRepository.create(assetDetails);
    }
    public void update(AssetDetails assetDetails){
        assetDetailsRepository.update(assetDetails);
    }
    public AssetDetails getById(String id){
        return assetDetailsRepository.getById(id);
    }

    public List<AssetDetails> getAll(){
        return assetDetailsRepository.getAll();
    }

    public void delete(String id){
        assetDetailsRepository.delete(id);
    }
}
