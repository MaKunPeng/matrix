package com.makunpeng.matrix.interfaces.post.query;

import lombok.Data;

/**
 * @author MaKunPeng
 * @version 1.0
 * since
 **/
@Data
public class PostInfoListQuery {
    private Long uid;
    private String catalog;
    private Integer pageSize;
    private Integer pageNumber;
}
