package br.com.banco.repository;

import br.com.banco.entity.WireTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WireTransferRepository extends JpaRepository<WireTransfer, Long> {
}
