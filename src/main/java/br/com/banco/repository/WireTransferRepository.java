package br.com.banco.repository;

import br.com.banco.entity.WireTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WireTransferRepository extends JpaRepository<WireTransfer, Long>, JpaSpecificationExecutor<WireTransfer> {
}
