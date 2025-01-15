package com.example.demo.Controller;

import com.example.demo.DTO.OutfitDTO;
import com.example.demo.Entity.Outfit;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/outfits")
@CrossOrigin(origins = "*")
public class WardrobeController {
    @Autowired
    private OutfitService outfitService;

    @PostMapping
    public ResponseEntity<?> addOutfit(@RequestBody Map<String, String> requestBody) {
        String userIdStr = requestBody.get("user_id");
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Missing or empty 'user_id' in request body.");
        }
        try {
            Long userId = Long.valueOf(userIdStr);
            String name = requestBody.get("name");
            String link = requestBody.get("link");
            String imgUrl = requestBody.get("img_url");
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Missing or empty 'name' in request body.");
            }

            if (link == null || link.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Missing or empty 'link' in request body.");
            }

            // Add the outfit using the service
            Outfit outfit = outfitService.addOutfit(userId, name, link, imgUrl);
            return ResponseEntity.ok(outfit);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid 'user_id': " + userIdStr);
        } catch (Exception e) {
            // Catch other unexpected exceptions and return a generic error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public List<OutfitDTO> getUserOutfits(@PathVariable Long userId) {
        List<OutfitDTO> wardrobe = outfitService.getUserOutfits(userId);
        return wardrobe;
    }
}
