package com.imooc.sell.VO;

import lombok.Data;

/**
 * HTPP 请求返回的最外层对象
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T date;
}
