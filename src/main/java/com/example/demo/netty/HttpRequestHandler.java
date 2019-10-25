package com.example.demo.netty;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.List;

/**
 * Created by 张帆 on 2019/10/25.
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

    private static PersonService personService;

    //用于记录和管理 所以客户端的channle
    private static ChannelGroup clients = new
            DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    static {
        personService = SpringUtil.getBean(PersonService.class);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame web) throws Exception {
        String content = web.text();
        List<Person> personList = personService.selectAll();
        JSONArray jsonObject = JSONUtil.parseArray(personList);
        System.out.println(jsonObject.toString());
        System.out.println("aa"+personList.size());
        for(Channel channel : clients){
            channel.writeAndFlush(new TextWebSocketFrame("【服务器接收到消息为:】"+jsonObject.toString()));
        }
    }

    /**
     * 当客户端连接服务端之后（打开链接）
     * 获取客户端的channle,并且放到channelGroup 中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发handlerRemoved，channelGroup 会自动移除对应客户端的channel
//		clients.remove(ctx.channel());
//		super.handlerRemoved(ctx);
        System.out.println("客户端断开，channle对应的长id为："+ctx.channel().id().asLongText());
        System.out.println("客户端断开，channle对应的短id为："+ctx.channel().id().asShortText());
    }


}
