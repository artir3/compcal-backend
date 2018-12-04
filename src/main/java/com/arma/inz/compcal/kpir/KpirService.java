package com.arma.inz.compcal.kpir;

import com.arma.inz.compcal.kpir.dto.KpirDTO;
import com.arma.inz.compcal.kpir.dto.KpirFilterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/kpir")
public interface KpirService {

    @GetMapping("/")
    ResponseEntity get(@RequestBody KpirFilterDTO kpirFilterDTO);

    @PostMapping("/")
    ResponseEntity create(@RequestBody KpirDTO kpirDTO);

    @PutMapping("/")
    ResponseEntity update(@RequestBody KpirDTO kpirDTO);

}
