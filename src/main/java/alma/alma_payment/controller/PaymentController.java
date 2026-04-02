package alma.alma_payment.controller;

import alma.alma_payment.model.dto.PaymentDetailResponseDto;
import alma.alma_payment.model.dto.PaymentDto;
import alma.alma_payment.model.dto.PaymentRequestDto;
import alma.alma_payment.model.dto.PaymentResponseDto;
import alma.alma_payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> payment(
            @RequestBody PaymentRequestDto paymentRequestDto
    ) {
        return ResponseEntity.ok(paymentService.payment(paymentRequestDto));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDetailResponseDto> getPaymentDetail(
            @PathVariable Long paymentId
    ) {
        return ResponseEntity.ok(paymentService.getPaymentDetail(paymentId));
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<PaymentResponseDto> confirmPayment(
            @PathVariable Long paymentId
    ) {
        return ResponseEntity.ok(paymentService.confirmPayment(paymentId));
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<PaymentResponseDto> cancelPayment(
            @PathVariable Long paymentId
    ) {
        return ResponseEntity.ok(paymentService.cancelPayment(paymentId));
    }

    @GetMapping("/clients/{clientId}/payments")
    public ResponseEntity<List<PaymentDto>> getClientPayments(
            @PathVariable Long clientId
    ) {
        return ResponseEntity.ok(paymentService.getClientPayments(clientId));
    }
}
