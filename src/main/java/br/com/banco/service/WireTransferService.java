package br.com.banco.service;

import br.com.banco.entity.WireTransfer;
import br.com.banco.repository.WireTransferRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WireTransferService {

    private final WireTransferRepository wireTransferRepository;

    public WireTransferService(WireTransferRepository wireTransferRepository) {
        this.wireTransferRepository = wireTransferRepository;
    }


    public List<WireTransfer> listAllWireTransfer(
            final LocalDate startDate,
            final LocalDate endDate,
            final String operatorName
    ) {
        Specification<WireTransfer> specification = wireTransferSpecifications(startDate, endDate, operatorName);
        return wireTransferRepository.findAll(specification);
    }

    private Specification<WireTransfer> wireTransferSpecifications(
            final LocalDate startDate,
            final LocalDate endDate,
            final String operatorName
    ) {
        return (Root<WireTransfer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (startDate != null && endDate != null) {
                predicates.add(criteriaBuilder.between(root.get("data"), startDate, endDate));
            } else if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("data"), startDate));
            } else if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("data"), endDate));
            }

            if (operatorName != null) {
                predicates.add(criteriaBuilder.equal(root.get("operador"), operatorName));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
