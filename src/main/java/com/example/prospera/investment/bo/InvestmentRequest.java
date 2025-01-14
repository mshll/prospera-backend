package com.example.prospera.investment.bo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentRequest {

    private String name;
    private Double sharesOwned;
    private Double amountInvested;


}
