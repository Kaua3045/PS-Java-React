package br.com.banco.configuration;

import br.com.banco.repository.WireTransferRepository;
import br.com.banco.service.WireTransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public WireTransferService wireTransferService(WireTransferRepository wireTransferRepository) {
        return new WireTransferService(wireTransferRepository);
    }
}
