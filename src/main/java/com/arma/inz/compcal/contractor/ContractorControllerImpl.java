package com.arma.inz.compcal.contractor;


import com.arma.inz.compcal.bankaccount.BankAccountController;
import com.arma.inz.compcal.bankaccount.BankAccountDTO;
import com.arma.inz.compcal.contractor.dto.ContractorDTO;
import com.arma.inz.compcal.contractor.dto.ContractorFilterDTO;
import com.arma.inz.compcal.contractor.dto.ContractorMiniDTO;
import com.arma.inz.compcal.contractor.dto.ContractorSelectDTO;
import com.arma.inz.compcal.users.BaseUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ContractorControllerImpl implements ContractorController {

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private BankAccountController bankAccountController;

    @Override
    public ContractorMiniDTO parseToDTO(Contractor contractor) {
        ContractorMiniDTO dto = new ContractorMiniDTO();
        BeanUtils.copyProperties(contractor, dto);
        dto.setAddress(contractor.getPrettyAddress());
        dto.setPersonName(contractor.getPrettyName());
        return dto;
    }

    @Override
    public List<ContractorMiniDTO> getAll(BaseUser baseUser, ContractorFilterDTO filterDTO) {
        List<Contractor> list = contractorRepository.findAll(ContractorSpecification.getAllByFilter(baseUser, filterDTO));
        List<ContractorMiniDTO> result = new ArrayList<>();
        for (Contractor contractor : list) {
            ContractorMiniDTO dto = parseToDTO(contractor);
            result.add(dto);
        }
        return result;
    }

    @Override
    public ContractorDTO getOne(Long id) {
        Optional<Contractor> entity = contractorRepository.findById(id);
        ContractorDTO dto = null;
        if (entity != null) {
            Contractor contractor = entity.get();
            dto = new ContractorDTO();
            BeanUtils.copyProperties(contractor, dto);
            Set<BankAccountDTO> bankAccountDTOSet = bankAccountController.copyToDTO(contractor.getBankAccounts());
            dto.setBankAccounts(bankAccountDTOSet);
        }
        return dto;
    }

    @Override
    public Contractor getOneEntity(Long id) {
        Optional<Contractor> entity = contractorRepository.findById(id);
        return entity != null? entity.get() : null;
    }

    @Override
    public Boolean updateOne(ContractorDTO dto) {
        Optional<Contractor> optional = contractorRepository.findById(dto.getId());
        if (optional != null) {
            Contractor contractor = optional.get();
            BeanUtils.copyProperties(dto, contractor, "id", "baseUser", "createdAt", "kpirList", "bankAccounts");
            contractor.setModifiedAt(LocalDateTime.now());
            Contractor entity = contractorRepository.save(contractor);
            bankAccountController.saveOrUpdate(dto.getBankAccounts(), entity);
        }
        return optional != null;
    }

    @Override
    public Boolean createOne(BaseUser baseUser, ContractorDTO dto) {
        Contractor entity = new Contractor();
        BeanUtils.copyProperties(dto, entity, "id", "kpirList", "bankAccounts");
        entity.setBaseUser(baseUser);
        entity.setModifiedAt(LocalDateTime.now());
        entity.setCreatedAt(LocalDateTime.now());
        entity = contractorRepository.save(entity);
        bankAccountController.saveOrUpdate(dto.getBankAccounts(), entity);
        return entity.getId() != null;
    }

    @Override
    public Boolean deleteOne(Long id) {
        Optional<Contractor> optional = contractorRepository.findById(id);
        if (optional != null) {
            contractorRepository.delete(optional.get());
        }
        return optional != null;
    }

    @Override
    public List<ContractorSelectDTO> getAllToSelect(BaseUser baseUser) {
        List<Contractor> list = contractorRepository.findAll(ContractorSpecification.byBaseUser(baseUser));
        List<ContractorSelectDTO> result = new ArrayList<>();
        for (Contractor contractor : list) {
            ContractorSelectDTO target = new ContractorSelectDTO();
            BeanUtils.copyProperties(contractor, target);
            result.add(target);
        }
        return result;
    }
}
