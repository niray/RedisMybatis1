package org.niray.mybatis;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.ConfigGenerator;

/**
 * Created by Mac on 16/10/24.
 */
public class MyBatisGenerator {

    private static final String packageName = "org.niray";
    //代码根目录
    private static final String src = "/Users/huway_iosdev2/Documents/SpringMVCDemos/RedisMybatis1/src/main/java";

    public static void main(String[] args) {

        ConfigGenerator cg = new ConfigGenerator();
        // 配置 MySQL 连接
        cg.setDbDriverName("com.mysql.jdbc.Driver");
        cg.setDbUser("root");
        cg.setDbPassword("huway");
        cg.setDbUrl("jdbc:mysql://192.168.1.101:3306/qq?characterEncoding=utf8");
        // 配置包名
        cg.setEntityPackage(packageName + ".entity");
        cg.setMapperPackage(packageName + ".mapper");
        cg.setXmlPackage(packageName + ".mapper.xml");
        cg.setServicePackage(packageName + ".service");
        cg.setServiceImplPackage(packageName + ".service.impl");

        // 配置表主键策略
        cg.setIdType(IdType.AUTO);

        // 配置保存路径
        cg.setSaveDir(src);
        // 其他参数请根据上面的参数说明自行配置，当所有配置完善后，运行AutoGenerator.run()方法生成Code
        // 生成代码
        AutoGenerator.run(cg);
    }

}
