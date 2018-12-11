package com.example.demo.core.configurer;

import com.example.demo.core.shiro.CustomRealm;
import com.example.demo.model.SysMenu;
import com.example.demo.service.SysMenuService;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * shiro 配置
 */
@Configuration
public class ShiroConfigurer {


    @Resource
    private SysMenuService sysMenuService;


    /**
     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     */
    @Bean
    public Realm realm() {
        return new CustomRealm();
    }



    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * 用于实现权限
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        /*List<SysMenu> list = sysMenuService.getAll();
        for(int i = 0,length = list.size();i<length;i++){
            SysMenu sysMenu = list.get(i);
            chain.addPathDefinition(sysMenu.getUrl(), sysMenu.getPerms());
        }
*/
        /**
         *  使用shiro 配置可访问的页面
         */
        chain.addPathDefinition("/static/**", "anon");//可以匿名访问
        chain.addPathDefinition("/webjars/**", "anon");
        chain.addPathDefinition("/template/**", "anon");
        chain.addPathDefinition("/login.html", "anon");
        chain.addPathDefinition("/error**", "anon");
        chain.addPathDefinition("/404", "anon");
        chain.addPathDefinition("/reg", "anon");
        chain.addPathDefinition("/login*", "anon");
        chain.addPathDefinition("/login", "anon");
        chain.addPathDefinition("/logout", "anon");
        chain.addPathDefinition("/sysuser/login", "anon");
        chain.addPathDefinition("/index.html", "anon");
        chain.addPathDefinition("/index", "anon");
        chain.addPathDefinition("/**", "anon");
        return chain;
    }
}
