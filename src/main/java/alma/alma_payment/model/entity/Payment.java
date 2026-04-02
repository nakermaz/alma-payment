package alma.alma_payment.model.entity;

import alma.alma_payment.model.enums.CurrencyType;
import alma.alma_payment.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    CurrencyType currency;

    @Column(name = "description")
    String description;

    @Column(name = "client_id")
    Long clientId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    PaymentStatus status;
}
