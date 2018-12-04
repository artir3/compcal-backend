package com.arma.inz.compcal.kpir;

import com.arma.inz.compcal.kpir.dto.KpirDTO;
import com.arma.inz.compcal.kpir.dto.KpirFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class KpirServiceImpl implements KpirService {
    @Autowired
    private KpirRepository kpirRepository;
    @Override
    public ResponseEntity get(KpirFilterDTO kpirFilterDTO) {
        return null;
    }

    @Override
    public ResponseEntity create(KpirDTO kpirDTO) {
        return null;
    }

    @Override
    public ResponseEntity update(KpirDTO kpirDTO) {
        return null;
    }
}
