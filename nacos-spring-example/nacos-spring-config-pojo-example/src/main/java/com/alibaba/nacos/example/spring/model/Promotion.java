package com.alibaba.nacos.example.spring.model;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosIgnore;
import com.alibaba.nacos.api.config.annotation.NacosProperty;

/**
 * @author hexu.hxy
 * @date 2019/1/6
 *
 * Nacos 控制台添加配置：
 * Data ID：promotion.properties
 * Group：spring-example
 * 配置内容：
 *  sku=1
 *  price=99.9
 *  amount=1000
 *  desc=desc
 *  description=description
 *  sold=99
 *
 */
@NacosConfigurationProperties(dataId = "promotion.properties", groupId = "spring-example", autoRefreshed = true)
public class Promotion {

    private long sku;

    private double price;

    private int amount;

    @NacosProperty(value = "desc")
    private String description;

    @NacosIgnore
    private int sold;

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
