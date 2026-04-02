package alma.alma_payment.repository;

import alma.alma_payment.model.entity.Payment;
import alma.alma_payment.model.projection.PaymentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = """
        SELECT 
            p.id AS paymentId,
            p.amount AS amount,
            p.currency AS currency,
            p.status AS status
        FROM payments p
        WHERE p.client_id = :clientId
    """, nativeQuery = true)
    List<PaymentProjection> getPaymentsByClientId(
            @Param("clientId") Long clientId
    );
}
