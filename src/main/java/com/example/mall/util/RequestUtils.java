package com.example.mall.util;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUtils {

private String userId;
private LocalDateTime lastLoginTime;
private String clientIP;
private String userAgent;

    public static  String getUserAgent(HttpServletRequest request){

        String requestUserAgent = request.getHeader("USER-AGENT").toLowerCase();
        return requestUserAgent;
    }

    public static String getClientIp(HttpServletRequest request){
    String ip = null;

    ip = request.getHeader("X-Forwarded-For");

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getHeader("Proxy-Client-IP");
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getHeader("WL-Proxy-Client-IP");
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getHeader("HTTP_CLIENT_IP");
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getHeader("X-Real-IP");
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getHeader("X-RealIP");
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getHeader("REMOTE_ADDR");
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.toString().toLowerCase())) {
    ip = request.getRemoteAddr();
    }

    return ip;
    }
}
