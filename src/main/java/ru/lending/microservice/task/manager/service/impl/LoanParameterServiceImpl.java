package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Mono<LoanParameter> create(LoanParameter lp) {
        return  lpRepository.save(lp)
                .flatMap(savedItem -> 
                	clientRepository.saveAll(lp.getClients()).collectList()
                	.then(Mono.just(savedItem)))
                .flatMap(savedItem -> 
                	collateralRepository.saveAll(lp.getCollaterals()).collectList()
	            	.then(Mono.just(savedItem)));
	}
}
