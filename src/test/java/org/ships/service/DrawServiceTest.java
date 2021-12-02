package org.ships.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.ships.Ship;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DrawServiceTest {

    @RepeatedTest(10)
    void shouldReturnProperlyEncodedShipAfterDraw(){

        //given
        //when
        List<String> result = DrawService.drawShipPlacement(Ship.CARRIER);

        //then
        assertFalse(result.isEmpty());
        assertThat(result, hasSize(5));
        assertThat(result.toString(), containsString("C"));

        System.out.println(result);
    }

}