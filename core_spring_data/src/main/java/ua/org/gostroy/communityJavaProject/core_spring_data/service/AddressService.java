package ua.org.gostroy.communityJavaProject.core_spring_data.service;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.core_spring_data.entity.AddressForAudit;
import ua.org.gostroy.communityJavaProject.core_spring_data.repository.AddressRepository;

import java.util.List;

/**
 * Created by Panov Sergey on 10/3/2014.
 */
@Service
@Transactional
public class AddressService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public AddressForAudit findById(final Long id) {
        LOG.trace(getClass() + " : findAddressById ... ");
        AddressForAudit addressForAudit = addressRepository.findOne(id);
        if (addressForAudit != null) {
            LOG.trace(getClass() + " : findAddressById. ");
            return addressForAudit;
        }
        LOG.trace(getClass() + " : findAddressById. Not found.");
        return null;
    }

    @Transactional(readOnly = true)
    public List<AddressForAudit> findAll() {
        LOG.trace(getClass() + " : findAll ... ");
        List<AddressForAudit> addressForAudits = addressRepository.findAll();
        LOG.trace(getClass() + " : findAll.");
        return addressForAudits;
    }

    @Transactional(rollbackFor = Exception.class)
    public AddressForAudit save(final AddressForAudit addressForAudit) throws ConstraintViolationException {
        LOG.trace(getClass() + " : save ... ");
        AddressForAudit addressForAuditNew = addressRepository.saveAndFlush(addressForAudit);
        LOG.trace(getClass() + " : save.");
        return addressForAuditNew;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public AddressForAudit update(final AddressForAudit addressForAudit) throws ConstraintViolationException {
        LOG.trace(getClass() + " : update ... ");
        AddressForAudit addressForAuditNew = addressRepository.saveAndFlush(addressForAudit);
        LOG.trace(getClass() + " : update.");
        return addressForAuditNew;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final AddressForAudit addressForAudit) throws ConstraintViolationException {
        LOG.trace(getClass() + " : delete ... ");
        addressRepository.delete(addressForAudit);
        LOG.trace(getClass() + " : delete.");
    }

}
