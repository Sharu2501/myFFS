package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Oral;
import org.myfss.service.OralServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Orals")
@RestController
@RequestMapping("/api/orals")
@RequiredArgsConstructor
public class OralController {

    private final OralServiceImpl oralServiceImpl;

    @GetMapping("/{id}")
    public Oral getOralById(@PathVariable Long id) {
        return oralServiceImpl.getOralById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Oral> updateOral(@PathVariable Long id, @RequestBody Oral oral) {
        Oral updated = oralServiceImpl.updateOral(id, oral);
        return ResponseEntity.ok(updated);
    }
}
