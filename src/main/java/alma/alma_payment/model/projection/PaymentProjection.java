package alma.alma_payment.model.projection;

import alma.alma_payment.model.enums.CurrencyType;
import alma.alma_payment.model.enums.PaymentStatus;

public interface PaymentProjection {
    Long getPaymentId();
    Double getAmount();
    CurrencyType getCurrency();
    PaymentStatus getStatus();
}
