package org.ships.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.ships.Ship;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DrawServiceTest {

    @RepeatedTest(10)
    void shouldReturnProperlyEncodedCarrierAfterDraw() {

        //given
        //when
        List<String> result = DrawService.drawPositionOfShip(Ship.CARRIER);

        //then
        assertFalse(result.isEmpty());
        assertThat(result, hasSize(5));
        assertThat(result.toString(), containsString("C"));

        System.out.println(result);
    }

    @RepeatedTest(10)
    void shouldReturnProperlyEncodedBattleshipAfterDraw() {

        //given
        //when
        List<String> result = DrawService.drawPositionOfShip(Ship.BATTLESHIP);

        //then
        assertFalse(result.isEmpty());
        assertThat(result, hasSize(4));
        assertThat(result.toString(), containsString("B"));

        System.out.println(result);
    }

    @RepeatedTest(10)
    void shouldReturnProperlyEncodedDestroyerAfterDraw() {

        //given
        //when
        List<String> result = DrawService.drawPositionOfShip(Ship.DESTROYER);

        //then
        assertFalse(result.isEmpty());
        assertThat(result, hasSize(3));
        assertThat(result.toString(), containsString("D"));

        System.out.println(result);
    }

    @RepeatedTest(10)
    void shouldReturnProperlyEncodedSubmarineAfterDraw() {

        //given
        //when
        List<String> result = DrawService.drawPositionOfShip(Ship.SUBMARINE);

        //then
        assertFalse(result.isEmpty());
        assertThat(result, hasSize(3));
        assertThat(result.toString(), containsString("S"));

        System.out.println(result);
    }

    @RepeatedTest(10)
    void shouldReturnProperlyEncodedPatrolBoatAfterDraw() {

        //given
        //when
        List<String> result = DrawService.drawPositionOfShip(Ship.PATROL_BOAT);

        //then
        assertFalse(result.isEmpty());
        assertThat(result, hasSize(2));
        assertThat(result.toString(), containsString("P"));

        System.out.println(result);
    }

    @RepeatedTest(10)
    void shouldCreateListOfAllPossiblePositionsForPatrolBoat() {

        //given
        // when
        List<List<String>> result = DrawService.createListOfAllPossiblePositions(Ship.PATROL_BOAT);
        List<List<String>> distinct = result.stream().distinct().collect(Collectors.toList());

        //then
        assertThat(result.toString(), containsString("P.1,1"));
        assertThat(result.toString(), containsString("P.9,9"));
        assertThat(result.toString(), containsString("P.5,6"));
        assertThat(result, hasSize(180));
        assertThat(distinct, hasSize(180));

    }

    @RepeatedTest(10)
    void shouldCreateListOfAllPossiblePositionsForCarrier() {

        //given
        // when
        List<List<String>> result = DrawService.createListOfAllPossiblePositions(Ship.CARRIER);
        List<List<String>> distinct = result.stream().distinct().collect(Collectors.toList());

        //then
        assertThat(result.toString(), containsString("C.1,1"));
        assertThat(result.toString(), containsString("C.9,9"));
        assertThat(result.toString(), containsString("C.5,6"));
        assertThat(result, hasSize(120));
        assertThat(distinct, hasSize(120));

    }

}