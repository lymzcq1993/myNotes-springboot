spring:
  profiles:
    active: dev
  cloud:
    #dataid = ${prefix}-${spring.profiles.active}.${file-extension}
    #所以此处为 product-center-dev.properties
    nacos:
      discovery:
        server-addr: 192.168.8.88:8848
        #命名空间，需要创建，默认可为public   namespace id为空
        namespace: 8cb4a832-1d3f-4cc8-ac52-e0d611a2b9ca
#        group: order1
        #cluster-name: WH
        #metadata:
          #version: v1
      config:
        server-addr: 192.168.8.88:8848
        #只支持yaml和properties
        file-extension: yaml
        namespace: 8cb4a832-1d3f-4cc8-ac52-e0d611a2b9ca
  application:
    name: product-center
#server:
#  port: 8083