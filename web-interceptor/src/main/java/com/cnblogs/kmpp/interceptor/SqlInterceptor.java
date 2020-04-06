package com.cnblogs.kmpp.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Mybatis-拦截器(Plugin+Interceptor)
 * #@Intercepts 实现Interceptor接口的类声明,使该类注册成为拦截器
 */
@Intercepts(value = {
        @Signature(type = Executor.class, //要拦截的对象
                method = "update",//要拦截的方法
                args = {MappedStatement.class, Object.class, Connection.class, Integer.class}),//拦截方法的参数
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, Connection.class, Integer.class, RowBounds.class, ResultHandler.class,
                        CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, Connection.class, Integer.class, RowBounds.class, ResultHandler.class})})
/*@Component("sqlInterceptor")*/
public class SqlInterceptor implements Interceptor {
    private static final Logger logger = Logger.getLogger(SqlInterceptor.class);

    public Object intercept(Invocation invocation) {
        try {
            Object target = invocation.getTarget();
            Object result = null;
            ObjectMapper mapper = new ObjectMapper();
            if (target instanceof Executor) {
                long start = System.currentTimeMillis();
                Method method = invocation.getMethod();
                /*执行方法*/
                result = invocation.proceed();
                long end = System.currentTimeMillis();
                final Object[] args = invocation.getArgs();

                //获取原始的SQL语句
                MappedStatement ms = (MappedStatement) args[0];
                String commandName = ms.getSqlCommandType().name();

                String name = method.getName();
                if (commandName.startsWith("INSERT")) {
                    name += "=新增";
                } else if (commandName.startsWith("UPDATE")) {
                    name += "=修改";
                } else if (commandName.startsWith("DELETE")) {
                    name += "=删除";
                } else if (commandName.startsWith("SELECT")) {
                    name += "=查询";
                }
                String message = "[SqlInterceptor] execute [" + name + "] cost [" + (end - start) + "] ms";
                StringBuilder stringBuffer = new StringBuilder();
                stringBuffer.append(message);
                stringBuffer.append("\n");

                //获取原始的SQL参数
                Object parameterObject = args[1];
                BoundSql boundSql = ms.getBoundSql(parameterObject);
                String sql = boundSql.getSql();

                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                String parameterObjects = mapper.writeValueAsString(boundSql.getParameterObject());


                String id = ms.getId();
                stringBuffer.append("id=").append(id);
                stringBuffer.append("\r\n");

                stringBuffer.append("sql=").append(sql);
                stringBuffer.append("\n");

                stringBuffer.append("parameterMappings=").append(parameterMappings);
                stringBuffer.append("\n");

                stringBuffer.append("parameterObjects=").append(parameterObjects);
                stringBuffer.append("\n");
                // stringBuffer.append("result="+result);
                if (result != null) {
                    if (result instanceof List) {
                        stringBuffer.append("result=").append(((List) result).size());
                    } else if (result instanceof Collection) {
                        stringBuffer.append("result=").append(((Collection) result).size());
                    } else {
                        stringBuffer.append("result=" + 1);
                    }
                } else {
                    stringBuffer.append("result=NULL");
                }
                stringBuffer.append("\n");
                logger.warn(stringBuffer.toString());
                //数组可能为空
                ParameterMapping mapping = boundSql.getParameterMappings().get(0);
                Configuration configuration = ms.getConfiguration();
                DynamicContext context = new DynamicContext(configuration, parameterObject);
                String originSql = context.getSql();
                System.out.println("@@@@originSql:" + originSql);
            }
            return result;
        } catch (Exception ex) {
            System.out.println("%%%%Exception:" + ex.getMessage());
        }

        return null;
    }

    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}