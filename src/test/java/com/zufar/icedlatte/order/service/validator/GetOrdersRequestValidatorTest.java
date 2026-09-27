package com.zufar.icedlatte.order.service.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.zufar.icedlatte.common.exception.BadRequestException;

class GetOrdersRequestValidatorTest {

    @Test
    void dateFromAfterDateToThrowsBadRequestException() {
        LocalDate dateFrom = LocalDate.of(2026, 9, 20);
        LocalDate dateTo = LocalDate.of(2026, 9, 10);

        assertThatThrownBy(() -> GetOrdersRequestValidator.validate(dateFrom, dateTo))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void dateFromBeforeDateToDoesNotThrow() {
        GetOrdersRequestValidator.validate(
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 20));
    }

    @Test
    void equalDatesDoNotThrow() {
        LocalDate date = LocalDate.of(2026, 9, 10);

        GetOrdersRequestValidator.validate(date, date);
    }

    @Test
    void nullDateFromDoesNotThrow() {
        GetOrdersRequestValidator.validate(
                null,
                LocalDate.of(2026, 9, 20));
    }

    @Test
    void nullDateToDoesNotThrow() {
        GetOrdersRequestValidator.validate(
                LocalDate.of(2026, 9, 10),
                null);
    }
}
