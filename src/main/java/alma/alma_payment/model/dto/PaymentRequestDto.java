package alma.alma_payment.model.dto;

import alma.alma_payment.model.enums.CurrencyType;

public record PaymentRequestDto (
        Double amount,
        CurrencyType currency,
        String description,
        Long clientId
) {
}
