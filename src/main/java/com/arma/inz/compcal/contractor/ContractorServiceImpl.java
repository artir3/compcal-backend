package com.arma.inz.compcal.contractor;

import com.arma.inz.compcal.AuthorizationHeaderUtils;
import com.arma.inz.compcal.contractor.dto.ContractorDTO;
import com.arma.inz.compcal.contractor.dto.ContractorMiniDTO;
import com.arma.inz.compcal.users.BaseUser;
import com.arma.inz.compcal.users.BaseUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private ContractorController contractorController;

    @Autowired
    private AuthorizationHeaderUtils header;

    @Override
    public ResponseEntity get() {
        List<ContractorMiniDTO> list = contractorController.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity get(Long id) {
        ContractorDTO contractorDTO = contractorController.getOne(id);
        return new ResponseEntity<>(contractorDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(ContractorDTO dto) {
        Boolean update = contractorController.updateOne(dto);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @Override
    public ResponseEntity create(String authorization, ContractorDTO dto) {
        BaseUser baseUser = header.getUserFromAuthorization(authorization);
        if (baseUser == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Boolean created = contractorController.createOne(baseUser, dto);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(Long id) {
        Boolean deleted = contractorController.deleteOne(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
