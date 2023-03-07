package com.yautumn.common.utils;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class BatchUtils {

    /**
     * 设置批量操作条数
     */
    private static final int BATCH_SIZE= 1000;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     *
     * @param data  批量操作的数据
     * @param mapperClass   mybatis操作Mapper
     * @param function  自定义操作逻辑 在什么条件下执行
     * @return
     */
    public  <T,U,R> int batchUpdateOrInsert(List<T> data, Class<U> mapperClass, BiFunction<T,U,R> function) {

        int i = 1;
        SqlSession batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

        try {
            U mapper = batchSqlSession.getMapper(mapperClass);
            int size = data.size();
            for (T element : data) {
                function.apply(element,mapper);
                if ((i % BATCH_SIZE == 0) || i == size) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.commit(!TransactionSynchronizationManager.isSynchronizationActive());
            return 1;
        } catch (Exception e) {
            batchSqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            batchSqlSession.close();
        }
    }

}
