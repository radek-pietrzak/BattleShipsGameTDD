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
        assertThat(DrawService.getAllPossiblePositions(), hasSize(120));

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
        assertThat(DrawService.getAllPossiblePositions(), hasSize(140));

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
        assertThat(DrawService.getAllPossiblePositions(), hasSize(160));

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
        assertThat(DrawService.getAllPossiblePositions(), hasSize(160));

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
        assertThat(DrawService.getAllPossiblePositions(), hasSize(180));

        System.out.println(result);
    }

    @RepeatedTest(10)
    void shouldCreateListOfAllPossiblePositionsForPatrolBoat() {

        //given
        // when
        DrawService.createListOfAllPossiblePositions(Ship.PATROL_BOAT);
        List<List<String>> distinct = DrawService.getAllPossiblePositions().stream().distinct().collect(Collectors.toList());

        //then
        assertThat(DrawService.getAllPossiblePositions().toString(), containsString("P.1,1"));
        assertThat(DrawService.getAllPossiblePositions().toString(), containsString("P.9,9"));
        assertThat(DrawService.getAllPossiblePositions().toString(), containsString("P.5,6"));
        assertThat(DrawService.getAllPossiblePositions(), hasSize(180));
        assertThat(distinct, hasSize(180));

    }

    @RepeatedTest(10)
    void shouldCreateListOfAllPossiblePositionsForCarrier() {

        //given
        // when
        DrawService.createListOfAllPossiblePositions(Ship.CARRIER);
        List<List<String>> distinct = DrawService.getAllPossiblePositions().stream().distinct().collect(Collectors.toList());

        //then
        assertThat(DrawService.getAllPossiblePositions().toString(), containsString("C.1,1"));
        assertThat(DrawService.getAllPossiblePositions().toString(), containsString("C.9,9"));
        assertThat(DrawService.getAllPossiblePositions().toString(), containsString("C.5,6"));
        assertThat(DrawService.getAllPossiblePositions(), hasSize(120));
        assertThat(distinct, hasSize(120));

    }

}