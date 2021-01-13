package org.noob;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty实现客户端--连接
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        // 传入线程、通道、处理器之后进行连接
        ChannelFuture future = bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {

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
        // 关闭线程
        eventLoopGroup.shutdownGracefully();
    }
}
