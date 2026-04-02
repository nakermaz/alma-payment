package alma.alma_payment.model.dto;

import alma.alma_payment.model.enums.CurrencyType;
import alma.alma_payment.model.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Детальная информация о платеже")
public record PaymentDetailResponseDto (
        @Schema(description = "ID платежа", example = "1")
        Long paymentId,

        @Schema(description = "Сумма платежа", example = "1500.00")
        Double amount,

        @Schema(description = "Валюта платежа", example = "KZT")
        CurrencyType currency,

        @Schema(description = "Описание платежа", example = "Заказ #123")
        String description,

        @Schema(description = "ID клиента", example = "1")
        Long clientId,

        @Schema(description = "Статус платежа", example = "PENDING")
        PaymentStatus status
) {
}
