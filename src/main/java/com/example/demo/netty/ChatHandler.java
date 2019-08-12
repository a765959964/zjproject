package com.example.demo.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 处理消息的handler
 * TextWebSocketFrame 在netty中，是用于websocket专门处理文本的对象，frame是消息的载体
 * @author 张帆
 * @date 2019-7-5 16:05:22
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	//用于记录和管理 所以客户端的channle
	private static ChannelGroup clients = new 
			DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			TextWebSocketFrame msg) throws Exception {
		//获取客户端传输过来的消息
		String content = msg.text();
		System.out.println("接收到的消息11:" + content);
		
		for(Channel channel : clients){
			channel.writeAndFlush(new TextWebSocketFrame("【服务器接收到消息为:】"+content));
		}
		//下面这个方法跟for循环一致
//		channel.writeAndFlush(new TextWebSocketFrame("【服务器接收到消息为:】"+content));
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
