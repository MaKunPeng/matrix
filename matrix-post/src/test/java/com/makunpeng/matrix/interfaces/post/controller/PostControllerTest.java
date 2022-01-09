package com.makunpeng.matrix.interfaces.post.controller;

import com.makunpeng.matrix.common.api.ResponseResult;
import com.makunpeng.matrix.infra.shared.util.JsonUtils;
import com.makunpeng.matrix.interfaces.post.command.PostPublishCommand;
import com.makunpeng.matrix.interfaces.post.command.PostUpdateCommand;
import com.makunpeng.matrix.interfaces.post.dto.PostDetailsDTO;
import com.makunpeng.matrix.interfaces.post.dto.PostInfoDTO;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * PostController 单元测试
 * @author Aaron Ma
 */
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private PostController postController;
    @Autowired
    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(PostControllerTest.class);

    @Ignore
    @Test
    void publish() {
        PostPublishCommand postPublishCommand = new PostPublishCommand();
        postPublishCommand.setUid(2L);
        postPublishCommand.setTitle("Test");
        postPublishCommand.setSummary("Test publish");
        postPublishCommand.setContent("# Test");
        ResponseResult<PostInfoDTO> result = postController.publish(postPublishCommand);
        logger.info("文章发布成功");
    }

    @Ignore
    @Test
    void update() {
        PostUpdateCommand postUpdateCommand = new PostUpdateCommand();
        postUpdateCommand.setPid(57633150L);
        postUpdateCommand.setTitle("Test");
        postUpdateCommand.setSummary("Test update");
        postUpdateCommand.setContent("# Test update");

        postController.update(postUpdateCommand);
        logger.info("文章更新成功");
    }

    @Test
    void getPostDetails() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/post/details/72601489"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void listPostInfo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/post/list")
                .content("{\"uid\":1,\"pageSize\":10,\"pageNumber\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testJson() {
        String json = "{\"pid\":72601489,\"postInfoDTO\":{\"id\":686368495725707264,\"pid\":72601489,\"uid\":2,\"title\":\"Test\",\"summary\":\"Test publish\",\"ctime\":1641572601498,\"mtime\":1641572601498},\"content\":\"# Test\"}";
        PostDetailsDTO a = new PostDetailsDTO();
        a.setPid(111L);
        a.setContent("asdfasdf");
        PostInfoDTO b = new PostInfoDTO();
        b.setPid(111L);
        b.setTitle("SDF");
        b.setSummary("S");
        a.setPostInfoDTO(b);
//        PostDetailsDTO parse = JsonUtils.parse(json, PostDetailsDTO.class);
//        System.out.printf(parse.toString());
        String s = JsonUtils.toJson(a);
        PostDetailsDTO parse = JsonUtils.parse(s, PostDetailsDTO.class);
        System.out.println(s);

    }
}