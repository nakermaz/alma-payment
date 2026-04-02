package alma.alma_payment.model.dto;

import alma.alma_payment.model.enums.CurrencyType;
import alma.alma_payment.model.enums.PaymentStatus;

public record PaymentDetailResponseDto (
        Long paymentId,
        Double amount,
        CurrencyType currency,
        String description,
        Long clientId,
        PaymentStatus status
) {
}
