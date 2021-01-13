# 1.入门案例

**客户端**

```java
package org.noob;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Hello world!
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

```

**服务器**

```java
package org.noob;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main(String[] args) {
        new Server().test();
    }

    public void test() {
        ServerSocket ss;

        try {
            ss = new ServerSocket(8090);
            Socket socket = ss.accept();
            System.out.println("一个client连接上了!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

# 官网案例



# 多人聊天

> 思路：每个客户端监听方法触发netty将消息发送到服务器，服务器接收到之后就将消息转发给所有的客户端，客户端接收到消息之后添加到聊天室里

