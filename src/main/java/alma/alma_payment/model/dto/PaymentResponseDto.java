package alma.alma_payment.model.dto;

import alma.alma_payment.model.enums.PaymentStatus;

public record PaymentResponseDto (
        Long paymentId,
        PaymentStatus status
) {
}
