package com.smart.service.excel;

public interface IExportExcel {

    byte[] run() throws Exception;

    String getFileName();

    String getFilePath();
}
