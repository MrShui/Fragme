## 1 gradle 编译命令

* 服务端地址文件：`environment`
    - 准生产 `-Penvironment=preproduct`
    - 生产  `-Penvironment=product`
    - 默认（没有-Penvironment 这一坨）为生产环境（product）

    - gradle aR -Penvironment=preproduct  准生产环境配置打包