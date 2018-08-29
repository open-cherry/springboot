package com.example.proto;

/**
 * 时间序列测试
 *
 * @author <a href="mailto:simling82@gmail.com">Simling</a>
 * @version v1.0 on 2017/8/10
 */
public interface URI {

    String SMCU_USER = "/smcu/user";
    String SMCU_USER_ADD = "/smcu/user/add";
    String SMCU_USER_GET = "/smcu/user/get";
    String SMCU_USER_UPDATE = "/smcu/user/update";
    String SMCU_USER_QUERY = "/smcu/user/query";
    String SMCU_USER_UPDATE_BY_JSON = "/smcu/user/updateByJson";
}
