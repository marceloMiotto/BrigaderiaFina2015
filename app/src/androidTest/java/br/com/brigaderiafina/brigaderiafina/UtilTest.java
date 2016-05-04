package br.com.brigaderiafina.brigaderiafina;

import org.junit.Before;
import org.junit.Test;

import br.com.brigaderiafina.brigaderiafina.utils.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



public class UtilTest {

    Util mUtil;

    public UtilTest(){}

    @Before
    public void createObject(){
        mUtil = new Util();
    }
    @Test
    public void currencyFormatShouldBeValid(){



        String result = mUtil.currencyFormat("100.00");

        assertNotNull(result);
        assertEquals("100,00",result);


    };

   @Test
    public void currencyFormatShouldBeInvalid(){


        String result = mUtil.currencyFormat("100.00");
        assertNotNull(result);



    };

}
