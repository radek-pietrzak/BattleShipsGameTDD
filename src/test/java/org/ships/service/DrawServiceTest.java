package org.ships.service;

import org.junit.jupiter.api.RepeatedTest;
import org.ships.Ship;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DrawServiceTest {

    @RepeatedTest(10)
    void shouldReturnProperlyEncodedCarrierAfterDraw(){

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
    void shouldReturnProperlyEncodedBattleshipAfterDraw(){

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
    void shouldReturnProperlyEncodedDestroyerAfterDraw(){

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
    void shouldReturnProperlyEncodedSubmarineAfterDraw(){

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
    void shouldReturnProperlyEncodedPatrolBoatAfterDraw(){

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

}