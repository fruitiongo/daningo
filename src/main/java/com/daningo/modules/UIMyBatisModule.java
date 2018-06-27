package com.daningo.modules;

import com.daningo.db.DBPropertiesProvider;
import com.daningo.db.ProdDBPropertiesProvider;
import com.daningo.mappers.MessageMapper;
import com.daningo.mappers.TopicMapper;
import com.daningo.mappers.UserMapper;
import com.daningo.util.EnvironmentProvider;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.c3p0.C3p0DataSourceProvider;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.name.Names.bindProperties;
import java.util.logging.Logger;

/**
 * Created by naing on 6/21/18.
 */
public class UIMyBatisModule extends MyBatisModule {
    private static final Logger logger =Logger.getLogger(UIMyBatisModule.class.getSimpleName());
    private DBPropertiesProvider dbPropertiesProvider;
    private boolean isTest;

    @Override
    protected void initialize() {
        EnvironmentProvider environmentProvider = EnvironmentProvider.getEnvironmentProvider();


        dbPropertiesProvider = new ProdDBPropertiesProvider();
        install(JdbcHelper.MySQL);
        bindDataSourceProviderType(C3p0DataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);

        bindProperties(binder(), dbPropertiesProvider.getDBProperties());

        addMapperClass(UserMapper.class);
        addMapperClass(TopicMapper.class);
        addMapperClass(MessageMapper.class);


    }

    public UIMyBatisModule() {
        this.isTest = false;
    }

    public UIMyBatisModule(boolean isTest){
        this.isTest = isTest;
    }

}
