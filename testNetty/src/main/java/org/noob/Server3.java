package org.noob;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * Netty实现服务端--读数据
 */
public class Server3 {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        try {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup(2);
            ServerBootstrap bootstrap = new ServerBootstrap();
            ChannelFuture future = bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel channel) {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new ChannelInboundHandlerAdapter() {
                                // 重写该方法,处理读入数据逻辑
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                    ByteBuf buf = null;
                                    try {
                                        buf = (ByteBuf) msg;
                                        byte[] bytes = new byte[buf.readableBytes()];
                                        buf.getBytes(buf.readerIndex(), bytes);
                                        System.out.println(new String(bytes));
                                    } finally {
                                        // 释放资源
                                        if (buf != null)
                                            ReferenceCountUtil.release(buf);
                                    }
                                }
                            });
                        }
                    }).bind(8090).sync();
            // 等待触发channel的close方法的时候才会执行后续代码
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
