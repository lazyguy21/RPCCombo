package org.yyf.rpcCombo.cxf.server;

import org.apache.logging.log4j.core.util.Throwables;
import org.yyf.rpcCombo.cxf.api.ExceptionService;

import java.util.List;

/**
 * Created by tobi on 16-8-3.
 */
public class ExceptionServiceImpl implements ExceptionService {
    @Override
    public void ex() {
        throw new RuntimeException("some reason for error,here we go!");
    }

    @Override
    public List ex2() {
        RuntimeException exception = new RuntimeException("some reason for error,here we go!");
        List<String> strings = Throwables.toStringList(exception);
        return strings;
    }
    @Override
    public Exception ex3(){
        return new RuntimeException("some reason for error,here we go!");
    }
    @Override
    public String ex4(){
        RuntimeException exception = new RuntimeException("some reason for error,here we go!");
        String stackTraceAsString = com.google.common.base.Throwables.getStackTraceAsString(exception);
        return stackTraceAsString;
    }


}
