package com.potevio.xacp.api.trees;

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PavilionApiServiceApplication.class)
@AutoConfigureMockMvc
public class TreesControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateReservation() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/trees/create-order-pay").
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
