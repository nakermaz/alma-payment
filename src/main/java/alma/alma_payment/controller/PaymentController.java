package alma.alma_payment.controller;

import alma.alma_payment.model.dto.PaymentDetailResponseDto;
import alma.alma_payment.model.dto.PaymentDto;
import alma.alma_payment.model.dto.PaymentRequestDto;
import alma.alma_payment.model.dto.PaymentResponseDto;
import alma.alma_payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Платежи", description = "API для управления платежами")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Создать платеж", description = "Создает новый платеж со статусом PENDING")
    @ApiResponse(responseCode = "201", description = "Платеж успешно создан")
    public ResponseEntity<PaymentResponseDto> payment(
            @Valid @RequestBody PaymentRequestDto paymentRequestDto
    ) {
        return ResponseEntity.status(201).body(paymentService.payment(paymentRequestDto));
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Получить детали платежа", description = "Возвращает полную информацию о платеже по его ID")
    @ApiResponse(responseCode = "200", description = "Детали платежа")
    @ApiResponse(responseCode = "404", description = "Платеж не найден")
    public ResponseEntity<PaymentDetailResponseDto> getPaymentDetail(
            @Parameter(description = "ID платежа") @PathVariable Long paymentId
    ) {
        return ResponseEntity.ok(paymentService.getPaymentDetail(paymentId));
    }

    @PostMapping("/{paymentId}/confirm")
    @Operation(summary = "Подтвердить платеж", description = "Переводит платеж в статус CONFIRMED")
    @ApiResponse(responseCode = "200", description = "Платеж подтвержден")
    @ApiResponse(responseCode = "400", description = "Платеж уже подтвержден или отменен")
    @ApiResponse(responseCode = "404", description = "Платеж не найден")
    public ResponseEntity<PaymentResponseDto> confirmPayment(
            @Parameter(description = "ID платежа") @PathVariable Long paymentId
    ) {
        return ResponseEntity.ok(paymentService.confirmPayment(paymentId));
    }

    @PostMapping("/{paymentId}/cancel")
    @Operation(summary = "Отменить платеж", description = "Переводит платеж в статус CANCELED")
    @ApiResponse(responseCode = "200", description = "Платеж отменен")
    @ApiResponse(responseCode = "404", description = "Платеж не найден")
    public ResponseEntity<PaymentResponseDto> cancelPayment(
            @Parameter(description = "ID платежа") @PathVariable Long paymentId
    ) {
        return ResponseEntity.ok(paymentService.cancelPayment(paymentId));
    }

    @GetMapping("/clients/{clientId}/payments")
    @Operation(summary = "Получить платежи клиента", description = "Возвращает список всех платежей указанного клиента")
    @ApiResponse(responseCode = "200", description = "Список платежей клиента")
    public ResponseEntity<List<PaymentDto>> getClientPayments(
            @Parameter(description = "ID клиента") @PathVariable Long clientId
    ) {
        return ResponseEntity.ok(paymentService.getClientPayments(clientId));
    }
}
