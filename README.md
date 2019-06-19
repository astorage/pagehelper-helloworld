# pagehelper-helloworld

## 使用步骤
1、加入依赖
compile group: 'com.github.pagehelper', name: 'pagehelper', version: '5.1.10'

2、将插件加入spring上下文

    @Bean
    public PageInterceptor pageHelper() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageInterceptor.setProperties(p);
        return pageInterceptor;
    }

3、在spring上下文中要使用注解@EnableTransactionManagement

4、最后是在需要分页的地方加入下面的代码

    PageHelper.startPage(page.getPageNum(), page.getPageSize());
    if (page.getSort() != null) {
        PageHelper.orderBy(PageHelperUtils.getOrderBy(page.getSort()));
    }
    
   查询出来List是com.github.pagehelper.Page类型的，里面有分页信息。
   
   ## 原理：
   用到了mybatis的拦截器 org.apache.ibatis.plugin.Interceptor
   
   1、PageInterceptor.intercept()
   
   2、com.github.pagehelper.util.ExecutorUtil
    
    /**
        * 分页查询
        *
        * @param dialect
        * @param executor
        * @param ms
        * @param parameter
        * @param rowBounds
        * @param resultHandler
        * @param boundSql
        * @param cacheKey
        * @param <E>
        * @return
        * @throws SQLException
        */
       public static  <E> List<E> pageQuery(Dialect dialect, Executor executor, MappedStatement ms, Object parameter,
                                    RowBounds rowBounds, ResultHandler resultHandler,
                                    BoundSql boundSql, CacheKey cacheKey) throws SQLException {
           //判断是否需要进行分页查询
           if (dialect.beforePage(ms, parameter, rowBounds)) {
               //生成分页的缓存 key
               CacheKey pageKey = cacheKey;
               //处理参数对象
               parameter = dialect.processParameterObject(ms, parameter, boundSql, pageKey);
               //调用方言获取分页 sql
               String pageSql = dialect.getPageSql(ms, boundSql, parameter, rowBounds, pageKey);
               BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
   
               Map<String, Object> additionalParameters = getAdditionalParameter(boundSql);
               //设置动态参数
               for (String key : additionalParameters.keySet()) {
                   pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
               }
               //执行分页查询
               return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
           } else {
               //不执行分页的情况下，也不执行内存分页
               return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, boundSql);
           }
       }
   
        com.github.pagehelper.PageHelper
        
       @Override
       public String getPageSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey pageKey) {
           return autoDialect.getDelegate().getPageSql(ms, boundSql, parameterObject, rowBounds, pageKey);
       }
   
   3、com.github.pagehelper.dialect.AbstractHelperDialect
   
    @Override
       public String getPageSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey pageKey) {
           String sql = boundSql.getSql();
           Page page = getLocalPage();
           //支持 order by
           String orderBy = page.getOrderBy();
           if (StringUtil.isNotEmpty(orderBy)) {
               pageKey.update(orderBy);
               sql = OrderByParser.converToOrderBySql(sql, orderBy);
           }
           if (page.isOrderByOnly()) {
               return sql;
           }
           return getPageSql(sql, page, pageKey);
       }
   
   4、com.github.pagehelper.dialect.helper.MySqlDialect
   
   @Override
       public String getPageSql(String sql, Page page, CacheKey pageKey) {
           StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
           sqlBuilder.append(sql);
           if (page.getStartRow() == 0) {
               sqlBuilder.append(" LIMIT ? ");
           } else {
               sqlBuilder.append(" LIMIT ?, ? ");
           }
           return sqlBuilder.toString();
       }



