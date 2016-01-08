package app.wen.com.okhttpdemo.utils;

/**
 * 
 * Copyright 2014-2015 Secken, Inc.  All Rights Reserved.
 * @Description: 上传图片bean
 * @author lizhangfeng
 * @version V1.2.3
 * @date 2015-6-5 下午12:28:48
 */
public class FormFile
{
	public String inputStreamKey;// 图片的key
	public String fileName;// 图片名称
    public byte [] dates;// 图片流

	public FormFile(String inputStreamKey, byte [] dates, String fileName)
	{
		this.fileName = fileName;
		this.inputStreamKey = inputStreamKey;
        this.dates = dates;
	}

}
