package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.internal.StringUtil;

public class CustomerFilter implements HttpRequestFilter {
    private final static String login="/login";
    private final static String open = "/open";
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        System.out.println("权限校验");
        //获取请求url
        String uri = fullRequest.uri();
        if (null != uri && uri.contains(login)) {
            return;
        }
        if (null != uri && uri.contains(open)) {
            String auth = fullRequest.headers().get("auth");
            if (null != auth && !"".equals(auth)) {
                return;
            }else {
                throw new RuntimeException("权限认证失败");
            }
        }

    }
}
