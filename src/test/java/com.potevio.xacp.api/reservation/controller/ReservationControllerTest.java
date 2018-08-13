package com.potevio.xacp.api.reservation.controller;

import com.potevio.xacp.api.PavilionApiServiceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Desc
 * @Version shadowolf
 * @Date 2018/8/5 下午5:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PavilionApiServiceApplication.class)
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMyReservation() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reservation/my-reservations").
                header("username", "18404968725")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    public void testCreateReservation() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservation").
                accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).
                content(("{\"visitDate\":1533613455,\"visitors\":[" +
                        "{\"name\":\"张伟\",\"idcardNumber\":\"142322199808080000\",\"mobile\":\"18404968725\"}," +
                        "{\"name\":\"李文\",\"idcardNumber\":\"142322199808060000\",\"mobile\":\"18404968721\"}" +
                        "]}").getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
