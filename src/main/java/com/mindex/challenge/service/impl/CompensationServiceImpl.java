package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * Creates record in the compensation table for the provided Compensation object
     * @param compensation - the object getting a compensation record created for
     * @return a new Compensation object
     */
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation for employee [{}]", compensation.getEmployeeId());
        compensationRepository.insert(compensation);

        return compensation;
    }

    /**
     * Retrieves a Compensation record for the desired employee
     * @param employeeId - id of the employee whose compensation record is being retrieved for.
     * @return the employees existing compensation Object
     */
    public  Compensation read(String employeeId) {
        LOG.debug("Fetching Compensation for employee with id [{}]", employeeId);

        Compensation compensation = compensationRepository.findByEmployeeId(employeeId);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        return compensation;
    }
}
