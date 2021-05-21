package com.revature.shop.commerce;


import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.shop.commerce.model.Cart;
import com.revature.shop.commerce.controller.CartController;
import com.revature.shop.commerce.service.CartService;
import com.revature.shop.commerce.exception.UnableToSaveCartException;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.assertEquals;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerUnitTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CartService beanService;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void saveCart() throws Exception {

            //Instantiate a cart and save it
            Cart cart = new Cart("2", "shallal", new HashMap<String, Integer>(){{
            put("t-shirt", 1);
            }});


            //Unit Test that the saved cart instance is returned by saveCart
            given(beanService.saveCart(cart)).willReturn(cart);

            //Integration Test of a post request with cart on the target endpoint, passing but not returning a cart!
            MvcResult mvcResult = mvc.perform(post("/commerce/saveCart")
                .content(asJsonString(cart))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn();
            String responseBody = mvcResult.getResponse().getContentAsString();
            System.out.println("responseBody: " + responseBody); //nothing in responseBody, why?
    }
}
