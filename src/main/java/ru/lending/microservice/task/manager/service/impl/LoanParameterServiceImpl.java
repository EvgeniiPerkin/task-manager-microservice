package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.LoanParameter;
import ru.lending.microservice.task.manager.repository.ClientRepository;
import ru.lending.microservice.task.manager.repository.CollateralRepository;
import ru.lending.microservice.task.manager.repository.LoanParameterRepository;
import ru.lending.microservice.task.manager.servce.LoanParameterService;

@Service
public class LoanParameterServiceImpl implements LoanParameterService {
	@Autowired
	private LoanParameterRepository lpRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CollateralRepository collateralRepository;

	@Override
    @Transactional
	public Mono<LoanParameter> create(@Valid Mono<LoanParameter> lp) {
        return lp.flatMap(l -> {
    		return lpRepository.save(l)
	            .flatMap(savedItem -> 
	            	clientRepository.saveAll(l.getClients()).collectList()
	            	.then(Mono.just(savedItem)))
	            .flatMap(savedItem -> 
	            	collateralRepository.saveAll(l.getCollaterals()).collectList()
	            	.then(Mono.just(savedItem)));
        });
	}

	@Override
	public Mono<LoanParameter> findByLoanParameterId(Long loanParameterId) {
		return lpRepository.findByLoanParameterId(loanParameterId);
	}
}
