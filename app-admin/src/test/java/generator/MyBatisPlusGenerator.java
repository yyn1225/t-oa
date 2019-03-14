package generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.jtech.marble.util.text.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus代码生成器(用于生成entity)<br>
 *
 * @author sogyf
 */
public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        String targetPath = "E:\\home";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(targetPath);//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("jtech");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.SQL_SERVER);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dsc.setUsername("sa");
        dsc.setPassword("sale1213@");
        dsc.setUrl("jdbc:sqlserver://192.168.100.161:1433;DatabaseName=Absen");
        mpg.setDataSource(dsc);

        String pkg = "com.jtech.toa.{}.system";
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"absen_offer_spares_"});//
        // 此处可以修改为您的表前缀
        strategy.setInclude(new String[]{
                "absen_offer_spares_formula"
        });
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);

        pc.setEntity(StrUtil.format(pkg, "entity"));
        pc.setMapper(StrUtil.format(pkg, "dao"));
        pc.setXml(StrUtil.format(pkg, "mapper"));
        pc.setService(StrUtil.format(pkg, "service"));
        pc.setServiceImpl(StrUtil.format(pkg, "service.impl"));
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}