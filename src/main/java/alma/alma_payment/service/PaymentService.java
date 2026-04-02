package alma.alma_payment.service;

import alma.alma_payment.model.dto.PaymentDetailResponseDto;
import alma.alma_payment.model.dto.PaymentDto;
import alma.alma_payment.model.dto.PaymentRequestDto;
import alma.alma_payment.model.dto.PaymentResponseDto;
import alma.alma_payment.model.entity.Payment;
import alma.alma_payment.model.enums.PaymentStatus;
import alma.alma_payment.model.projection.PaymentProjection;
import alma.alma_payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentResponseDto payment(PaymentRequestDto paymentRequestDto) {
        Payment payment = mapToEntity(paymentRequestDto);
        Payment savedPayment = paymentRepository.save(payment);
        return new PaymentResponseDto(savedPayment.getId(), savedPayment.getStatus());
    }

    public PaymentDetailResponseDto getPaymentDetail(Long paymentId) {
        Payment payment = findPaymentById(paymentId);

        return new PaymentDetailResponseDto(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getDescription(),
                payment.getClientId(),
                payment.getStatus()
        );
    }

    @Transactional
    public PaymentResponseDto confirmPayment(Long paymentId) {
        Payment payment = findPaymentById(paymentId);
        checkOnConfirmPayment(payment);

        payment.setStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
        return new PaymentResponseDto(payment.getId(), PaymentStatus.CONFIRMED);
    }

    @Transactional
    public PaymentResponseDto cancelPayment(Long paymentId) {
        Payment payment = findPaymentById(paymentId);

        payment.setStatus(PaymentStatus.CANCELED);
        paymentRepository.save(payment);
        return new PaymentResponseDto(payment.getId(), PaymentStatus.CANCELED);
    }

    public List<PaymentDto> getClientPayments(Long clientId) {
        List<PaymentProjection> projections = paymentRepository.getPaymentsByClientId(clientId);
        return projections.stream().map(this::mapProjectionToDto).toList();
    }

    private Payment mapToEntity(PaymentRequestDto dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.amount());
        payment.setCurrency(dto.currency());
        payment.setDescription(dto.description());
        payment.setClientId(dto.clientId());
        payment.setStatus(PaymentStatus.PENDING);
        return payment;
    }

    private PaymentDto mapProjectionToDto(PaymentProjection projection) {
        return new PaymentDto(
                projection.getPaymentId(),
                projection.getAmount(),
                projection.getCurrency(),
                projection.getStatus()
        );
    }

    private void checkOnConfirmPayment(Payment payment) {
        switch (payment.getStatus()) {
            case CONFIRMED -> throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Payment already confirmed");
            case CANCELED -> throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Payment already cancelled");
        }
    }

    private Payment findPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
    }
}
