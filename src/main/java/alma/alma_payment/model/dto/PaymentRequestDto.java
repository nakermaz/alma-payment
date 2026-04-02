package alma.alma_payment.model.dto;

import alma.alma_payment.model.enums.CurrencyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;

@Schema(description = "Запрос на создание платежа")
public record PaymentRequestDto (
        @Schema(description = "Сумма платежа", example = "1500.00")
        @DecimalMin("0.01")
        Double amount,

        @Schema(description = "Валюта платежа", example = "KZT")
        CurrencyType currency,

        @Schema(description = "Описание платежа", example = "Заказ #123")
        String description,

        @Schema(description = "ID клиента", example = "1")
        Long clientId
) {
}
