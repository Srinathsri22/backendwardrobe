package com.example.demo.Service;

import com.example.demo.DTO.OutfitDTO;
import com.example.demo.Entity.Outfit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OutfitService {
    public Outfit addOutfit(Long userId, String name, String link, String imgUrl);
    public List<OutfitDTO> getUserOutfits(Long userId);
}
