package netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebsocketServer {
	
	public void bind(int port) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast("http-codec",new HttpServerCodec());
						ch.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));
						ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
						ch.pipeline().addLast("handel",new WebsocketServerHandler());
					}
				});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class WebsocketServerHandler extends ChannelInboundHandlerAdapter{

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			//如果是HTTP请求
			if(msg instanceof FullHttpRequest){
				handleHttpRequest(ctx,(FullHttpRequest)msg);
			}
			//如果是WebSocket
			if(msg instanceof WebSocketFrame){
				handleWebsocketFrame(ctx,(WebSocketFrame)msg);
			}
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx)
				throws Exception {
			ctx.flush();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
		
	}

	private static void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest request){
		
	}
	
	private static void handleWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
		
	}
}
