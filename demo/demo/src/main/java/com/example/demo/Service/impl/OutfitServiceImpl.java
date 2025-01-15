package com.example.demo.Service.impl;

import com.example.demo.DTO.OutfitDTO;
import com.example.demo.Entity.Outfit;
import com.example.demo.Entity.User;
import com.example.demo.Repository.OutfitRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutfitServiceImpl implements OutfitService {

    @Autowired
    private OutfitRepository outfitRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Outfit addOutfit(Long userId, String name, String link, String imgUrl) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Outfit newOutfit = new Outfit();
        newOutfit.setUser(user);
        newOutfit.setName(name);
        newOutfit.setLink(link);
        newOutfit.setImgUrl(imgUrl);
        return outfitRepository.save(newOutfit);
    }

    @Override
    public List<OutfitDTO> getUserOutfits(Long userId) {
        List<Outfit> outfits = outfitRepository.findByUserId(userId);
        List<OutfitDTO> outfitDTOS = new ArrayList<>();
        for (Outfit outfit : outfits) {
            OutfitDTO outfitDTO = new OutfitDTO();
            outfitDTO.setId(outfit.getId());
            outfitDTO.setName(outfit.getName());
            outfitDTO.setLink(outfit.getLink());
            outfitDTO.setImgUrl(outfit.getImgUrl());
            outfitDTOS.add(outfitDTO);
        }
        return outfitDTOS;
    }
}
