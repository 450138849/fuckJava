package org.noob;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * Netty实现客户端--写数据
 */
public class Client2 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = null;
        try {
            eventLoopGroup = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();

            // 传入线程、通道、处理器之后进行连接
            ChannelFuture future = bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel channel) {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) {
                                    ByteBuf buffer = null;
                                    try {
                                        buffer = Unpooled.copiedBuffer("我是客户端2!".getBytes());
                                        // 使用如下方法byteBuf会自动释放内存引用，不要再主动释放
                                        ctx.writeAndFlush(buffer);
                                    } finally {
                                        // 释放内存ByteBuf由于使用直接内存绕过了GC机制，所以要手动释放,这里writeAndFlush方法已经释放了一次，所以不要在这里再释放
//                                        if (buffer != null) ReferenceCountUtil.release(buffer);
                                    }

                                }
                            });
                        }
                    })
                    .connect("localhost", 8090);

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        System.out.println("连接不成功!");
                    } else {
                        System.out.println("连接成功!");

                    }
                }
            });
            // 要等连接完成之后才能关闭线程
            future.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程
            eventLoopGroup.shutdownGracefully();
        }
    }
}
