package alma.alma_payment.model.dto;

import alma.alma_payment.model.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с результатом операции над платежом")
public record PaymentResponseDto (
        @Schema(description = "ID платежа", example = "1")
        Long paymentId,

        @Schema(description = "Статус платежа", example = "PENDING")
        PaymentStatus status
) {
}
