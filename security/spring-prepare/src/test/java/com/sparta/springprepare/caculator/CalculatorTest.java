package com.sparta.springprepare.caculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    @DisplayName("더하기 테스트")
    void test1() {
        Calculator calculator = new Calculator();
        Double result = calculator.operate(0, "+", 5);
        System.out.println("result = " + result);
        Assertions.assertThat(result)
                  .isEqualTo(5);
    }


    @Test
    @DisplayName("나누기 테스트")
    void test2() {
        Calculator calculator = new Calculator();
        Double result = calculator.operate(8, "/", 2);
        System.out.println("result = " + result);
        Assertions.assertThat(result)
                  .isEqualTo(4);
    }
}