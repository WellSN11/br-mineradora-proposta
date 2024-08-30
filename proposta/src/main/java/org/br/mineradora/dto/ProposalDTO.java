package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@Jacksonized
public class ProposalDTO {

    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;

}
