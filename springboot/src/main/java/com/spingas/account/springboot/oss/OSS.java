package com.spingas.account.springboot.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSSClientBuilder;

public class OSS {
    public static void main(String[] args) {
        String endpoint = "https://spingas.oss-ap-southeast-1.aliyuncs.com";
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tH9FiZkhmY8ku46TwtT";
        String accessKeySecret = "jfue556zrsJxnGMywhkbVRFMMwb01e";

// 创建ClientConfiguration实例，您可以根据实际情况修改默认参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
// 设置是否支持CNAME。CNAME用于将自定义域名绑定到目标Bucket。
        conf.setSupportCname(true);

// 创建OSSClient实例。
        OSS ossClient = (OSS) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);
    }
}
