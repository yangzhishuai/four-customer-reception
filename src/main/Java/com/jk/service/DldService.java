package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.Dldshop;
import com.jk.model.SearchResult;
import com.jk.model.Xxmember;

import java.util.List;

public interface DldService {
    List<Dldshop> dldshopinsertsolr()throws Exception;

    JSONObject login(Xxmember user)throws Exception;

    void userzhuce(Xxmember xxm)throws Exception;
}
