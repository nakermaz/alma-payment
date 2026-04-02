# Alma Payment

Сервис платежей на Spring Boot с PostgreSQL и Flyway миграциями.

## Требования

- Java 17+
- PostgreSQL
- Maven 3.9+

## Установка и запуск

### 1. Создать базу данных

```sql
CREATE DATABASE alma_payment;
```

### 2. Настроить подключение

Отредактируйте `src/main/resources/application.yaml`, если ваши данные отличаются от значений по умолчанию:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/alma_payment
    username: postgres
    password: postgres
```

### 3. Собрать и запустить

```bash
./mvnw spring-boot:run
```

Flyway автоматически применит миграции при запуске.

## API

Базовый URL: `http://localhost:8080`

### Swagger UI

После запуска приложения откройте:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

### Эндпоинты

#### Создать платеж

```bash
curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 1500.00,
    "currency": "KZT",
    "description": "Заказ #123",
    "clientId": 1
  }'
```

Ответ `201`:
```json
{
  "paymentId": 1,
  "status": "PENDING"
}
```

#### Получить детали платежа

```bash
curl http://localhost:8080/api/payments/1
```

Ответ `200`:
```json
{
  "paymentId": 1,
  "amount": 1500.00,
  "currency": "KZT",
  "description": "Заказ #123",
  "clientId": 1,
  "status": "PENDING"
}
```

#### Подтвердить платеж

```bash
curl -X POST http://localhost:8080/api/payments/1/confirm
```

Ответ `200`:
```json
{
  "paymentId": 1,
  "status": "CONFIRMED"
}
```

#### Отменить платеж

```bash
curl -X POST http://localhost:8080/api/payments/1/cancel
```

Ответ `200`:
```json
{
  "paymentId": 1,
  "status": "CANCELED"
}
```

#### Получить платежи клиента

```bash
curl http://localhost:8080/api/payments/clients/1/payments
```

Ответ `200`:
```json
[
  {
    "paymentId": 1,
    "amount": 1500.00,
    "currency": "KZT",
    "status": "CONFIRMED"
  }
]
```

## Валюты

`KZT`, `USD`, `EUR`, `RUB`, `CNY`

## Статусы платежей

| Статус | Описание |
|---|---|
| `PENDING` | Создан, ожидает подтверждения |
| `CONFIRMED` | Платеж подтвержден |
| `CANCELED` | Платеж отменен |
