package com.jk.controller;



import com.alibaba.fastjson.JSONObject;
import com.jk.model.Dldshop;
import com.jk.model.SearchResult;
import com.jk.model.Xxmember;
import com.jk.service.DldService;
import com.jk.utils.DldSolr;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping("dldshop")
public class DldController {

    @Autowired
    private DldService dldshopServer;
    /*商品查询存储solr*/
    @RequestMapping("dldshopinsertsolr")
    public void  dldshopinsertsolr()throws Exception{
        /*int page,int rows*/
        List<Dldshop> lisr= dldshopServer.dldshopinsertsolr();
        DldSolr.addDoc(lisr);
    }


    @RequestMapping("getshopquery")
    @ResponseBody
    public List<Dldshop> getshopquery(String tile, Integer page, Integer rows, Integer price,String type,String barand)throws SolrServerException, IOException {

        List<Dldshop> dldshops = DldSolr.selectDoc(tile, page, rows,price, type,barand);
        return dldshops;
    }

    @RequestMapping("getip")
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    //登录
    @RequestMapping("login")
    @ResponseBody
    public String login(Xxmember user, HttpSession session, HttpServletRequest request)throws Exception{
        JSONObject json =  dldshopServer.login(user);

        Xxmember nuser = (Xxmember) json.get("nuser");
        if(nuser != null){
            request.getSession().setAttribute("userById", nuser);
            session.setAttribute("userByIdaaa", nuser.getId());
            session.setAttribute("username", nuser.getUsername());
            session.setAttribute("loginUser", nuser);
        }
        return json.getString("flag");
    }
    @RequestMapping("logintiao")
    public String logintiao( HttpServletRequest request,Model model){
        Xxmember user = (Xxmember)request.getSession().getAttribute("userById");
        model.addAttribute("hyuanuser",user);
        return "shop/index";
    }

    /*会员注册*/
    @RequestMapping("userzhuce")
    @ResponseBody
    public String userzhuce(Xxmember xxm)throws Exception{
        dldshopServer.userzhuce(xxm);
        return  "1";
    }


}
